package org.metsetmerveilles.data_access.entity;

import jakarta.persistence.*;
import lombok.*;
import org.metsetmerveilles.domain.model.MainCourse;

import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "main_course")
public class MainCourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "main_course_generator")
    @SequenceGenerator(name = "main_course_generator", sequenceName = "main_course_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne(optional = true)
    @JoinColumn(name = "menu_id", nullable = true)
    private MenuEntity menu;

    public static MainCourseEntity fromDomain(MainCourse mainCourse) {
        MainCourseEntityBuilder builder = MainCourseEntity.builder();
        if (mainCourse.menuId().isPresent()) {
            builder.menu(MenuEntity.builder().id(mainCourse.menuId().get()).build());
        }
        return
                builder
                        .id(mainCourse.id())
                        .name(mainCourse.name())
                        .description(mainCourse.description())
                        .price(mainCourse.price())
                        .build();
    }

    public MainCourse toDomain() {
        return new MainCourse(
                this.getId(),
                this.getName(),
                this.getDescription(),
                this.getPrice(),
                Optional.ofNullable(this.getMenu() != null ? this.getMenu().getId() : null)
        );
    }

}
