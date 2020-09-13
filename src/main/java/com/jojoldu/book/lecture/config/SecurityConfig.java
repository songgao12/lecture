package com.jojoldu.book.lecture.config;

import com.jojoldu.book.lecture.config.service.AuthProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthProvider authProvider;
    @Override
    public void configure(WebSecurity web) { // 5
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 페이지 권한 설정
                .antMatchers("/posts/login").permitAll()
                .antMatchers("/**").hasRole("USER")
                .and() // 로그인 설정
                .formLogin()
                .loginPage("/posts/login")
                .defaultSuccessUrl("/")
                .and()
                .authenticationProvider(authProvider)
                .csrf().disable();
    }
}
