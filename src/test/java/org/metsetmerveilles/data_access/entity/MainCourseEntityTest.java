package org.metsetmerveilles.data_access.entity;

import org.junit.jupiter.api.Test;
import org.metsetmerveilles.domain.model.MainCourse;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MainCourseEntityTest {

    @Test
    void ShouldBuildFromDomainWithoutMenu() {
        MainCourse mainCourse = new MainCourse(1L, "name", "description", 1.0, "imageUrl", Optional.empty());
        org.metsetmerveilles.data_access.entity.MainCourseEntity expected = new org.metsetmerveilles.data_access.entity.MainCourseEntity();
        expected.setId(1L);
        expected.setName("name");
        expected.setDescription("description");
        expected.setPrice(1.0);
        expected.setImageUrl("imageUrl");
        expected.setMenu(null);

        org.metsetmerveilles.data_access.entity.MainCourseEntity actual = org.metsetmerveilles.data_access.entity.MainCourseEntity.fromDomain(mainCourse);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

}