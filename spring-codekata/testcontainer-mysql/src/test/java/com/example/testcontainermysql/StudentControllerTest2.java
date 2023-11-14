package com.example.testcontainermysql;

import com.example.testcontainermysql.domain.Student;
import com.example.testcontainermysql.domain.StudentRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

/**
 * 1. 통합 테스트를 위해 임의의 포트 번호를 할당한 웹 어플리케이션을 실행합니다.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest2 {

    //현재 서버의 포트 번호
    @LocalServerPort
    private Integer port;

    /**
     * 2. 도커 이미지 명을 명시해 컨테이너를 정의합니다.
     */
    static MySQLContainer<?> mysql = new MySQLContainer<>(
            "mysql:8.0.35"
    ).withReuse(true);


    @BeforeAll
    public static void beforeAll() {
        mysql.start();
    }
    /**
     * 5. 통합 테스트에서 동적으로 데이터베이스 관련 프로퍼티를 정의합니다. 이를 통해, JPA가 어떤 데이터베이스 연결 정보를 알 수 있습니다.
     * @param registry
     */
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Autowired
    StudentRepository studentRepository;

    /**
     * 6. 각 테스트 수행 전, 학생 테이블을 초기화합니다.
     */
    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        studentRepository.deleteAll();
    }

    /**
     * 7. 예시 데이터를 입력 후, API 테스트를 수행합니다.
     */
    @Test
    void shouldGetAllStudents() {
        List<Student> customers = List.of(
                new Student("foo", 11, "john@mail.com"),
                new Student("bar", 12, "dennis@mail.com")
        );
        studentRepository.saveAll(customers);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/student/all")
                .then()
                .statusCode(200)
                .body(".", hasSize(2));
    }
}
