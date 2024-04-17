package com.ram.buspass.utils

object Validators {
    fun isValidEmailAddress(email: String): Boolean {
        val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
        return email.trim().matches(emailRegex)
    }
}