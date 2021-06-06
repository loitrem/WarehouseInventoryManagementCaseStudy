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
public class InventoryGroup implements Serializable {
    static final long serialVersionUID = 1L;

    //fields

    @Id //makes this field the id
    @SequenceGenerator( //creates a sequence
            name = "Inventory_sequence", //names the table in the database
            sequenceName = "Inventory_sequence", // sequence name
            allocationSize = 1 // incriment by 1
    )
    @GeneratedValue(//tells what value to input
            strategy = GenerationType.SEQUENCE, // says to use a sequence instead of auto increment aka GenerationType.IDENTITY
            generator = "Inventory_sequence" // use sequence name
    )
    Long gId;
    //cannot be null/blank
    @NonNull @NotBlank
    String gCompanyAbbreviation; //to be used as inventory group field in inventory
    @NonNull @NotBlank
    String gCompanyName;
    @NonNull @NotBlank
    String gContactFirstName;
    @NonNull @NotBlank
    String gContactLastName;
    @NonNull @NotBlank
    String gContactEmail;
    @NonNull @NotBlank
    String gContactPhone;
    @NonNull @NotBlank
    LocalDate gContractStartDate;
    @NonNull @NotBlank
    LocalDate gContractEndDate;
}
