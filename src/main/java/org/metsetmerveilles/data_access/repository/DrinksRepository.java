package org.metsetmerveilles.data_access.repository;

import org.metsetmerveilles.data_access.entity.DrinksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinksRepository extends JpaRepository<DrinksEntity, Long> {
}
