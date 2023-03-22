package com.dental.lab.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String name;

    private int age;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Dentist dentist;

}
