package com.marchrich.findmyclass.config.auth;

import com.marchrich.findmyclass.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/* config.auth 패키지 : 시큐리리 관련 클래스를 이곳에 담음 */
@RequiredArgsConstructor
@EnableWebSecurity // 스프링 시큐리티 설정들을 활성화시켜줌
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 스프링 시큐리티의 설정

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**",
                        "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
            .and()
                .logout()
                    .logoutSuccessUrl("/")
            .and()
                .oauth2Login()
                    .userInfoEndpoint()
                        .userService(customOAuth2UserService);

    }
/*
*  .csrf().disable().headers().frameOptions().disable() :  h2-console 화면을 사용하기 위해 해당 옵션들을 disable함
*
*  authorizeRequests: URL별 권한 관리를 설정하는 옵션의 시작점.antMatchers를 쓰기 전에 선언되야함
*
*  antMatchers: 권한 관리 대상 지정. URL, HTTP메소드 별로 관리 가능. '/'등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 권한을 준다
*
*  anyRequest: 설정된 값들 이외 나머지 URL들. 뒤에 authenticated가 붙으면 이 나머지 URL들은 모두 인증된 사용자들(=로그인한 사용자)에게만 허용
*
*  .logout(): 로그아웃 기능에 대한 여러 설정의 진입점. 
* 
*  .logout().logoutSuccessUrl("/"): 로그아웃 성공 시 /주소로 이동
* 
*  oauth2Login: OAuth2 로그인 기능에 대한 여러 설정의 진입점.
* 
*  userInfoEndpoint: OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정을 담당
* 
*  userService: 소셜 로그인 성공 시 후속 조치를 진행할 서비스 인터페이스의 구현체를 등록함
*               소셜 서비스들에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할수 있음
* 
* */


}
