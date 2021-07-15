package me.silvernine.tutorial.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// 기본적인 Web 보안을 활성화 어노테이션
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /** 설명: configure(WebSecurity web)
     * h2-console 하위 모든 요청들과 파비콘 관련 요청은 Spring Security 로직을 수행하지 않도록 내용을 추가.
     * /h2-console/** 에 대한 요청은 Security filter chain을 적용할 필요가 전혀 없는 요청이여서 ignoring 함.
     * permitAll()로 해도 문제가 없지만 의미나 로직상 ignoring 쪽이 더 적합하다고 생각.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(
                        "/h2-console/**"
                        ,"/favicon.ico"
                );
    }

    /** 설명: configure(HttpSecurity http)
     *   authorizeRequests()는 httpServletRequest를 사용하는 요청들에 대한 접근제한을 설정하겠다는 의미
     *   antMatchers(path).permitAll() : /api/hello에 대한 요청은 인증없이 접근을 허용하겠다는 의미입니다.
     *   anyRequest().authenticated() : 나머지 요청들은 모두 인증되어야 한다는 의미
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/hello").permitAll()
                .anyRequest().authenticated();
    }



}
