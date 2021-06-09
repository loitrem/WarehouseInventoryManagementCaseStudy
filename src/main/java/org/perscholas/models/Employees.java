package org.perscholas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity //database
@Component //spring boot component
public class Employees implements Serializable {
    private static final long serialVersionUID = 990121599287231165L;

    //fields
    @Id //makes this field the id
    @SequenceGenerator( //creates a sequence
            name = "Employees_sequence", //names the table in the database
            sequenceName = "Employees_sequence", // sequence name
            allocationSize = 1 // incriment by 1
    )
    @GeneratedValue(//tells what value to input
            strategy = GenerationType.SEQUENCE, // says to use a sequence instead of auto increment aka GenerationType.IDENTITY
            generator = "Employees_sequence" // use sequence name
    )
    @Column(name = "Employees_Id")
    Long eId;
    //cannot be null/blank
    @NonNull @NotBlank
    String eFirstName;
    @NonNull @NotBlank
    String eLastName;
    @NonNull @NotBlank
    String eJobTitle;
    @NonNull @NotBlank
    String ePhoneNumber;
    //cannot be null/blank and must be unique
    @NonNull @NotBlank @Column(unique = true)
    String eEmail;
    @NonNull @NotBlank
    Date eDob;
    @NonNull @NotBlank
    Date eHireDate;
    String eProfileImage;
    //joining employees to users table
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Users_Id")
    Users eUser_Id;
    //joining employees to inventory table
    @ToString.Exclude
    @OneToOne(mappedBy = "iMovedBy", fetch = FetchType.LAZY)
    Inventory eInventoryMovedBy;
    //joining employees to departments table
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Departments_Id")
    Departments eDepartment;


}