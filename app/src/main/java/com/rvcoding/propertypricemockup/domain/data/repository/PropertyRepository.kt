package com.rvcoding.propertypricemockup.domain.data.repository

import com.rvcoding.propertypricemockup.core.domain.util.DataError
import com.rvcoding.propertypricemockup.domain.Property
import com.rvcoding.propertypricemockup.domain.Rates
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow

interface PropertyRepository {
    /**
     * UDF - Unidirectional Data Flow
     * Source of truth comes from the listing() method.
     * Action to reset the data comes from the refresh() method.
     * */
    fun listing(): Flow<List<Property>>
    suspend fun refresh()

    suspend fun refreshDetails(id: Long): Property?
    suspend fun rates(): Rates

    val errors: Channel<DataError>
}