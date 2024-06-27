package com.example.thenewboston.controller

import com.example.thenewboston.model.Bank
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

private const val BASE_URL = "/api/banks"

@SpringBootTest
@AutoConfigureMockMvc
class BankControllerTest{

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

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

    @Test
    fun `should add new bank`(){
        //given
        val newBank = Bank("12345",35.12,45)

        //when
        val performPost = mockMvc.post(BASE_URL){
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newBank)
        }

        //then
        performPost
            .andDo { print() }
            .andExpect {
                status { isCreated() } //created is 201
                content { contentType(MediaType.APPLICATION_JSON) }// if u decide to return the bank created
                jsonPath("$.accountNumber"){value("12345")}
                jsonPath("$.trust"){value(35.12)}
                jsonPath("$.transactionFee"){value(45)}
            }


    }
    
    @Test
    fun `should return BAD REQUEST account that exists`(){
         //given
         val invalidBank = Bank("1234",1.0,1)

         //when
         val performPost = mockMvc.post(BASE_URL){
             contentType = MediaType.APPLICATION_JSON
             content = objectMapper.writeValueAsString(invalidBank)
         }
         
         //then
         performPost
             .andDo { print() }
             .andExpect { status { isBadRequest() } }
    }
}