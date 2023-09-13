package com.springldap.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Value("${spring.ldap.urls}")
    String ldapUrl;
    @Value("${spring.ldap.base}")
    String base;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                        .anyRequest().fullyAuthenticated())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userDnPatterns("CN={0},OU=Users,OU=Moscow,OU=Russia,OU=COMPANY",
                        "CN={0},OU=Terminated,OU=Service,OU=Moscow,OU=Russia,OU=COMPANY")
                .groupSearchBase("")
                .contextSource()
                .managerDn("cn=admin,dc=spring-ldap,dc=com")
                .managerPassword("password")
                .url(ldapUrl + "/" + base)
                .and()
                .passwordCompare()
//                .passwordEncoder(new BCryptPasswordEncoder())
                .passwordAttribute("userPassword")
        ;
    }

}
