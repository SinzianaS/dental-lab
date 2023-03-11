package com.dental.lab.data.domain;

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


    @ManyToOne
    @JoinColumn(name = "dental_technician_id")
    private DentalTechnician dentalTechnician;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    Status status;

    Type type;

    Color color;


}
