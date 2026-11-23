package org.example.springboot_app_people.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

public record PeopleRequest(

        @NotBlank (message = "Name can not be empty")
        @Size (max = 255, message = "A persons name can not exceeed 255 characters")
        String name,

        @NotBlank (message = "Email can not be empty")
        @Size (max = 255, message = "A persons email can not be exceed 255 characters")
        String email

) {
}
