package com.springldap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;

@EnableLdapRepositories
@SpringBootApplication
public class SpringLdapApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLdapApplication.class, args);
    }

}
