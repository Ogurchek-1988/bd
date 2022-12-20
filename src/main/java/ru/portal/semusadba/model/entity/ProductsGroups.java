package ru.portal.semusadba.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "products_groups")
public class ProductsGroups {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_product_group", nullable = false)
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "description")
    private String description;
}
