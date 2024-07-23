package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
    @NotBlank(message = "Username is required")
    @Size(min = 3,message = "min 3 char. is required")
    private String name;
    @Email(message = "Invalid Email Address")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "password is required")
    @Size(min = 6,message = "MIn 6 character is required")
    private String password;
    @NotBlank(message = "About is Required")
    private String about;
    @Size(min = 10,max = 12,message = "Invalid Phone Number")
    private String phoneNumber;
}
