package com.example.validation

import jakarta.validation.Valid
import jakarta.validation.constraints.*
import org.hibernate.validator.constraints.Range
import org.hibernate.validator.constraints.ScriptAssert
import org.jetbrains.annotations.NotNull
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
import org.springframework.validation.DataBinder
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import org.springframework.validation.annotation.Validated
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@ScriptAssert(lang = "javascript", script = "_this.price *_this.quantity<=100000", message = "총합이 100000 이상은 허용되지 않습니다.")
data class Item(
    @field: NotBlank
    val itemName: String,
    @field:NotNull
    @field: Range(min=1000,max=10000)
    val price: Int,
    @field: NotNull
    @field: Max(9999)
    val quantity: Int
){
    fun toParamString(): String{
        return "itemName=$itemName&price=$price&quantity=$quantity"
    }

    class Test{
        class ItemValidator: Validator{
            override fun supports(clazz: Class<*>): Boolean {
                return Item::class.java.isAssignableFrom(clazz)
            }

            override fun validate(target: Any, errors: Errors) {
                if((target as Item).price>10000){
                    errors.rejectValue("price","required")
                }
            }

        }
    }
}

@RestController
class SampleController {
    @InitBinder
    fun initBind(dataBinder: DataBinder){
        dataBinder.addValidators(Item.Test.ItemValidator())
    }

    @PostMapping("/v1")
    fun post(@Validated @ModelAttribute item: Item){

    }
}