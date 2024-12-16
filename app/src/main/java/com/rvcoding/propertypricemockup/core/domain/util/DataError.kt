package com.rvcoding.propertypricemockup.core.domain.util

sealed interface DataError: AppError {
    enum class Network: DataError {
        REQUEST_TIMEOUT,
        NO_INTERNET,
        UNKNOWN
    }

    enum class Local: DataError {
        DISK_FULL
    }
}