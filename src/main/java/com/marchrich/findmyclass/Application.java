package com.marchrich.findmyclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application { // 프로젝트의 메인 클래스

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		// SpringApplication.run : 내장 WAS를 실행함
		/* 내장 WAS를 쓰는 이유 :
		* 언제 어디서나 같은 환경에서 스프링 부트를 배포할수있다
		* 외장 WAS를 쓰면 여러대 서버에 설치된 WAS중 하나의 버전을 올리면 다 올려줘야 하는 문제가 이음
		* */
	}

}
