package org.metsetmerveilles.data_access.entity;

import jakarta.persistence.*;
import lombok.*;
import org.metsetmerveilles.data_access.entity.enums.ItemSizeEnum;
import org.metsetmerveilles.data_access.entity.enums.ItemTypeEnum;
import org.metsetmerveilles.data_access.entity.enums.ItemVolumeEnum;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_generator")
    @SequenceGenerator(name = "item_generator", sequenceName = "item_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ItemTypeEnum type;

    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false)
    private ItemSizeEnum size;

    @Enumerated(EnumType.STRING)
    @Column(name = "volume", nullable = false)
    private ItemVolumeEnum volume;

    @Column(name = "availableForClients")
    private Boolean availableForClients;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private MenuEntity menu;

}
