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
public class Inventory implements Serializable {
    private static final long serialVersionUID = 9112337966959229253L;

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
    @Column(name = "Inventory_Id")
    Long iId;
    //cannot be null/blank and must be unique
    @NonNull @NotBlank @Column(unique = true)
    //cannot be null/blank
    String iLocation;
    @NonNull @NotNull
    String iItemNumber;
    @NonNull @NotBlank
    String iDescription;
    @NonNull @NotNull
    int iQuantity;
    //joining inventory to group table
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Inv_Group_Id")
    InventoryGroup iInventoryGroup;
    @Temporal(TemporalType.DATE)
    @NonNull @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date iDateReceived;
    //joining inventory to status table
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Status_Id")
    Status iStatus;
    //joining inventory to employees table
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Moved_By_Id")
    Employees iMovedBy;
    @Temporal(TemporalType.DATE)
    @NonNull @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date iLastDateMoved;



}
