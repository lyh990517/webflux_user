package com.yunho.webfluxsample2.auth

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import java.security.Principal

class Authentication(private val name: String) : Authentication {
    override fun getName(): String = name

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? = null

    override fun getCredentials(): Any? = null

    override fun getDetails(): Any? = null

    override fun getPrincipal(): Any = object : Principal{
        override fun getName(): String = name
    }

    override fun isAuthenticated(): Boolean = true

    override fun setAuthenticated(isAuthenticated: Boolean) {
    }
}