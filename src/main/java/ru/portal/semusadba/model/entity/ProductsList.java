package ru.portal.semusadba.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "products_list")
public class ProductsList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_product", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "price")
    private int price;

    @Column(name = "count")
    private int count;
}
