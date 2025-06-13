package com.github.beatngu13.lwstrainconductorreminder;

import com.github.beatngu13.lwstrainconductorreminder.Reminder.Cycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ReminderTest {

	@ParameterizedTest
	@MethodSource
	void determineTrainConductor(LocalDateTime today, String expectedLwsUsername) {
		var cut = new Reminder();

		var trainConductor = cut.determineTrainConductor(today);

		assertThat(trainConductor.lwsUsername()).isEqualTo(expectedLwsUsername);
	}

	static Stream<Arguments> determineTrainConductor() {
		return Stream.of(
				Arguments.of(
						LocalDateTime.of(2025, 12, 23, 3, 0),
						"PopeofNope"
				),
				Arguments.of(
						LocalDateTime.of(2025, 12, 24, 3, 0),
						"DaMaddin"
				),
				Arguments.of(
						LocalDateTime.of(2025, 12, 25, 3, 0),
						"Emeral Four"
				),
				Arguments.of(
						LocalDateTime.of(2025, 12, 26, 3, 0),
						"Pavwla"
				),
				Arguments.of(
						LocalDateTime.of(2025, 12, 27, 3, 0),
						"Chrille79"
				),
				Arguments.of(
						LocalDateTime.of(2025, 12, 28, 3, 0),
						"Carisma69"
				),
				Arguments.of(
						LocalDateTime.of(2025, 12, 29, 3, 0),
						"beatngu13"
				),
				Arguments.of(
						LocalDateTime.of(2025, 12, 30, 3, 0),
						"Dieser eine Lauch"
				),
				Arguments.of(
						LocalDateTime.of(2025, 12, 31, 3, 0),
						"Nervengift"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 1, 3, 0),
						"PopeofNope"
				)
		);
	}

	@ParameterizedTest
	@MethodSource
	void determineCycle(LocalDateTime today, Cycle expectedCycle) {
		var cut = new Reminder();

		var actualCycle = cut.determineCycle(today);

		assertThat(actualCycle).isEqualTo(expectedCycle);
	}

	static Stream<Arguments> determineCycle() {
		return Stream.of(
				Arguments.of(
						LocalDateTime.of(2025, 3, 18, 3, 0),
						Cycle.R3
				),
				Arguments.of(
						LocalDateTime.of(2025, 3, 19, 3, 0),
						Cycle.R3
				),
				Arguments.of(
						LocalDateTime.of(2025, 3, 20, 3, 0),
						Cycle.R3
				),
				Arguments.of(
						LocalDateTime.of(2025, 3, 28, 3, 0),
						Cycle.R4
				),
				Arguments.of(
						LocalDateTime.of(2025, 3, 29, 3, 0),
						Cycle.R4
				),
				Arguments.of(
						LocalDateTime.of(2025, 3, 30, 3, 0),
						Cycle.R4
				)
		);
	}

}
