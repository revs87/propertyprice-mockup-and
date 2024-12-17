package com.rvcoding.propertypricemockup

import com.rvcoding.propertypricemockup.core.DispatchersProvider
import kotlinx.coroutines.test.StandardTestDispatcher

class TestDispatchers : DispatchersProvider {
    internal val testDispatcher = StandardTestDispatcher()

    override val main = testDispatcher
    override val mainImmediate = testDispatcher
    override val io = testDispatcher
    override val default = testDispatcher
}