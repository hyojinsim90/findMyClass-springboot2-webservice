package com.marchrich.findmyclass.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
        /* assertThat
         assertj라는 테스트 검증 라이브러리의 메소드
         검증하고 싶은 대상을 메소드 인자로 받음
         메소드 체이닝이 지원되어 isEqualTo와 같이 메소드를 이어서 사용할 수 있다.

         Junit이 아닌 assertJ를 쓰는 이유
         Junit에서 자동으로 라이브러리 등록을 해줌
         CoreMatchers와 달리 추가적으로 라이브러리가 필요하지 않음
         자동완성이 좀 더 확실하게 지원됨
         Mactcher 라이브러리 : 필터나 검색등을 위해 값을 비교할 때 좀 더 편리하게 사용하도록
                                    도와주는 라이브러리입니다.
         */
    }

}
