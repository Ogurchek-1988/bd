package ru.portal.semusadba.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "shops")
public class Shops {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_shop", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;
}
