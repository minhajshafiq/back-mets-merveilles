package org.metsetmerveilles.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PriceTest {

    @Test
    void shouldBuildValidPrice() {
        Price price = new Price(1.12);

        assertThat(price.value()).isEqualTo(1.12);
    }
    @ParameterizedTest
    @MethodSource("invalidPriceProvider")
    void shouldBuildInvalidPrice(double invalidPrice) {

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Price(invalidPrice));
    }

    public static Stream<Arguments> invalidPriceProvider() {
        return Stream.of(
                Arguments.of(-1.0),
                Arguments.of(0.0),
                Arguments.of(-3.2)
        );
    }
}
