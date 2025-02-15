package org.metsetmerveilles.data_access.entity;

import jakarta.persistence.*;
import lombok.*;
import org.metsetmerveilles.domain.model.Drinks;
import org.metsetmerveilles.domain.model.MainCourse;

import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "drinks")
public class DrinksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drinks_generator")
    @SequenceGenerator(name = "drinks_generator", sequenceName = "drinks_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = true)
    private MenuEntity menu;

    public static DrinksEntity fromDomain(Drinks drinks) {
        DrinksEntity.DrinksEntityBuilder builder = DrinksEntity.builder();
        if (drinks.menuId().isPresent()) {
            builder.menu(MenuEntity.builder().id(drinks.menuId().get()).build());
        }
        return
                builder
                        .id(drinks.id())
                        .name(drinks.name())
                        .description(drinks.description())
                        .price(drinks.price())
                        .imageUrl(drinks.imageUrl())
                        .build();
    }

    public Drinks toDomain() {
        return new Drinks(
                this.getId(),
                this.getName(),
                this.getDescription(),
                this.getPrice(),
                this.getImageUrl(),
                Optional.ofNullable(this.getMenu() != null ? this.getMenu().getId() : null)
        );
    }
}
