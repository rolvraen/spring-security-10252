package com.sample.springsecurityissue.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import org.springframework.security.web.server.savedrequest.NoOpServerRequestCache

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig {

    @Bean
    fun springSecurityFilterChain(
        http: ServerHttpSecurity
    ): SecurityWebFilterChain {
        http
            .requestCache().requestCache(NoOpServerRequestCache.getInstance())
            .and()
            .csrf()
            .disable()
            .authorizeExchange()
            .anyExchange().authenticated()
            .and()
            .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
        return http.build()
    }
}