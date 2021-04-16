package com.marchrich.findmyclass.web;

import com.marchrich.findmyclass.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.*;


@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        // requestParam : 외부에서 api로 넘긴 파라미터를 가져오는 어노테이션. @RequestParam("name") 이란 이름으로 넘긴 파라미터를  String name에 저장한다
        return new HelloResponseDto(name, amount);
    }

}
/*
* @RestController
* 컨트롤러를 json을 반환하는 컨트롤러로 만들어 줌
* 예전에는 @responseBody를 각 메소드마다 선언했지만, 이제는 RestController로 한번에 사용
*
* @GetMapping
* HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어줌
* 예전에는 @RequestMapping(method = RequestMethod.GET)으로 사용했었음
*
* */
