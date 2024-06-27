package com.example.thenewboston.service

import com.example.thenewboston.datasource.BankDataSource
import com.example.thenewboston.model.Bank
import org.springframework.stereotype.Service
import java.util.*

@Service
class BankService(private val dataSource: BankDataSource) {

    fun getBanks() = dataSource.retrieveBanks()
    fun getBank(accountNumber: String): Bank =dataSource.retrieveBank(accountNumber)
    fun add(bank: Bank): Bank = dataSource.createBank(bank)

}