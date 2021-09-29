package com.ernez.craftapp.security;

import com.ernez.craftapp.security.jwt.AuthEntryPointJwt;
import com.ernez.craftapp.security.jwt.AuthTokenFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
         securedEnabled = true,
         jsr250Enabled = true,
        prePostEnabled = true)
@Slf4j
@Profile("!test")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, AuthEntryPointJwt unauthorizedHandler) {
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll();
    }


    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers("/**");
        log.info("Authentication Configurer started in development mode. All the security is disabled.");
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



//    @Bean
//    @Profile("!dev")
//    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
//        return new WebSecurityConfigurerAdapter() {
//            @Override
//            public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//                authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//            }
//
//            @Bean
//            @Override
//            public AuthenticationManager authenticationManagerBean() throws Exception {
//                return super.authenticationManagerBean();
//            }
//
//            @Override
//            protected void configure(HttpSecurity httpSecurity) throws Exception {
//                httpSecurity
////                .csrf(c -> c
////                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
////                )
//                        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                .authorizeRequests().antMatchers("*/**").permitAll()
//                        .and().authorizeRequests().antMatchers("/h2-console/**", "/api/**").permitAll()
//                        .anyRequest().authenticated()//all other urls can be access by any authenticated role
////                .and().formLogin()//enable form login instead of basic login
////                .and().csrf().ignoringAntMatchers("/h2-console/**")//don't apply CSRF protection to /h2-console
//                        .and().csrf().disable()
//                        .headers().frameOptions().sameOrigin();//allow use of frame to same origin urls
//
//                // CORS configuration
//
//                // This value must be parameterized according to your application needs
//                final String corsOrigin="http://localhost:8080";
//                // The idea is to insert the CORS filter before the filter injected by
//                // the @EnableOAuth2Sso annotation
//                httpSecurity.addFilterBefore(new CorsFilter(corsConfigurationSource(corsOrigin)), AbstractPreAuthenticatedProcessingFilter.class);
//            }
//        };
//
//    }

//    @Bean
//    @Profile("dev")
//    public WebSecurityConfigurerAdapter developmentWebSecurityConfigurerAdapter() {
//        return new WebSecurityConfigurerAdapter() {
//            @Bean
//            @Override
//            public AuthenticationManager authenticationManagerBean() throws Exception {
//                return super.authenticationManagerBean();
//            }
//
//            @Override
//            public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//                authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//            }
//
//            @Override
//            protected void configure(HttpSecurity http) throws Exception {
//                http
//                        .csrf().disable()
//                        .authorizeRequests()
//                        .antMatchers("/**").permitAll();
//            }
//
//
//            @Override
//            public void configure(WebSecurity webSecurity) {
//                webSecurity.ignoring().antMatchers("/**");
//                log.info("Authentication Configurer started in development mode. All the security is disabled.");
//            }
//        };
//    }

    private CorsConfigurationSource corsConfigurationSource(String corsOrigin) {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(corsOrigin));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","HEAD","OPTIONS","PUT","PATCH","DELETE"));
        configuration.setMaxAge(10L);
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Accept","Access-Control-Request-Method","Access-Control-Request-Headers", "Access-Control-Allow-Origin",
                "Accept-Language","Authorization","Content-Type","Request-Name","Request-Surname","Origin","X-Request-AppVersion",
                "X-Request-OsVersion", "X-Request-Device", "X-Requested-With"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
