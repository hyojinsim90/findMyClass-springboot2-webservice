package com.marchrich.findmyclass.config;

import com.marchrich.findmyclass.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/* LoginUserArgumentResolver가 스프링에서 인식될 수 있도록 WebMvcConfigurer 에 추가 */
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolverList) {
        argumentResolverList.add(loginUserArgumentResolver); // 다른 HandlerMethodArgumentResolver를 추가하고 싶을 때도 같은 방식으로 추가하면 됨
    }
}
