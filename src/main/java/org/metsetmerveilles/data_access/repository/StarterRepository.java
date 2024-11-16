package org.metsetmerveilles.data_access.repository;

import org.metsetmerveilles.data_access.entity.StarterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarterRepository extends JpaRepository<StarterEntity, Long> {
}
