package org.perscholas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity //database
@Component //spring boot component
public class Employees implements Serializable {
    static final long serialVersionUID = 1L;

    //fields

    @Id //makes this field the id
    @SequenceGenerator( //creates a sequence
            name = "User_sequence", //names the table in the database
            sequenceName = "User_sequence", // sequence name
            allocationSize = 1 // incriment by 1
    )
    @GeneratedValue(//tells what value to input
            strategy = GenerationType.SEQUENCE, // says to use a sequence instead of auto increment aka GenerationType.IDENTITY
            generator = "User_sequence" // use sequence name
    )
    Long eId;
    //cannot be null/blank
    @NonNull @NotBlank
    String eFirstName;
    @NonNull @NotBlank
    String eLastName;
    @NonNull @NotBlank
    String eJobTitle;
    @NonNull @NotBlank
    String eDepartment;
    @NonNull @NotBlank
    String ePhoneNumber;
    @NonNull @NotBlank
    String eEmail;
    @NonNull @NotBlank
    LocalDate eDob;
    @NonNull @NotBlank
    LocalDate eHireDate;


}