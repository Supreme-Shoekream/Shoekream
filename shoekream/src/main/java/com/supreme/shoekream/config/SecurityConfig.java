package com.supreme.shoekream.config;

import com.supreme.shoekream.model.dto.MemberDTO;
import com.supreme.shoekream.model.enumclass.Status;
import com.supreme.shoekream.model.network.security.KakaoOAuth2Response;
import com.supreme.shoekream.model.network.security.KreamPrincipal;
import com.supreme.shoekream.repository.MemberRepository;
import com.supreme.shoekream.service.MemberApiLogicService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

import java.util.UUID;

@Configuration
public class SecurityConfig{



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http
                , OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService
    ) throws Exception {
        http.csrf().disable();
        return http
                .authorizeRequests(auth -> auth.antMatchers("/login").permitAll()
                        .mvcMatchers("/",
                                "https://kauth.kakao.com/oauth/authorize/**",
                                "/login",
                                "/login/**",
                                "/loginOk",
                                "/join",
                                "/api/**",
                                "/find",
                                "/check/**",
                                "/product/**",
                                "/brand/**",
                                "/searchs/**",
                                "/social",
                                "/social/newest",
                                "/social/hashtag/**",
                                "/social/details",
                                "/joinOk",
                                "/notice/**",
                                "/faq",
                                "/auth_policy",
                                "/images/**"
                        ).permitAll()
                   .anyRequest().authenticated()
                )
                .formLogin()
                .loginPage("/login")            // 사용자 정의 로그인 페이지
                    .defaultSuccessUrl("/")            // 로그인 성공 후 이동 페이지
                    .failureUrl("/login.html?error=true")            // 로그인 실패 후 이동 페이지
                    .usernameParameter("email")            // 아이디 파라미터명 설정
                    .passwordParameter("memberPw")            // 패스워드 파라미터명 설정
                    .loginProcessingUrl("/loginOk")            // 로그인 Form Action Url
                    .successHandler(new CustomLoginSuccessHandler())		// 로그인 성공 후 핸들러
    //              .failureHandler(loginFailureHandler())		// 로그인 실패 후 핸들러
                    .and()
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .oauth2Login(oAuth -> oAuth
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oAuth2UserService)
                        )
                        .successHandler(new CustomLoginSuccessHandler())
                ).oauth2Login().loginPage("/login")
                .and()
                .build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/js/**", "/img/**", "/css/**","/lib/**");
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
                .map(KreamPrincipal::fromFull)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다 - email" + email));
    }

    //세션설정 spring이 로그인 할때 자동으로 생성
//    protected void configure(HttpSecurity http) throws Exception {
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .sessionFixation().changeSessionId();
//    }

    /**
     * <p>
     * OAuth 2.0 기술을 이용한 인증 정보를 처리한다.
     * 카카오 인증 방식을 선택.
     *
     * <p>
     * TODO: 카카오 도메인에 결합되어 있는 코드. 확장을 고려하면 별도 인증 처리 서비스 클래스로 분리하는 것이 좋지만, 현재 다른 OAuth 인증 플랫폼을 사용할 예정이 없어 이렇게 마무리한다.
     *
     * @param memberApiLogicService  게시판 서비스의 사용자 계정을 다루는 서비스 로직
     * @param passwordEncoder 패스워드 암호화 도구
     * @return {@link OAuth2UserService} OAuth2 인증 사용자 정보를 읽어들이고 처리하는 서비스 인스턴스 반환
     */
    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService(
            MemberApiLogicService memberApiLogicService,
            MemberRepository memberRepository,
            PasswordEncoder passwordEncoder
    ) {
        final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

        return userRequest -> {
            OAuth2User oAuth2User = delegate.loadUser(userRequest);

            KakaoOAuth2Response kakaoResponse = KakaoOAuth2Response.from(oAuth2User.getAttributes());
            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            String providerId = String.valueOf(kakaoResponse.id());
//            String username = registrationId + "_" + providerId;
            String username = kakaoResponse.email().split("@")[0];
            String email = kakaoResponse.email();
            String dummyPassword = passwordEncoder.encode("{bcrypt}" + UUID.randomUUID());
            String imgUrl = "/img/styleImg/empty_profile_img.png";

            return memberRepository.findByEmail(email)
                    .map(MemberDTO::fromEntity)
                    .map(KreamPrincipal::fromFull)
                    .orElseGet(() ->
                            KreamPrincipal.fromFull(//memberDTO
                                    memberApiLogicService.saveUser(
                                            username,
                                            dummyPassword,
                                            kakaoResponse.nickname(),
                                            null,
                                            email,
                                            Status.MEMBER,
                                            null,
                                            imgUrl
                                    )
                            )
                    );
        };
    }

}