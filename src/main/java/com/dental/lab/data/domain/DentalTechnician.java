package com.dental.lab.data.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "dental_technicians")
public class DentalTechnician {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String technicianName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dental_work_technician_id",
                joinColumns = {@JoinColumn(name = "dental_technician_id")},
                inverseJoinColumns = {@JoinColumn(name = "dental_work_id")},
                foreignKey = @ForeignKey(name = "dental_work_to_technician_fk"),
                inverseForeignKey = @ForeignKey(name = "technician_to_dental_work_fk"))
    private List<DentalWork> dentalWorkList;
}
