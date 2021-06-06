package org.perscholas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity //database
@Component //spring boot component
public class Users implements Serializable {
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
    Long uId;
    @NonNull @Column(unique = true) @NotBlank //cannot be null/blank and must be unique
    String uUsername;
    @NonNull @NotBlank//cannot be null/blank
    String uPassword;


}
