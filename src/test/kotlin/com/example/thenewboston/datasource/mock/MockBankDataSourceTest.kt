package com.example.thenewboston.datasource.mock

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class MockBankDataSourceTest{

    private val mockDataSource = MockBankDataSource();

    @Test
    fun `should provide a collection of banks`(){
         //given


         //when

        val banks = mockDataSource.retrieveBanks()

         //then

//all satisfy u put multiple assertions
        assertThat(banks).allSatisfy{
            assertThat(banks).isNotEmpty()
            assertThat(banks).isNotEmpty()}
    }

    @Test
    fun `should provide mock data`(){
         //given


         //when
        val banks = mockDataSource.retrieveBanks()

         //then
        //this one is testing nothing
        //assertThat(banks).allSatisfy{it.accountNumber.isNotBlank()}

        assertThat(banks).allMatch{it.accountNumber.isNotBlank()}

        assertThat(banks).allSatisfy{
            assertThat(banks).allMatch{it.accountNumber.isNotBlank()}
            assertThat(banks).anyMatch{it.trust != 0.0}
            assertThat(banks).anyMatch{it.transactionFee != 0}
        }

    }



}