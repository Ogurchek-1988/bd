package ru.portal.semusadba.model.projects;

import lombok.*;
import ru.portal.semusadba.model._common.BaseEntity;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
@Entity
@Table(name = "projects")
public class Projects implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name = "image_name")
    private String imageName;


}

