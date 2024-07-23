package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactForm {
    @NotBlank(message = "Name is Required")
    private String name;
    @NotBlank(message = "Email Is Required")
    @Email(message = "Invalid Email")
    private String email;
    @Pattern(regexp = "^[0-9]{10}$")
    @NotBlank(message = "Phone Number is required")
    private String phoneNumber;
    @NotBlank(message = "Address is required")
    private String address;
    private String description;
    private boolean favourite;
    private String websiteLink;
    private String linkedInLink;
    private MultipartFile contactImage;
}
