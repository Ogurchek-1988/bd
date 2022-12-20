package ru.portal.semusadba.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "returns")
public class Returns {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_return", nullable = false)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "count")
    private int count;

    @Column(name = "date")
    private Date date;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "description")
    private String description;

    @Column(name = "worker_id")
    private Long workerId;
}
