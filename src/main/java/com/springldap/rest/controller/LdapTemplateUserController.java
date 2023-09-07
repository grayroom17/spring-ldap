package com.springldap.rest.controller;

import com.springldap.rest.dto.AttributeDto;
import com.springldap.rest.dto.UserCreateDto;
import com.springldap.rest.dto.UserGetDto;
import com.springldap.service.LdapTemplateUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("ldap-template-users")
public class LdapTemplateUserController {

    LdapTemplateUserService ldapTemplateUserService;

    @GetMapping
    List<UserGetDto> getAllLdapUsers() {
        return ldapTemplateUserService.getAllLdapUsers();
    }

    @GetMapping("/get-all-user-names")
    List<String> getAllUserNames() {
        return ldapTemplateUserService.getAllUserNames();
    }

    @GetMapping("/{sureName}")
    List<UserGetDto> getAllLdapUsersBySureName(@PathVariable("sureName") String sureName) {
        return ldapTemplateUserService.getAllLdapUsersBySureName(sureName);
    }

    @GetMapping("/get-by-dn/{dn}")
    UserGetDto getByDn(@PathVariable("dn") String dn) {
        return ldapTemplateUserService.lookupByDn(dn);
    }

    @GetMapping("/get-by-dn-with-context-mapper/{dn}")
    UserGetDto getByDnWithContextMapper(@PathVariable("dn") String dn) {
        return ldapTemplateUserService.lookupByDnWithContextMapper(dn);
    }

    @PostMapping
    void create(@RequestBody UserCreateDto dto) {
        ldapTemplateUserService.create(dto);
    }

    @PostMapping("/create-with-dir-context-adapter/{dn}")
    void createWithDirContextAdapter(@PathVariable("dn") String dn,
                                     @RequestBody UserCreateDto dto) {
        ldapTemplateUserService.createWithDirContextAdapter(dn, dto);
    }

    @DeleteMapping("/delete-by-dn/{dn}")
    void create(@PathVariable("dn") String dn) {
        ldapTemplateUserService.delete(dn);
    }

    @PutMapping("/rebind/{dn}")
    void create(@PathVariable("dn") String dn,
                @RequestBody UserCreateDto dto) {
        ldapTemplateUserService.rebind(dn, dto);
    }

    @PatchMapping("/update-attribute/{dn}")
    void updateAttribute(@PathVariable("dn") String dn,
                         @RequestBody AttributeDto attribute) {
        ldapTemplateUserService.updateAttribute(dn, attribute);
    }

    @PutMapping("/update-with-dir-context-operations/{dn}")
    void updateAttribute(@PathVariable("dn") String dn,
                         @RequestBody UserCreateDto dto) {
        ldapTemplateUserService.updateWithDirContextOperations(dn, dto);
    }

    //ODM - Object-Directory Mapping

    @GetMapping("/find-one-by-guid")
    UserGetDto findOneByGuid(@RequestParam("guid") String guid) {
        return ldapTemplateUserService.findOneByGuid(guid);
    }

    @GetMapping("/find-all")
    List<UserGetDto> findAll() {
        return ldapTemplateUserService.findAll();
    }

    @GetMapping("/find-all-by-first-name")
    List<UserGetDto> findAllByFirsName(String firstName) {
        return ldapTemplateUserService.findAllByFirsName(firstName);
    }

    @GetMapping("/find-all-in-base")
    List<UserGetDto> findAllInBase(String base) {
        return ldapTemplateUserService.findAllInBase(base);
    }

    @GetMapping("/find-all-by-full-name")
    List<UserGetDto> findAllByFullName(@RequestParam("name") String name) {
        return ldapTemplateUserService.findAllByFullName(name);
    }

    @GetMapping("/test-custom-search")
    List<UserGetDto> testCustomSearch() {
        return ldapTemplateUserService.testCustomSearch();
    }

}
