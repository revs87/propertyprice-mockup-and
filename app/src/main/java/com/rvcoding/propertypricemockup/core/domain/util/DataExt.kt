package com.rvcoding.propertypricemockup.core.domain.util

import android.database.sqlite.SQLiteFullException
import com.rvcoding.propertypricemockup.core.domain.util.Result.Error
import com.rvcoding.propertypricemockup.core.domain.util.Result.Success
import java.io.IOException
import java.net.SocketTimeoutException


inline fun <T> networkCall(
    block: () -> T
): Result<T, DataError.Network> =
    try {
        Success(block.invoke())
    } catch (_: IOException) {
        Error(DataError.Network.NO_INTERNET)
    } catch (_: SocketTimeoutException) {
        Error(DataError.Network.REQUEST_TIMEOUT)
    } catch (_: Exception) {
        Error(DataError.Network.UNKNOWN)
    }

inline fun <T> dbCall(
    block: () -> T
): Result<T, DataError.Local> =
    try {
        Success(block.invoke())
    } catch (_: SQLiteFullException) {
        Error(DataError.Local.DISK_FULL)
    } catch (_: Exception) {
        Error(DataError.Local.UNKNOWN)
    }
