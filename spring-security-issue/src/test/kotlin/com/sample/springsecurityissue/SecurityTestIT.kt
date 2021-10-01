package com.sample.springsecurityissue

import com.sample.springsecurityissue.api.ExampleDto
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters


@SpringBootTest
@AutoConfigureWebTestClient
class SecurityTestIT {

    @Autowired
    private lateinit var client: WebTestClient

    @Test
    @WithMockUser(authorities = ["SOME.ROLE"])
    fun `should throw exception`(){
        client.post().uri("/api/v1/example")
            .body(BodyInserters.fromValue(ExampleDto(value = "Hello world")))
            .exchange()
            .expectStatus().is5xxServerError
    }

    @Test
    @WithMockUser(authorities = ["SOME.ROLE"])
    fun `should also throw exception`(){
        client.post().uri("/api/v1/example/flow")
            .body(BodyInserters.fromValue(ExampleDto(value = "Hello world")))
            .exchange()
            .expectStatus().is5xxServerError
    }

    @Test
    @WithMockUser(authorities = ["NONE.ROLE"])
    fun `should return forbidden`(){
        client.post().uri("/api/v1/example")
            .body(BodyInserters.fromValue(ExampleDto(value = "Hello world")))
            .exchange()
            .expectStatus().isForbidden
    }
}