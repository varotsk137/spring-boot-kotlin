package com.example.kotlin.adaptor

import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

abstract class BaseAdaptor {

    abstract fun getRestTemplate(): RestTemplate

    fun <T> exchange(
        url: String,
        httpMethod: HttpMethod,
        httpEntity: HttpEntity<T>,
        responseClass: Class<T>
    ): ResponseEntity<T> {
        return getRestTemplate().exchange(url, httpMethod, httpEntity, responseClass)
    }

    fun <T> get(url: String, responseClass: Class<T>): ResponseEntity<T> {
        return getRestTemplate().getForEntity(url, responseClass)
    }
}