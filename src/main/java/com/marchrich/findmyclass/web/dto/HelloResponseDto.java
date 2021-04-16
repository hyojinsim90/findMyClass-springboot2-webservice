package com.marchrich.findmyclass.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 롬복을 설치하면 아래처럼 원래 변수 선언해고 해줬던 긴 getter,setter와 생성자를 적어주지 않고 이렇게 annotationi으로 쓸수있다
@Getter // 선언된 모든 필드의 get메소드를 생성해준다 - 예전에 getter setter하던거
@RequiredArgsConstructor // 선언된 모든 final필드가 포함된 생성자를 생성해 준다. - 예전에 생성자 선언해준거
public class HelloResponseDto {

    private final String name;
    private final int amount;
}
