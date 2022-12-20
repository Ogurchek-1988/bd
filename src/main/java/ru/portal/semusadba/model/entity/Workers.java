package ru.portal.semusadba.model.entity;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "workers")
public class Workers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_workers", nullable = false)
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;
}
