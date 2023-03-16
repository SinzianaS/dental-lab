package com.dental.lab.data.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "dentists")
public class Dentist {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String name;

    @Column(name = "clinic")
    private String clinicName;

    private String address;

}
