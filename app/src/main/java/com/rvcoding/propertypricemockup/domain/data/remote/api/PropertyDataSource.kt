package com.rvcoding.propertypricemockup.domain.data.remote.api

import com.rvcoding.propertypricemockup.core.domain.util.DataError
import com.rvcoding.propertypricemockup.core.domain.util.Result
import com.rvcoding.propertypricemockup.data.remote.PropertiesResponse
import com.rvcoding.propertypricemockup.data.remote.RatesResponse

interface PropertyDataSource {
    suspend fun fetchProperties(): Result<PropertiesResponse, DataError.Network>
    suspend fun fetchRates(): Result<RatesResponse, DataError.Network>
}