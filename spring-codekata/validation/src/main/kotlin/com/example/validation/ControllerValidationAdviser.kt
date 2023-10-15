package com.example.validation

import org.springframework.context.MessageSource
import org.springframework.context.NoSuchMessageException
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*
import kotlin.collections.HashMap


@RestControllerAdvice
class ControllerValidationAdviser(
    val messageSource: MessageSource
) {

    /**
     * @Valid 어노테이션으로 등록된 인자의 유효성이 잘못된 경우 발생하는 예외 처리
     *
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(methodArgumentNotValidException: MethodArgumentNotValidException): HashMap<String, String> {
        val hashMap = HashMap<String, String>()
        for(error in methodArgumentNotValidException.bindingResult.allErrors){
            val filedError=(error as FieldError)
            hashMap[filedError.field]=messageSource.getMessages(filedError) as String
        }
        return hashMap
    }

    private fun MessageSource.getMessages(fieldError: FieldError): String? {
        for(code in fieldError.codes!!){
            try {
                return messageSource.getMessage(code, null,Locale.getDefault())
            } catch (ignored: NoSuchMessageException) {
            }
        }
        return fieldError.defaultMessage
    }
}