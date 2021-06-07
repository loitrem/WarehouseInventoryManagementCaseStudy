package org.perscholas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity //database
@Component //spring boot component
public class Departments implements Serializable {
    private static final long serialVersionUID = 8216668784100856052L;

    //fields
    @Id //makes this field the id
    @SequenceGenerator( //creates a sequence
            name = "Departments_sequence", //names the table in the database
            sequenceName = "Departments_sequence", // sequence name
            allocationSize = 1 // incriment by 1
    )
    @GeneratedValue(//tells what value to input
            strategy = GenerationType.SEQUENCE, // says to use a sequence instead of auto increment aka GenerationType.IDENTITY
            generator = "Departments_sequence" // use sequence name
    )
    @Column(name = "Departments_Id")
    Long dId;
    //cannot be null/blank and must be unique
    @NonNull @NotBlank @Column(unique = true)
    String dName;
    //joining employees to departments table
    @ToString.Exclude
    @OneToMany(mappedBy = "eDepartment", fetch = FetchType.LAZY)
    List<Employees> dEmployees;


}