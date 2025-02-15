package org.metsetmerveilles.data_access.entity;

import jakarta.persistence.*;
import lombok.*;
import org.metsetmerveilles.domain.model.Starter;

import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "starter")
public class StarterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "starter_generator")
    @SequenceGenerator(name = "starter_generator", sequenceName = "starter_seq", allocationSize = 1)
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

    public static StarterEntity fromDomain(Starter starter) {
        StarterEntity.StarterEntityBuilder builder = StarterEntity.builder();
        if (starter.menuId().isPresent()) {
            builder.menu(MenuEntity.builder().id(starter.menuId().get()).build());
        }
        return
                builder
                        .id(starter.id())
                        .name(starter.name())
                        .description(starter.description())
                        .price(starter.price())
                        .imageUrl(starter.imageUrl())
                        .build();
    }

    public Starter toDomain() {
        return new Starter(
                this.getId(),
                this.getName(),
                this.getDescription(),
                this.getPrice(),
                this.getImageUrl(),
                Optional.ofNullable(this.getMenu() != null ? this.getMenu().getId() : null)
        );
    }
}
