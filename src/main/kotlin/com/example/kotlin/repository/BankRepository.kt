package com.example.kotlin.repository

import com.example.kotlin.model.entity.Bank
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BankRepository: JpaRepository<Bank, String> {

    fun findByAccountNumber(accountNumber: String): Optional<List<Bank>>

}