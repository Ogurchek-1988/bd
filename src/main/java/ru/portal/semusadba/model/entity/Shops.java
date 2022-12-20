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
@Table(name = "shops")
public class Shops {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_shop", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    public Shops(String name){
        this.name = name;
    }
}
