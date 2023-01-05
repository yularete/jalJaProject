package com.jalja.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/members/login") // 로그인 페이지 URL을 설정
                .defaultSuccessUrl("/")//로그인 성공 시 이동할 URL
                .usernameParameter("email")//로그인 시 사용할 파라미터 이름으로 email 지정
                .failureUrl("/members/login/error")//로그인 실패 시 이동할 URL
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))//로그아웃 URL
                .logoutSuccessUrl("/"); //로그아웃 성공 시 이동할 URL

        //시큐리티 처리에 HttpServletRequest를 이용한다는 것을 의미.
        http.authorizeRequests()
                .mvcMatchers("/","/members/**").permitAll()
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();

        http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        //BCryptPE의 해시 함수를 이용해 비번을 암호화하여 저장. //BCryptPE를 빈으로 등록해 사용
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
    //인증되지 않은 사용자가 리소스에 접근하였을 때 수행되는 핸들러를 등록
        return authConfiguration.getAuthenticationManager();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        //static 디렉터리 하위 파일들은 인증을 무시하도록 설정
        return (web) -> web.ignoring().antMatchers("/img/**", "/css/**", "/js/**");
    }


}
