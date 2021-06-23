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
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity //database
@Component //spring boot component
public class UserType implements Serializable {
    private static final long serialVersionUID = 5203197473190411379L;

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
    @Column(name = "User_Type_Id")
    Long userTypeId;

    @NonNull @NotBlank @Column(unique = true)//cannot be null/blank and must be unique
    String userTypeName;

    //joining userType to users table
    @ToString.Exclude
    @OneToMany(mappedBy = "uUserType",fetch = FetchType.LAZY)
    List<Users> userTypeToUsers;

    //joining userType to Permissions table
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserType_Permissions_Id")
    Permissions userTypePermissions;

}
