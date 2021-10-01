package com.sample.springsecurityissue.interceptor

import org.aopalliance.aop.Advice
import org.springframework.aop.Pointcut
import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component


@Component
class ExceptionAnnotationPostProcessor(
    private val throwExceptionInterceptor: ThrowExceptionInterceptor
) : AbstractBeanFactoryAwareAdvisingPostProcessor(), InitializingBean {

    override fun afterPropertiesSet() {
        this.isProxyTargetClass = true
        val advice: Advice = throwExceptionInterceptor
        val pointcut: Pointcut = AnnotationMatchingPointcut(null, ThrowException::class.java)
        advisor = DefaultPointcutAdvisor(pointcut, advice)
    }
}