package org.perscholas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity //database
@Component //spring boot component
public class Permissions implements Serializable {
    private static final long serialVersionUID = 5634285645092880262L;

    //fields
    @Id //makes this field the id
    @SequenceGenerator( //creates a sequence
            name = "Permissions_sequence", //names the table in the database
            sequenceName = "Permissions_sequence", // sequence name
            allocationSize = 1 // incriment by 1
    )
    @GeneratedValue(//tells what value to input
            strategy = GenerationType.SEQUENCE, // says to use a sequence instead of auto increment aka GenerationType.IDENTITY
            generator = "Permissions_sequence" // use sequence name
    )
    @Column(name = "Permissions_Id")
    Long pId;
    @NonNull @NotBlank
    String pName;
    //joining permissions to userType table
    @ToString.Exclude
    @OneToMany(mappedBy = "userTypePermissions", fetch = FetchType.LAZY)
    List<UserType> pPermissions;

}
