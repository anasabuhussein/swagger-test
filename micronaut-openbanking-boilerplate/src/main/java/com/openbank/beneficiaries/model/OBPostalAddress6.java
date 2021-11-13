package com.openbank.beneficiaries.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neovisionaries.i18n.CountryCode;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

/*
 * Created by Anas Bassam Hussein
 * on 23/05/2021
 * */

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OBPostalAddress6 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Size(min = 0, max = 70)
    private String department;

    @Size(min = 0, max = 70)
    private String subDepartment;

    @Size(min = 0, max = 70)
    private String streetName;

    @Size(min = 0, max = 70)
    private String buildingNumber;

    @Size(min = 0, max = 16)
    private String postCode;

    @Size(min = 0, max = 35)
    private String townName;

    @Size(min = 0, max = 35)
    private String countrySubDivision;

    @Size(min = 0, max = 70)
    private String addressLine;

    private CountryCode country;

    private OBAddressTypeCode addressType;
}
