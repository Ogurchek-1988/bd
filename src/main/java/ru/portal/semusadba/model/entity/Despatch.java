package ru.portal.semusadba.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "despatch")
public class Despatch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_despatch", nullable = false)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "count")
    private int count;

    @Column(name = "date")
    private Date date;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "worker_id")
    private Long workerId;

    @Column(name = "price")
    private int price;

    public Despatch(Long productId, int count, Date date, Long shopId, Long workerId, int price){
        this.productId = productId;
        this.count = count;
        this.date = date;
        this.shopId = shopId;
        this.workerId = workerId;
        this.price = price;
    }
}
