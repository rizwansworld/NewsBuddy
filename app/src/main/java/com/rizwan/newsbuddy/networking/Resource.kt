package com.rizwan.newsbuddy.networking

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Empty<T>(oldData: T? = null) : Resource<T>(oldData)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}