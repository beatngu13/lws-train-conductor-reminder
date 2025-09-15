package com.github.beatngu13.lwstrainconductorreminder;

import com.github.beatngu13.lwstrainconductorreminder.Reminder.Cycle;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
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
						LocalDateTime.of(2025, 12, 25, 3, 0),
						"beatngu13"
				),
				Arguments.of(
						LocalDateTime.of(2025, 12, 26, 3, 0),
						"Xaver 123"
				),
				Arguments.of(
						LocalDateTime.of(2025, 12, 27, 3, 0),
						"Sunny"
				),
				Arguments.of(
						LocalDateTime.of(2025, 12, 28, 3, 0),
						"Nervengift"
				),
				Arguments.of(
						LocalDateTime.of(2025, 12, 29, 3, 0),
						"Emeral Four"
				),
				Arguments.of(
						LocalDateTime.of(2025, 12, 30, 3, 0),
						"Pavwla"
				),
				Arguments.of(
						LocalDateTime.of(2025, 12, 31, 3, 0),
						"Dieser eine Lauch"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 1, 3, 0),
						"Amboss1919"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 2, 3, 0),
						"beatngu13"
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
						LocalDateTime.of(2025, 6, 10, 3, 0),
						Cycle.R4
				),
				Arguments.of(
						LocalDateTime.of(2025, 6, 11, 3, 0),
						Cycle.R3
				),
				Arguments.of(
						LocalDateTime.of(2025, 6, 12, 3, 0),
						Cycle.R3
				),
				Arguments.of(
						LocalDateTime.of(2025, 6, 13, 3, 0),
						Cycle.R3
				),
				Arguments.of(
						LocalDateTime.of(2025, 6, 14, 3, 0),
						Cycle.R3
				),
				Arguments.of(
						LocalDateTime.of(2025, 6, 15, 3, 0),
						Cycle.R3
				),
				Arguments.of(
						LocalDateTime.of(2025, 6, 16, 3, 0),
						Cycle.R3
				),
				Arguments.of(
						LocalDateTime.of(2025, 6, 17, 3, 0),
						Cycle.R3
				),
				Arguments.of(
						LocalDateTime.of(2025, 6, 18, 3, 0),
						Cycle.R3
				),
				Arguments.of(
						LocalDateTime.of(2025, 6, 19, 3, 0),
						Cycle.R4
				)
		);
	}

	@Disabled
	@Test
	void smokeTest() {
		var today = LocalDateTime.now();
		var cut = new Reminder();

		var cycle = cut.determineCycle(today);
		var trainConductor = cut.determineTrainConductor(today);

		assertThat(trainConductor.lwsUsername()).isEqualTo("Nervengift");
		assertThat(cycle).isEqualTo(Cycle.R3);
	}

}
