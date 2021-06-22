package org.perscholas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
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
    @NonNull @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date gContractStartDate;
    @NonNull @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date gContractEndDate;
    //joining group to inventory table
    @ToString.Exclude
    @OneToMany(mappedBy = "iInventoryGroup", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.PERSIST)
    List<Inventory> gInventory;


}
