package com.github.beatngu13.lwstrainconductorreminder;

import com.github.beatngu13.lwstrainconductorreminder.Reminder.Cycle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ReminderTest {

	@Test
	void determineTrainConductor_lastDayOf2025() {
		var cut = new Reminder();
		var today = LocalDateTime.of(2025, 12, 31, 3, 0);

		var trainConductor = cut.determineTrainConductor(today);

		assertThat(trainConductor.lwsUsername()).isEqualTo("Carisma69");
	}

	@Test
	void determineTrainConductor_firstDayOf2026() {
		var cut = new Reminder();
		var today = LocalDateTime.of(2026, 1, 1, 3, 0);

		var trainConductor = cut.determineTrainConductor(today);

		assertThat(trainConductor.lwsUsername()).isEqualTo("beatngu13");
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
						Cycle.R4
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
						Cycle.R3
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
