package com.example.thenewboston.service

import com.example.thenewboston.datasource.BankDataSource
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

class BankServiceTest{


    private val dataSource:BankDataSource = Mockito.mock(BankDataSource::class.java)
    val bankService: BankService = BankService(dataSource)

@Test
fun `should call its data source to retrieve banks`(){
     //given

     //when
    var banks = bankService.getBanks()

     
     //then
    verify(dataSource, atMostOnce()).retrieveBanks()
}
}