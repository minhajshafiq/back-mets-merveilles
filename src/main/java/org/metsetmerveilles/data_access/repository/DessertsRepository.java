package org.metsetmerveilles.data_access.repository;

import org.metsetmerveilles.data_access.entity.DessertsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DessertsRepository extends JpaRepository<DessertsEntity, Long> {
}
