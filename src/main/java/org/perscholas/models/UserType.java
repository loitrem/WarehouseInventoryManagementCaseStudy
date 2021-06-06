package org.perscholas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity //database
@Component //spring boot component
public class UserType implements Serializable {
    private static final long serialVersionUID = 1L;

    //fields

    @Id //makes this field the id
    @SequenceGenerator( //creates a sequence
            name = "UserType_sequence", //names the table in the database
            sequenceName = "UserType_sequence", // sequence name
            allocationSize = 1 // incriment by 1
    )
    @GeneratedValue(//tells what value to input
            strategy = GenerationType.SEQUENCE, // says to use a sequence instead of auto increment aka GenerationType.IDENTITY
            generator = "UserType_sequence" // use sequence name
    )
    Long uTypeId;
    @NonNull
    @Column(unique = true) @NotBlank //cannot be null/blank and must be unique
    String uType;

}
