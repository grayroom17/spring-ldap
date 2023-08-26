package com.springldap.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String distinguishedName;

    String commonName;

    String canonicalName;

    UUID guid;

    String userPrincipalName;

    String displayName;

    String fullName;

    String firstname;

    String lastname;

    String otherName;

    String initials;

    String telephoneNumber;

    String homePhone;

    String mobilePhone;

    String country;

    String state;

    String city;

    String street;

    String postalCode;

    String company;

    String organization;

    String division;

    String department;

    String office;

    String manager;

    String employeeId;

    String employeeNumber;

    String mail;

    String mailNickname;

    String samAccountName;

    String officePhone;

    String ipPhone;

    String title;

    String userAccountControl;

    String enabled;

}
