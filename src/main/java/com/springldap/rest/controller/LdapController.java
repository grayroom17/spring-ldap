package com.springldap.rest.controller;

import com.springldap.rest.dto.UserCreateDto;
import com.springldap.rest.dto.UserGetDto;
import com.springldap.service.LdapService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{sureName}")
    List<UserGetDto> getAllLdapUsersBySureName(@PathVariable("sureName") String sureName) {
        return ldapService.getAllLdapUsersBySureName(sureName);
    }

    @GetMapping("/get-by-dn/{dn}")
    UserGetDto getByDn(@PathVariable("dn") String dn) {
        return ldapService.lookupByDn(dn);
    }

    @PostMapping
    void create(@RequestBody UserCreateDto dto) {
        ldapService.create(dto);
    }

    @DeleteMapping("/delete-by-dn/{dn}")
    void create(@PathVariable("dn") String dn) {
        ldapService.delete(dn);
    }

}
