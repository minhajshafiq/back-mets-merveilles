package org.metsetmerveilles.data_access.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "menu")
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_generator")
    @SequenceGenerator(name = "menu_generator", sequenceName = "menu_seq", allocationSize = 1)
    private Long id;

    @Column(name = "label", nullable = false, unique = true)
    private String label;

    public MenuEntity() {
    }

    public MenuEntity(String label) {
        this.label = label;
    }
}
