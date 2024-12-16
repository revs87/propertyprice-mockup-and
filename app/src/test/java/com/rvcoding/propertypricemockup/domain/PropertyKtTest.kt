package com.rvcoding.propertypricemockup.domain

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource


class PropertyKtTest {

    @ParameterizedTest
    @CsvSource(
        delimiter = ',',
        textBlock = """
            -10, 0.0
            0, 0.0
            1, 0.1
            20.0001, 2.0
            20.5000, 2.0
            20.5001, 2.1
            45, 4.5
            100, 10.0
            200, 10.0"""
    )
    fun ratingFormatted(initial: String, converted: String) {
        val property = Property.INITIAL.copy(rating = initial.toDouble())

        assertThat(property.ratingFormatted()).isEqualTo(converted)
    }

}