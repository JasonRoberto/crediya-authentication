package co.com.crediya.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UserResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String identityDocument;
    private String phone;
    private BigDecimal baseSalary;
    private LocalDate birthDate;
    private String roleName;
}
