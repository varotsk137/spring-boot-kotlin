package com.example.kotlin.datasource.jpa

import com.example.kotlin.datasource.BankDataSource
import com.example.kotlin.model.entity.Bank
import com.example.kotlin.repository.BankRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException

@Repository("jpa_repo")
class JpaBankDataSource(
    @Autowired private val repository: BankRepository
) : BankDataSource {

    override fun retrieveBanks(): Collection<Bank> = repository.findAll()

    override fun retrieveBank(accountNumber: String): Bank = repository.findByIdOrNull(accountNumber)
        ?: throw NoSuchElementException("Could not find bank with account number $accountNumber")

    override fun createBank(bank: Bank): Bank {
        if (repository.findByIdOrNull(bank.accountNumber) != null) {
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exists.")
        }
        repository.save(bank)
        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        repository.findByIdOrNull(bank.accountNumber)
            ?: throw NoSuchElementException("Could not find bank with account number ${bank.accountNumber}")
        repository.save(bank)
        return bank
    }

    override fun deleteBank(accountNumber: String) {
        val currentBank = repository.findByIdOrNull(accountNumber)
            ?: throw NoSuchElementException("Could not find bank with account number $accountNumber")
        repository.delete(currentBank)
    }

}