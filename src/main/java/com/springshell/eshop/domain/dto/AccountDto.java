package com.springshell.eshop.domain.dto;

import com.springshell.eshop.domain.entity.Account;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AccountDto {
    private Long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    //private CustomerDto customerDto;

    public static AccountDto from(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .login(account.getLogin())
                .password(account.getPassword())
                .name(account.getName())
                .surname(account.getSurname())
                .email(account.getEmail())
                .phoneNumber(account.getPhoneNumber())
                //.customerDto(CustomerDto.from(account.getCustomer()))
                .build();
    }
}
