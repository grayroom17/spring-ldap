package com.springldap.rest.controller;

import com.springldap.rest.dto.UserGetDto;
import com.springldap.service.LdapService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("ldap-users")
public class LdapController {

    LdapService ldapService;

    @GetMapping
    List<UserGetDto> getAllLdapUsers() {
        return ldapService.getAllLdapUsers();
    }

    @GetMapping("/get-all-user-names")
    List<String> getAllUserNames() {
        return ldapService.getAllUserNames();
    }

}