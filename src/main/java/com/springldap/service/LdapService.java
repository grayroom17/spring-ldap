package com.springldap.service;

import com.springldap.domain.entity.LdapUser;
import com.springldap.repository.LdapCustomRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class LdapService {

    LdapCustomRepository ldapRepository;

    public List<LdapUser> findAll() {
        return ldapRepository.findAll();
    }

}
