package ru.portal.semusadba.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public ProductsList(String name, Long groupId, int price, int count){
        this.name = name;
        this.groupId = groupId;
        this.price = price;
        this.count = count;
    }
}
