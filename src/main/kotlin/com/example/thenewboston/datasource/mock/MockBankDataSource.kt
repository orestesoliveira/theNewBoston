package com.example.thenewboston.datasource.mock

import com.example.thenewboston.datasource.BankDataSource
import com.example.thenewboston.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource:BankDataSource {

    val banks = mutableListOf(
        Bank("abcdef", 1.0,1),
        Bank("1234", 3.14,1),
        Bank("asda1231312dddsdad", 1.0,1)
    )

    override fun retrieveBanks(): Collection<Bank> {
         return banks
    }

    override fun retrieveBank(accountNumber: String): Bank {
        return banks.firstOrNull(){it.accountNumber==accountNumber}?:throw NoSuchElementException("Could not find bank for goven account number !!!")
    }

    override fun createBank(bank: Bank): Bank {

        if(banks.any{it.accountNumber==bank.accountNumber}){
            throw IllegalArgumentException("bank with account NUmber : ${bank.accountNumber} already exists")
        }

        banks.add(bank)
        return bank

    }



}