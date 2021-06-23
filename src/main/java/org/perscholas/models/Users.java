package org.perscholas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity //database
@Component //spring boot component
public class Users implements Serializable {
    private static final long serialVersionUID = -989715664155322932L;

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
    @Column(name = "Users_Id")
    Long uId;
    @NonNull @NotBlank @Column(unique = true) //cannot be null/blank and must be unique
    @Pattern(regexp = "^[A-Za-z]\\w{6,}$", message = "Must contain at least 6 characters\n" +
            "Must only contain letters or numbers")
    String uUsername;
    @NonNull @NotBlank//cannot be null/blank
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "Must contain at least 8 characters\n" +
            "At least 1 upper case letter\n" +
            "At least 1 lower case letter\n" +
            "At least 1 number\n" +
            "At least 1 special character")
    String uPassword;
    //joining users to userType table
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Type")
    UserType uUserType;
    //joining users to employees table
    @ToString.Exclude
    @OneToOne(mappedBy = "eUser_Id", fetch = FetchType.LAZY)
    Employees uEmployee_Username;
    //add permissions table and link it


}
