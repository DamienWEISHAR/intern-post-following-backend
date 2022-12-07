package canard.intern.post.following.backend.dto;

import canard.intern.post.following.backend.enums.Gender;
import canard.intern.post.following.backend.validator.DateLessThan;
import lombok.Builder;
import lombok.Data;

import javax.validation.Constraint;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class TraineeDto {

    private Integer id;

    @NotBlank
    private String lastName;
    @NotBlank
    private String firstName;

    private Gender gender;

    @NotNull
    //@Past
    @DateLessThan
    private LocalDate birthDate;

    @Pattern(regexp = "(\\+33||0)[0-9]{9}")
    private String phoneNumber;

    @NotBlank
    @Email
    private String email;


}
