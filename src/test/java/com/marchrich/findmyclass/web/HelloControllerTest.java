package com.marchrich.findmyclass.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) // 테스트를 진행할 때 Spring Runner 라는 스프링 실행자를 사용한다. 즉, 스프링 부트 테스트와 Junit사이에 연결자 역할을 한다.
@WebMvcTest(controllers = HelloController.class) // 선언하면 @Controller, @ControllerAdvice등을 사용할 수 있다. (@Service, @Component, @repository는 사용할 수 없다)
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈(Bean)을 주입받는다
    private MockMvc mvc; // 웹 api를 테스트할 때 사용한다. 스프링 mvc테스트의 시작. 이 클래스를 통해 get,post 등에 대한 http api테스트를 할 수 있다.

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc를 통해 /hello 주소로 HTTP GET요청을 한다
                .andExpect(status().isOk()) // mvc.perform의 결과 검증. HTTP Header의 Status를 검증한다. 여기서는 ok 즉 status가 200인지 아닌지 검증한다.
                .andExpect(content().string(hello)); // mvc.perform의 결과 검증. 응답 본문의 내용을 검증한다. Controller에서 "hello"를 리턴하기로했는데 이 값이 맞는지 검증한다.
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                        .param("name", name) // param : api테스트할때 사용될 요청 파라미터 설정
                        .param("amount", String.valueOf(amount))) // param의 값이 string만 허용되므로 숫자/날짜등의 데이터도 등록시에는 문장열로 변경한다.(받을땐 숫자/날짜로 받아도 되나봄)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // json응답값을 필드별로 검증할 수 있는 메소드. $를 기준으로 필드명을 명시한다.
                .andExpect(jsonPath("$.amount", is(amount))); // is 는 Matchers(값 비교 라이브러리)의 is
    }


}
