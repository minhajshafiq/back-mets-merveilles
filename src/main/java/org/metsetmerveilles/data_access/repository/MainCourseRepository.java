package org.metsetmerveilles.data_access.repository;

import org.metsetmerveilles.data_access.entity.MainCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainCourseRepository extends JpaRepository<MainCourseEntity, Long> {
}
