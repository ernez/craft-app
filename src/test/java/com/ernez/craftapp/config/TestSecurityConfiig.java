package com.ernez.craftapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Profile("test")
@Configuration
public class TestSecurityConfiig extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(WebSecurity webSecurity) throws Exception {
            webSecurity.ignoring().antMatchers("/**");
        }
}
