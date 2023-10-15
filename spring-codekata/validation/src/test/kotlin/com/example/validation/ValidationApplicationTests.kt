package com.example.validation

import jakarta.validation.ConstraintViolation
import jakarta.validation.Validation
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.validation.DefaultMessageCodesResolver

@SpringBootTest
@AutoConfigureMockMvc
class ValidationApplicationTests{
    @Autowired
    lateinit var mockMvc: MockMvc


    val messageCodesResolver=DefaultMessageCodesResolver()

    /**
     * MessageCodesResolver는 메시지 코드를 생성하는 역할을 합니다.
     * resolveMessageCodes 메서드는 주어진 아규먼트로 error.properties 파일에서 조회할 메시지 코드를 생성합니다.
     * 생성된 메시지 코드를 반환할 때는 가장 구체적인 메시지 코드 부터 순서대로 반환합니다.
     * 아래와 같이 에러코드, 객체명을 기입할 경우 "required.item","required" 를 반환합니다.
     *
     * 자세한 메시지 코드 생성 로직은 {@link DefaultMessageCodesResolver} 클래스에서 확인 가능합니다.
     */
    @Test
    fun getMessageByObject() {
        val resolveMessageCodes: Array<String> = messageCodesResolver.resolveMessageCodes("required", "item")
        assertThat(resolveMessageCodes).containsExactly("required.item","required")
    }

    /**
     * 아래와 같이 필드명과 타입까지 명시할 경우 이를 통해 생성 가능한 메시지 코드를 반환합니다.
     */
    @Test
    fun getMessageByField(){
        val resolveMessageCodes: Array<String> = messageCodesResolver.resolveMessageCodes("required", "item","itemName",
            String::class.java
        )
        assertThat(resolveMessageCodes).containsExactly(
            "required.item.itemName",
            "required.itemName",
            "required.java.lang.String",
            "required"
        )

    }

    @Test
    fun v1Test(){
        val item=Item("itemA",100000,10)
        mockMvc.post("/v1?${item.toParamString()}").andExpect {
            content {
                status { isBadRequest() }
            }
        }
    }

    @Test
    fun typeMisMatchTest(){
        val itemName="itemA"
        val price="10000000as"
        val quantity="100ass"
        mockMvc.post("/v1?itemName=$itemName&price=$price&quantity=$quantity").andExpect {
            content {
                status { isBadRequest() }
                string("")
            }
        }
    }

    @Test
    fun beanValidation(){
        val validator = Validation.buildDefaultValidatorFactory().validator
        val validate: MutableSet<ConstraintViolation<Item>> = validator.validate(Item("", 0, 10000))
        for (constraintViolation in validate) {
            println("constraintViolation = ${constraintViolation}")
            println("message = ${constraintViolation.message}")
        }

    }
}
