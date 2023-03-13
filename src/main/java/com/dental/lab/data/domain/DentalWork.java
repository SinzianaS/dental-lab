package com.dental.lab.data.domain;

import com.dental.lab.data.domain.enums.Color;
import com.dental.lab.data.domain.enums.Status;
import com.dental.lab.data.domain.enums.Type;
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
