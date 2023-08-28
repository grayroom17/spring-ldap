package com.springldap.rest.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {

    String commonName;
    String canonicalName;
    String guid;
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
    String enabled;

}
