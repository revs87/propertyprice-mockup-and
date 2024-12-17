package com.rvcoding.propertypricemockup.data.repository

import com.rvcoding.propertypricemockup.data.db.PropertyDao
import com.rvcoding.propertypricemockup.data.db.PropertyEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PropertyDatabaseFake : PropertyDao {
    private val list = (0..5).map { i -> PropertyEntity.STUB.copy(id = i.toLong()) }.toMutableList()

    override fun getProperties(): Flow<List<PropertyEntity>> = flow { emit(list) }
    override suspend fun getProperty(id: Int): PropertyEntity? = list.find { it.id == id.toLong() }?.let { return null }
    override suspend fun insertProperty(property: PropertyEntity): Long {
        list.add(property)
        return property.id
    }
}