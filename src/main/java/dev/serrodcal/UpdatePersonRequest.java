package dev.serrodcal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePersonRequest(
        @NotNull @Min(value = 0, message = "id cannot be negative") Long id,
        @NotNull(message = "name cannot be null") @NotBlank(message = "name cannot be blank") String name,
        @Min(value = 0, message = "age cannot be less than 0") @Max(value = 120, message = "age cannot be more than 120")  Integer age
) {
}
