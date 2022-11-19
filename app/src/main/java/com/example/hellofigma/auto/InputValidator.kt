package com.example.hellofigma.auto

import com.example.hellofigma.R
object InputValidator {
    fun getNameErrorIdOrNull(input: String): Int? {
        return when {
            input.length < 2 -> R.string.name_too_short
            //etc..
            else -> null
        }
    }
}