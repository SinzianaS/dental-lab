package com.dental.lab.model;


import com.dental.lab.model.enums.Color;
import com.dental.lab.model.enums.Status;
import com.dental.lab.model.enums.Type;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "dental_works")
public class DentalWork {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid2")
    private UUID id;


    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Enumerated(EnumType.STRING)
    Status status;

    @Enumerated(EnumType.STRING)
    Type type;

    @Enumerated(EnumType.STRING)
    Color color;


}
