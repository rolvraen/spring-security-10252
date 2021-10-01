package com.sample.springsecurityissue.v1

import com.sample.springsecurityissue.api.ExampleDto
import com.sample.springsecurityissue.service.ExampleService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/example")
class ExampleController(private val exampleService: ExampleService) {

    @PostMapping()
    suspend fun createExampleEntity(
        @RequestBody
        request: ExampleDto
    ) = exampleService.createExample(request)

    @PostMapping("/flow")
    suspend fun createExampleEntityAndReturnFlow(
        @RequestBody
        request: ExampleDto
    ) = exampleService.createExampleReturnFlow(request)
}