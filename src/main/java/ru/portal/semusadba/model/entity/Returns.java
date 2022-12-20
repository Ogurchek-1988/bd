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

    public Returns(Long productId, int count, Date date, Long shopId, String description, Long workerId){
        this.productId = productId;
        this.count = count;
        this.date = date;
        this.shopId = shopId;
        this.description = description;
        this.workerId = workerId;
    }
}
