package com.sample.springsecurityissue.interceptor

import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.springframework.stereotype.Component

@Component
class ThrowExceptionInterceptor : MethodInterceptor {
    override fun invoke(invocation: MethodInvocation): Any? {
        throw Exception("Interceptor doing it's thing")
    }
}