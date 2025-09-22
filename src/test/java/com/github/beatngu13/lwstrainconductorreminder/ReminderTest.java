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
						LocalDateTime.of(2025, 12, 28, 3, 0),
						"Xaver 123"
				),
				Arguments.of(
						LocalDateTime.of(2025, 12, 29, 3, 0),
						"Sunny"
				),
				Arguments.of(
						LocalDateTime.of(2025, 12, 30, 3, 0),
						"Pavwla"
				),
				Arguments.of(
						LocalDateTime.of(2025, 12, 31, 3, 0),
						"WretčhedEgg"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 1, 3, 0),
						"Killiz"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 2, 3, 0),
						"Sharky1972"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 3, 3, 0),
						"Nervengift"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 4, 3, 0),
						"Emeral Four"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 5, 3, 0),
						"Dieser eine Lauch"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 6, 3, 0),
						"Amboss1919"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 7, 3, 0),
						"beatngu13"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 8, 3, 0),
						"Xaver 123"
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
						LocalDateTime.of(2025, 12, 28, 3, 0),
						Cycle.R3
				),
				Arguments.of(
						LocalDateTime.of(2025, 12, 29, 3, 0),
						Cycle.R3
				),
				Arguments.of(
						LocalDateTime.of(2025, 12, 30, 3, 0),
						Cycle.R3
				),
				Arguments.of(
						LocalDateTime.of(2025, 12, 31, 3, 0),
						Cycle.R3
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 1, 3, 0),
						Cycle.R3
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 2, 3, 0),
						Cycle.R3
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 3, 3, 0),
						Cycle.R4
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 4, 3, 0),
						Cycle.R4
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 5, 3, 0),
						Cycle.R4
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 6, 3, 0),
						Cycle.R4
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 7, 3, 0),
						Cycle.R4
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 8, 3, 0),
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

		assertThat(trainConductor.lwsUsername()).isEqualTo("Pavwla");
		assertThat(cycle).isEqualTo(Cycle.R4);
	}

}
