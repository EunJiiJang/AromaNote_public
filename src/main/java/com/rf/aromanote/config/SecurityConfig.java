package com.rf.aromanote.config;

import com.rf.aromanote.config.jwt.JwtProperties;
import com.rf.aromanote.config.jwt.JwtProvider;
import com.rf.aromanote.config.securoty.JwtAuthenticationFilter;
import com.rf.aromanote.config.jwt.JwtAuthorizationFilter;
import com.rf.aromanote.config.securoty.handlers.LoginFailureHandler;
import com.rf.aromanote.config.securoty.handlers.LoginSuccessHandler;
import com.rf.aromanote.repository.core.login.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CorsConfig corsConfig;

    // JWT 제공 클래스
    private final JwtProvider jwtProvider;
    // 인증 실패 또는 인증헤더가 전달받지 못했을때 핸들러
    private final AuthenticationEntryPoint authenticationEntryPoint;
    // 인증 성공 핸들러
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    // 인증 실패 핸들러
    private final AuthenticationFailureHandler authenticationFailureHandler;
    // 인가 실패 핸들러
    private final AccessDeniedHandler accessDeniedHandler;


    @Bean
    // BCryptPasswordEncoder는 Spring Security에서 제공하는 비밀번호 암호화 객체입니다.
    // Service에서 비밀번호를 암호화할 수 있도록 Bean으로 등록합니다.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //어드민,멤버라는 로그인 이후 접근가능한 url접근시 auth체크
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .addFilter(corsConfig.corsFilter())
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// 토큰 기반 인증이므로 세션 역시 사용하지 않습니다.
                .and()
                    .httpBasic().disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .accessDeniedHandler(accessDeniedHandler)
                .and()
                    .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(jwtFilter(),UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()// 요청에 대한 사용권한 체크
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/member/**").hasRole("MEMBER")
                    .antMatchers("/login/**").permitAll()
                    .anyRequest().permitAll()// 그외 나머지 요청은 누구나 접근 가능
                .and()
                    .formLogin()
                    .loginPage("/login/adminLogin")
                    .loginProcessingUrl("/login_proc")
                    //.defaultSuccessUrl("/admin/main")
                .and()
                    .logout()
                    .logoutUrl("/login/logout")
                    .deleteCookies(JwtProperties.HEADER_STRING)
                .and()
                .csrf().disable();//csrf 보안 토큰 disable처리.
    }

    @Override
    public void configure(WebSecurity web) throws Exception { // Spring Security가 인증을 무시할 경로 설정
        web.ignoring().antMatchers("/css/**", "/img/**", "/js/**", "/lib/**", "/vendor/**");
    }



    /**
     * 사용자 요청 정보로 UserPasswordAuthenticationToken 발급하는 필터
     */
    @Bean
    public JwtAuthenticationFilter authenticationFilter() throws Exception {
        JwtAuthenticationFilter customAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
        // 필터 URL 설정
        customAuthenticationFilter.setFilterProcessesUrl("/login_proc");
        // 인증 성공 핸들러
        customAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        // 인증 실패 핸들러
        customAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        // BeanFactory에 의해 모든 property가 설정되고 난 뒤 실행
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }
    /**
     * JWT의 인증 및 권한을 확인하는 필터
     */
    @Bean
    public JwtAuthorizationFilter jwtFilter() {
        return new JwtAuthorizationFilter(jwtProvider);
    }

}
