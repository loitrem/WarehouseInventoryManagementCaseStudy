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
public class InventoryGroup implements Serializable {
    private static final long serialVersionUID = -4628411252883298196L;

    //fields
    @Id //makes this field the id
    @SequenceGenerator( //creates a sequence
            name = "InventoryGroup_sequence", //names the table in the database
            sequenceName = "InventoryGroup_sequence", // sequence name
            allocationSize = 1 // incriment by 1
    )
    @GeneratedValue(//tells what value to input
            strategy = GenerationType.SEQUENCE, // says to use a sequence instead of auto increment aka GenerationType.IDENTITY
            generator = "InventoryGroup_sequence" // use sequence name
    )
    @Column(name = "Inv_Group_Id")
    Long gId;
    //cannot be null/blank and must be unique
    @NonNull @NotBlank @Column(unique = true)
    String gCompanyAbbreviation; //to be used as inventory group field in inventory
    @NonNull @NotBlank @Column(unique = true)
    String gCompanyName;
    //cannot be null/blank
    @NonNull @NotBlank
    String gContactFirstName;
    @NonNull @NotBlank
    String gContactLastName;
    @NonNull @NotBlank @Column(unique = true)
    String gContactEmail;
    @NonNull @NotBlank @Column(unique = true)
    String gContactPhone;
    @NonNull @NotBlank
    Date gContractStartDate;
    @NonNull @NotBlank
    Date gContractEndDate;
    //joining group to inventory table
    @ToString.Exclude
    @OneToMany(mappedBy = "iInventoryGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Inventory> gInventory;


}
