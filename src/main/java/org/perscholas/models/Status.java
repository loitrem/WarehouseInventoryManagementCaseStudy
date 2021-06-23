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
public class Status implements Serializable {
    private static final long serialVersionUID = -2698976041882598361L;

    //fields
    @Id //makes this field the id
    @SequenceGenerator( //creates a sequence
            name = "Status_sequence", //names the table in the database
            sequenceName = "Status_sequence", // sequence name
            allocationSize = 1 // incriment by 1
    )
    @GeneratedValue(//tells what value to input
            strategy = GenerationType.SEQUENCE, // says to use a sequence instead of auto increment aka GenerationType.IDENTITY
            generator = "Status_sequence" // use sequence name
    )
    @Column(name = "Status_Id")
    Long sId;

    @NonNull @NotBlank
    String sStatus;

    //joining status to inventory table
    @ToString.Exclude
    @OneToMany(mappedBy = "iStatus",fetch = FetchType.LAZY)
    List<Inventory> sInv_Status;

}
