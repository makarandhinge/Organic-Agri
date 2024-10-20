package com.scm.form;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {
    @NotBlank(message = "Name is required")
    private String name;
    @Size(min = 8,message = "Minimun 8 digit password required")
    private String password;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Address")
    private String email;
    @Size(min = 10,max = 10,message = "Invalid Phone Number")
    private String phoneNumber;
}
