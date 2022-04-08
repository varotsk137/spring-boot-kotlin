package com.example.kotlin.datasource.mock

import com.example.kotlin.datasource.BankDataSource
import com.example.kotlin.model.entity.Bank
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException

@Repository("mock_repo")
class MockBankDataSource: BankDataSource {

        val banks = mutableListOf(
                Bank("12345", 20.2, 25),
                Bank("1114", 17.14, 0),
                Bank("1321", 0.0, 16),
        )

    override fun retrieveBanks(): Collection<Bank> = banks

//    override fun retrieveBank(accountNumber: String): Bank = banks.first { it.accountNumber == accountNumber }
    override fun retrieveBank(accountNumber: String): Bank =
        banks.firstOrNull() { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Could not find bank with account number $accountNumber")

    override fun createBank(bank: Bank): Bank {
        if (banks.any { it.accountNumber == bank.accountNumber } ) {
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exists.")
        }
        banks.add(bank)

        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = banks.firstOrNull() { it.accountNumber == bank.accountNumber }
            ?: throw NoSuchElementException("Could not find bank with account number ${bank.accountNumber}")

        banks.remove(currentBank)
        banks.add(bank)

        return bank
    }

    override fun deleteBank(accountNumber: String) {
        val currentBank = banks.firstOrNull() { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Could not find bank with account number $accountNumber")

        banks.remove(currentBank)
    }

}