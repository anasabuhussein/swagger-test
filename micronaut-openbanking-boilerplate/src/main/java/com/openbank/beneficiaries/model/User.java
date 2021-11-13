package com.openbank.beneficiaries.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/*
*
* Created By Anas Bassam Hussein
* on 25/03/2021
*
**/

@Entity
@Table(name="`User`")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class User {

    @Id @NotNull @NotEmpty
    private String username;

    @NotNull @NotEmpty
    private String password;

    @NotNull @NotEmpty
    private UserPrivileges privileges = UserPrivileges.UnReadBeneficiariesDetail;
}
