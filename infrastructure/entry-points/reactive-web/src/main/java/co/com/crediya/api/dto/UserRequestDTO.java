package co.com.crediya.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    @NotNull(message = "First name cannot be null")
    @Size(min = 2, message = "First name must be at least 2 characters long")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, message = "Last name must be at least 2 characters long")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Identity document cannot be null")
    private String identityDocument;

    private String phone;

    @NotNull(message = "Base salary cannot be null")
    @DecimalMin(value = "0.0", message = "Base salary must be a positive number")
    private BigDecimal baseSalary;

    private LocalDate birthDate;

    //@NotNull(message = "Role ID cannot be null")
    private Long roleId;
}
