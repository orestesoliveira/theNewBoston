package com.example.thenewboston.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

private const val BASE_URL = "/api/banks"

@SpringBootTest
@AutoConfigureMockMvc
class BankControllerTest{

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `should $DESCRIPTION$`(){
         //when

            mockMvc.get("$BASE_URL")
                .andDo { print() }
                .andExpect {
                    status {
                        isOk()
                        content { contentType(MediaType.APPLICATION_JSON) }
                        jsonPath("$[0].accountNumber"){value ("abcdef")}
                    }
                }


    }

    @Test
    fun `should return the bank with the given account number`(){
        //given
        val accountNumber = 1234

        //when/then
        mockMvc.get("$BASE_URL/$accountNumber")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.trust"){value("3.14")}
            }


    }

    @Test
    fun `should return not fpund with the given account number that does not exist`(){
        //given
        val accountNumber = "account_number_that_does_not_exist"

        //when/then
        mockMvc.get("$BASE_URL/$accountNumber")
            .andDo { print() }
            .andExpect {
                status { isNotFound() }

            }


    }
}