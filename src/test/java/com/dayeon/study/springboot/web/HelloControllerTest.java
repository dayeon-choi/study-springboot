package com.dayeon.study.springboot.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HelloController.class) // 여러 스프링 어노테이션 중 Web(Spring MVC)에 집중할 수 있는 어노테이션(@Controller, @ControllerAdvice 사용 가능)
public class HelloControllerTest {
    @Autowired // 스프링이 관리하는 빈 주입
    private MockMvc mvc; // 웹 API를 테스트(HTTP GET, POST 등에 대한 테스트)할 때 사용, 스프링 MVC 테스트의 시작점

    @Test
    void hello가_리턴된다() throws Exception {
        // given
        String hello = "hello";

        // when
        mvc.perform(get("/hello")) // MockMVC를 통해 /hello 주소로 HTTP GET 요청, 체이닝 지원
                .andExpect(status().isOk()) // HTTP Header의 Status 검증 (200, 404, 500 상태)
                .andExpect(content().string(hello)); // mvc 결과, 응답 본문 내용 검증, Controller의 리턴 값 "hello"가 맞는지 검증
    }

    @Test
    void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)    // param: 요청 파라미터 설정, 값은 String만 허용 (숫자/날짜 데이터 등록 시 문자열 변경 필수)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))    // jsonPath: JSON 응답 값을 필드별로 검증할 수 있는 메서드, $를 기준으로 필드명 명시
                .andExpect(jsonPath("$.amount", is(amount))
        );
    }
}
