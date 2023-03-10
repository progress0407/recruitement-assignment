package com.your.lol.acceptance

import com.your.lol.support.GlobalTestUtils
import com.your.lol.infrastructure.WebClientFacade
import io.restassured.RestAssured
import io.restassured.response.ValidatableResponse
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AcceptanceTest : GlobalTestUtils() {

    @MockBean
    protected lateinit var webClientFacade: WebClientFacade

    @LocalServerPort
    protected var port: Int = 0

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
    }

    protected operator fun get(uri: String?): ValidatableResponse? {
        return RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .`when`()[uri]
                .then().log().all()
    }
}