package co.com.crediya.model.user;
import co.com.crediya.model.role.Role;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    private long idUser;
    private String name;
    private String lastName;
    private Date birthDate;
    private String email;
    private String identityDocument;
    private String phone;
    private BigDecimal baseSalary;

    private Role role;
}
