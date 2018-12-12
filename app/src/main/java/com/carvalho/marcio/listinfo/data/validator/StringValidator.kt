package com.carvalho.marcio.listinfo.data.validator

enum class StringValidator {

    VALID_CPF {
         fun validate(value: CharSequence): Boolean {
            return false
        }
    },

}