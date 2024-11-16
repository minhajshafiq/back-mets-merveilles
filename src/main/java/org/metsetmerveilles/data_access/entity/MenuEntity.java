package org.metsetmerveilles.data_access.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "menu")
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_generator")
    @SequenceGenerator(name = "menu_generator", sequenceName = "menu_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "menu")
    private List<StarterEntity> starter;

    @OneToMany(mappedBy = "menu")
    private List<MainCourseEntity> mainCourse;

    @OneToMany(mappedBy = "menu")
    private List<DessertEntity> desserts;

    @OneToMany(mappedBy = "menu")
    private List<DrinksEntity> drinks;

    @Column(name = "price", nullable = false)
    private double price;
}
