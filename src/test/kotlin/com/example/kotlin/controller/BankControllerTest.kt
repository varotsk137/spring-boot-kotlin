package com.example.kotlin.controller

import com.example.kotlin.model.entity.Bank
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.*

private const val s = "/api/banks"

@SpringBootTest // Started application-context (started spring boot app to test)
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor( // Inject dependency in constructor
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {


    val baseUrl = "/api/banks"

    @Nested
    @DisplayName("GET /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBanks {

        @Test
        fun `should return all banks`() {
            // when/then
            mockMvc.get("$baseUrl")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber") { value("1234") }
                }
        }

    }

    @Nested
    @DisplayName("GET /api/banks/{accountNumber}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBank {

        @Test
        fun `should return the bank with the given account number`() {
            // given
            val accountNumber = 1234

            // when / then
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }

                .andExpect {
                    status { isOk() }
                    content { contentType(APPLICATION_JSON) }
                    jsonPath("$.trust") { value(3.14) }
                    jsonPath("$.transactionFee") { value(1) }
                }
        }

        @Test
        fun `should return NOT_FOUND when account number does not exist`() {
            // given
            val accountNumber = "does_not_exist"

            // when / then
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }

                .andExpect {
                    status { isNotFound() }
                }
        }
    }

    @Nested
    @DisplayName("POST /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostNewBank {

        @Test
        fun `should add the new bank`() {
            // given
            val newBank = Bank("4321", 31.11, 4)

            // when
            val performPost = mockMvc.post(baseUrl) {
                contentType = APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }

            // then
            performPost.andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(newBank))
//                jsonPath("$.accountNumber") { value("4321") }
//                jsonPath("$.trust") { value(31.11) }
//                jsonPath("$.transactionFee") { value(4) }
                    }
                }

            mockMvc.get("$baseUrl/${newBank.accountNumber}")
                .andExpect { content { json(objectMapper.writeValueAsString(newBank)) } }
        }

        @Test
        fun `should return BAD REQUEST when bank with given account number already already exist`() {
            // given
            val invalidBank = Bank("4321", 1.2333, 5)

            // when
            val performPost = mockMvc.post(baseUrl) {
                contentType = APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }

            // then
            performPost.andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }
        }
    }

    @Nested
    @DisplayName("PATCH /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PatchExistingBank {

        @Test
        fun `should update an existing bank`() {
            // given
            val updatedBank = Bank("1234", 1.0, 25)

            // when
            val performPatch = mockMvc.patch(baseUrl) {
                contentType = APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedBank)
            }

            // then
            performPatch.andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updatedBank))
                    }
                }

            mockMvc.get("$baseUrl/${updatedBank.accountNumber}")
                .andExpect { content { json(objectMapper.writeValueAsString(updatedBank)) } }
        }

        @Test
        fun `should return NOT FOUND if bank with given account number does not exist`() {
            // given
            val invalidBank = Bank("does not exist", 1.0, 1)

            // when
            val performPatch = mockMvc.patch(baseUrl) {
                contentType = APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }

            // then
            performPatch.andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }

    @Nested
    @DisplayName("DELETE /api/banks/{accountNumber}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class DeleteExistingBank {

        @Test
        fun `should delete bank with the given account number`() {
            // given
            val accountNumber = "1234"

            // when
            mockMvc.delete("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isNoContent() }
                }

            mockMvc.get("$baseUrl/${accountNumber}")
                .andExpect { status { isNotFound() } }
        }

        @Test
        fun `should return NOT FOUND if bank with given account number does not exist`() {
            // given
            val invalidAccountNumber = "does not exist"

            // when / then
            mockMvc.delete("$baseUrl/$invalidAccountNumber")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }
}
