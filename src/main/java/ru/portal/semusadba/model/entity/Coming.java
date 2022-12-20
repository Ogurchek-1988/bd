package ru.portal.semusadba.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "coming")
public class Coming {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_coming", nullable = false)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "count")
    private int count;

    @Column(name = "date")
    private Date date;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "worker_id")
    private Long workerId;

    @Column(name = "price")
    private int price;
}
