package com.rizwan.newsbuddy.utils

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.rizwan.newsbuddy.networking.Resource

abstract class NetworkBoundResource<ResponseType, RequiredType> {

    //private val TAG = "NetworkBoundResource"

    private val result = MediatorLiveData<Resource<RequiredType>>()

    init {
        result.value = Resource.Loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                //Log.e(TAG, "ShouldFetch TRUE, calling API")
                result.addSource(dbSource) { oldData ->
                    setValue(Resource.Success(oldData))
                }
                fetchFromNetwork(dbSource)
            } else {
                //Log.e(TAG, "ShouldFetch FALSE, getting from DB")
                result.addSource(dbSource) { oldData ->
                    setValue(Resource.Success(oldData))
                }
            }
        }

    }

    @MainThread
    private fun setValue(newValue: Resource<RequiredType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<RequiredType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            setValue(Resource.Loading(newData))
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is ApiSuccessResponse -> {
                    AppExecutors.diskIO().execute {
                        saveCallResult(processResponse(response))
                        AppExecutors.mainThread().execute {
                            result.addSource(loadFromDb()) { newData ->
                                setValue(Resource.Success(newData))
                            }
                        }
                    }

                }
                is ApiErrorResponse -> {
                    result.addSource(dbSource) { oldData ->
                        setValue(Resource.Error(response.errorMessage, oldData))
                    }

                }
                is ApiEmptyResponse -> {
                    AppExecutors.mainThread().execute {
                        result.addSource(loadFromDb()) { oldData ->
                            setValue(Resource.Empty(oldData))
                        }
                    }

                }
            }.exhaustive
        }

    }

    fun asLiveData() = result as LiveData<Resource<RequiredType>>

    @WorkerThread
    protected abstract fun processResponse(response: ApiSuccessResponse<ResponseType>): RequiredType

    @WorkerThread
    protected abstract fun saveCallResult(item: RequiredType)

    @WorkerThread
    protected abstract fun shouldFetch(data: RequiredType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<RequiredType>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<ResponseType>>

}