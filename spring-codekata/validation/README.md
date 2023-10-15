# Spring Validation
스프링 프레임워크에서 데이터의 유효성을 검증하기 위한 기능 예시입니다.

## 1. 사전 준비
스프링 검증 관련 모듈을 의존성에 추가합니다.
```kotlin
dependeencies{
    implementation("org.springframework.boot:spring-boot-starter-validation")
}
```

## 2. 에러 메시지 리소스
에러 메시지와 같은 정적 String 데이터들은 정적 리소스로 관리하는 것이 좋습니다.
`./src/resource` 디렉토리 하위에 `error.properties` 파일을 추가합니다.
에러 메시지는 아래와 같이 단계별로 설정할 수 있습니다. 단계를 선택하는 순서는 
`DefaultMessageCodesResolver` 클래스를 참고해주세요.
```properties
#1
required.item.itemName=상품 이름은 필수 입니다.
#2
required.item=상품 정보는 필수입니다.
#3
required.java.lang.String=문자 정보를 입력해야합니다.
#4
required=필수 값입니다.
```
## 3. Bean Validation
스프링에서는 어노테이션을 이용해 검증 로직을 간단하게 명시할 수 있스빈다.
### 3.1 Field Validation
아래와 같은 간단한 검증 로직의 경우 어노테이션을 활용한 Bean Validation을 이용해 쉽게 구현할 수 있습니다.
1. NotNull, 비어있으면 안되는 값
2. Max, 최대값
3. Range, 범위 지정
```kotlin
data class Item(
    @NotBlank
    val itemName: String,
    @NotNull
    @Range(min=1000,max=10000)
    val price: Int,
    @NotNull
    @Max(9999)
    val quantity: Int
)
```
> 자세한 설명은 https://hibernate.org/validator/ 참고
> 
> 어노테이션 모음은 https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-builtin-constraints 참고
> , 처음에 추가한 의존성에는 `hibernate validater` 모듈도 포함되어 있어 링크에 있는 모든 어노테이션 사용 가능
### 3.2 Object Validation
`가격과 수량의 곱이 1000000이상이면 안된다.`와 같은 조합 검증 로직은 이전에 사용한 Field Validation을
사용하기 어렵습니다. 이는 `@ScriptAssert` 어노테이션을 이용해 해결할 수 있습니다. 
```kotlin
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
)
```
그러나, 이 기능은 외부에서 가져온 값과 비교한다는 등 복잡한 검증 로직에 적용하기는 어렵습니다.
## 4. Validator 인터페이스
`가격과 수량의 곱이 데이터베이스에 저장된 최대 총합 정보 이상이면 안된다.`와 같은 복잡한 검증 로직은 `Validator` 인터페이스를 활용해 정의할 수 있습니다.
`validate` 메서드 내부에 검증 로직에 대해서 정의합니다. 필드 에러는`rejectValue`, 객체 에러는 `reject` 메서드로
에러 클래스에 추가할 수 있습니다. 그럼 추후, 에러 정보에 있는 객체, 필드 값을 토대로 `error.properties` 파일에서
우선 순위에 따라 예외 메시지를 로드 할 수 있습니다. 

```kotlin
data class Item(val itemName: String, val price: Int,val quantity: Int){
    
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
```

정의한 `Validator` 클래스는 글로벌 또는 각 컨트롤러 별로 등록할 수 있습니다.

**Controller**
```kotlin
@RestController
class SampleController {
    @InitBinder
    fun initBind(dataBinder: DataBinder){
        dataBinder.addValidators(Item.ItemValidator())
    }

}
```
**Global**
> 글로벌로 처리할 경우 스프링의 기본 Validator 가 등록되지 않으므로 주의해야합니다.
```kotlin
@Configuration
class GlobalValidatorConfiguration : WebMvcConfigurer{
    override fun getValidator(): Validator? {
        return Item.ItemValidator()
    }
}
```

## 5. API 예외 처리
스프링에서 `@RestController`를 사용하면 기본적으로 400 에러코드와 
빈 메시지를 반환합니다. 그러나 형식이 잘못된 경우 왜 형식이 잘못되었는지, 
올바른 형식은 무엇인지 메시지에 명시를 해주는 것이 좋습니다. 

Bean Validation에서 타입 유효성이 잚못된 것을 확인하면 `MethodArgumentNotValidException`
예외가 발생합니다. 

스프링에서는 예외가 발생했을 때 `ExceptionHandler`를 이용해 예외를 처리할 수 있으면,
예외 처리 결과를 `RestControllerAdvice`를 이용해서 응답 메시지로 반환할 수 있습니다.

또한, 예외 메시지는 `MessageSource`를 이용해 `error.properties`, `message.properties` 에서 
메시지를 가져와 반환할 수 있습니다.

이 기능들을 조합하면, 타입 예외가 발생했을 때, 이에 해당하는 예외 메시지를 모두 가져와
사용자에게 반환할 수 있습니다.
```kotlin
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
```

**예시 결과**
```
           Status = 400
    Error message = null
          Headers = [Content-Type:"application/json"]
     Content type = application/json
             Body = {"quantity":"\"숫자를 입력해야합니다\"","price":"\"숫자를 입력해야합니다\""}
    Forwarded URL = null
   Redirected URL = null
          Cookies = []
```