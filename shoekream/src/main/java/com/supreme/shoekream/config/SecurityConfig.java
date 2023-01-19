package com.supreme.shoekream.config;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        return http
                .authorizeRequests(auth -> auth.anyRequest().permitAll())
                .formLogin()
                .loginPage("/login")            // 사용자 정의 로그인 페이지
                    .defaultSuccessUrl("/")            // 로그인 성공 후 이동 페이지
                    .failureUrl("/login.html?error=true")            // 로그인 실패 후 이동 페이지
                    .usernameParameter("email")            // 아이디 파라미터명 설정
                    .passwordParameter("memberPw")            // 패스워드 파라미터명 설정
                    .loginProcessingUrl("/loginOk")            // 로그인 Form Action Url
    //                    .successHandler(loginSuccessHandler())		// 로그인 성공 후 핸들러
    //                    .failureHandler(loginFailureHandler())		// 로그인 실패 후 핸들러
                    .and()
                .logout()
                    .logoutSuccessUrl("/")      //로그아웃 페이지
                    .and()
                .build();
    }

//        return http
//            .authorizeRequests(auth -> auth.anyRequest().permitAll())
//            .formLogin().and()
//            .build();
//        }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(MemberRepository memberRepository){
        return email -> memberRepository.findByEmail(email)
                .map(MemberDTO::fromEntity)
                .map(KreamPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다 - email" + email));
    }

    //세션설정 spring이 로그인 할때 자동으로 생성
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().changeSessionId();
    }
}