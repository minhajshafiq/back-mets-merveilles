package org.metsetmerveilles.data_access.entity;

import jakarta.persistence.*;
import lombok.*;
import org.metsetmerveilles.domain.model.Desserts;
import org.metsetmerveilles.domain.model.Drinks;
import org.metsetmerveilles.domain.model.MainCourse;

import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "desserts")
public class DessertsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "desserts_generator")
    @SequenceGenerator(name = "desserts_generator", sequenceName = "desserts_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(optional = true)
    @JoinColumn(name = "menu_id", nullable = true)
    private MenuEntity menu;

    public static DessertsEntity fromDomain(Desserts desserts) {
        DessertsEntity.DessertsEntityBuilder builder = DessertsEntity.builder();
        if (desserts.menuId().isPresent()) {
            builder.menu(MenuEntity.builder().id(desserts.menuId().get()).build());
        }
        return
                builder
                        .id(desserts.id())
                        .name(desserts.name())
                        .description(desserts.description())
                        .price(desserts.price())
                        .imageUrl(desserts.imageUrl())
                        .build();
    }

    public Desserts toDomain() {
        return new Desserts(
                this.getId(),
                this.getName(),
                this.getDescription(),
                this.getPrice(),
                this.getImageUrl(),
                Optional.ofNullable(this.getMenu() != null ? this.getMenu().getId() : null)
        );
    }
}
