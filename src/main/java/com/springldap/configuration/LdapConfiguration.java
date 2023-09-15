package com.springldap.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.BaseLdapPathBeanPostProcessor;

@Configuration
public class LdapConfiguration {

    @Value("${spring.ldap.base}")
    private String baseDn = "dc=springframework,dc=org";

    @Bean
    public BaseLdapPathBeanPostProcessor ldapPathBeanPostProcessor() {
        BaseLdapPathBeanPostProcessor baseLdapPathBeanPostProcessor = new BaseLdapPathBeanPostProcessor();
        baseLdapPathBeanPostProcessor.setBasePath(baseDn);
        return baseLdapPathBeanPostProcessor;
    }

}
