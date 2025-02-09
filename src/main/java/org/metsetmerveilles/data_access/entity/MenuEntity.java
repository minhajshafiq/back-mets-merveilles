package org.metsetmerveilles.data_access.entity;

import jakarta.persistence.*;
import lombok.*;
import org.metsetmerveilles.domain.model.Menu;

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

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<StarterEntity> starter;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<MainCourseEntity> mainCourse;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<DessertsEntity> desserts;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<DrinksEntity> drinks;

    @Column(name = "price", nullable = false)
    private double price;

    public static MenuEntity fromDomain(Menu menu) {
        MenuEntity.MenuEntityBuilder builder = MenuEntity.builder();

        if (menu.starterIds().isPresent()) {
            builder.starter(
                    menu.starterIds().get().stream()
                            .map(id -> StarterEntity.builder().id(id).build())
                            .toList()
            );
        }

        if (menu.mainCourseIds().isPresent()) {
            builder.mainCourse(
                    menu.mainCourseIds().get().stream()
                            .map(id -> MainCourseEntity.builder().id(id).build())
                            .toList()
            );
        }

        if (menu.dessertsIds().isPresent()) {
            builder.desserts(
                    menu.dessertsIds().get().stream()
                            .map(id -> DessertsEntity.builder().id(id).build())
                            .toList()
            );
        }

        if(menu.drinksIds().isPresent()) {
            builder.drinks(
                    menu.drinksIds().get().stream()
                            .map(id -> DrinksEntity.builder().id(id).build())
                            .toList()
            );
        }

        return
                builder
                        .id(menu.id())
                        .name(menu.name())
                        .description(menu.description())
                        .price(menu.price())
                        .build();
    }

    public Menu toDomain() {
        return new Menu(
                this.getId(),
                this.getName(),
                this.getDescription(),
                this.getPrice(),
                this.getStarter() != null
                        ? Optional.of(this.getStarter().stream().map(StarterEntity::getId).toList())
                        : Optional.empty(),
                this.getMainCourse() != null
                        ? Optional.of(this.getMainCourse().stream().map(MainCourseEntity::getId).toList())
                        : Optional.empty(),
                this.getDesserts() != null
                        ? Optional.of(this.getDesserts().stream().map(DessertsEntity::getId).toList())
                        : Optional.empty(),
                this.getDrinks() != null
                        ? Optional.of(this.getDrinks().stream().map(DrinksEntity::getId).toList())
                        : Optional.empty()
        );
    }
}
