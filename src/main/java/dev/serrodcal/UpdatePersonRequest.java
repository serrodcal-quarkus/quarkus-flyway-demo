package dev.serrodcal;

import jakarta.validation.constraints.*;

public record UpdatePersonRequest(
        @NotNull @Min(value = 0, message = "id cannot be negative") Long id,
        @NotNull(message = "name cannot be null") @NotBlank(message = "name cannot be blank") String name,
        @Min(value = 0, message = "age cannot be less than 0") @Max(value = 120, message = "age cannot be more than 120")  Integer age,
        @NotNull(message = "email cannot be null") @NotBlank(message = "email cannot be blank") @Email String email
) {
}
