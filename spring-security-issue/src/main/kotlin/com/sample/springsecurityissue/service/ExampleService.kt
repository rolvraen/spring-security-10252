package com.sample.springsecurityissue.service

import com.sample.springsecurityissue.api.ExampleDto
import com.sample.springsecurityissue.interceptor.ThrowException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class ExampleService {

    @PreAuthorize("hasAuthority('SOME.ROLE')")
    @ThrowException
    suspend fun createExample(request: ExampleDto) : ExampleDto{
        return ExampleDto(value = "Hello world")
    }

    @PreAuthorize("hasAuthority('SOME.ROLE')")
    @ThrowException
     fun createExampleReturnFlow(request: ExampleDto) : Flow<ExampleDto> {
        return flow {
            emit(ExampleDto(value = "Hello world"))
        }
    }
}