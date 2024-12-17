package com.rvcoding.propertypricemockup.data.repository

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.rvcoding.propertypricemockup.TestDispatchers
import com.rvcoding.propertypricemockup.data.remote.api.PropertyService
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PropertyRepositoryImplTest {
    private lateinit var testDispatchers: TestDispatchers
    private lateinit var propertyRepository: PropertyRepositoryImpl

    @BeforeEach
    fun setUp() {
        testDispatchers = TestDispatchers()
        propertyRepository = PropertyRepositoryImpl(
            propertyDao = PropertyDatabaseFake(),
            propertyDataSource = PropertyService.createApiForTesting(),
        )
    }

    @Test
    fun listing() = runTest(testDispatchers.testDispatcher) {
        propertyRepository.listing().test {
            val list = awaitItem()
            assertThat(list.size).isEqualTo(6)
            cancelAndConsumeRemainingEvents()
        }
    }
}