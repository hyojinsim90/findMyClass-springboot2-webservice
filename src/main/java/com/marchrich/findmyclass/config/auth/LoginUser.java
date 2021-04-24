package com.marchrich.findmyclass.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/* 생성이유 : IndexController에서 세션값을 가져오는 코드를 반복해서 사용하는것은 불필요하므로 메소드 인자로 세션값을 바로 받을수 있도록 변경하기위해 */
@Target(ElementType.PARAMETER) // 이 어노테이션이 생성될 수 있는 위치는 PARAMETER. 메소드의 파라미터로 선언된 객체에서만 사용할 수 있음.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { // LoginUser라는 어노테이션이 생성되었다고 보면 됨
}
