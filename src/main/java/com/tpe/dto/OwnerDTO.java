package com.tpe.dto;

import com.tpe.domain.Owner;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class OwnerDTO {

    @NotBlank(message = "Geçerli bir isim giriniz!")
    private String name;

    @NotBlank(message = "Geçerli bir soyisim giriniz!")
    private String lastName;

    private String phoneNumber;

    @Email(message = "Geçerli bir email giriniz!")
    private String email;

    //owner->DTO
    public OwnerDTO(Owner owner){
        this.name=owner.getName();
        this.lastName=owner.getLastName();
        this.phoneNumber= owner.getPhoneNumber();
        this.email= owner.getEmail();
    }


}