package ru.portal.semusadba.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "suppliers")
public class Suppliers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_supplier", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    public Suppliers(String name){
        this.name = name;
    }
}
