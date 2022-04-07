package com.example.kotlin.service

import com.example.kotlin.datasource.BankDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BankServiceTest {

    private val dataSource: BankDataSource = mockk()
    private val bankService = BankService(dataSource)
    
    @Test
    fun `should call its datasource to retrieve banks`() {
        // given
        every { dataSource.retrieveBanks() } returns emptyList()
        
        // when
        val banks = bankService.getBanks()
        
        // then
        verify(exactly = 1) { dataSource.retrieveBanks() }

    }
}