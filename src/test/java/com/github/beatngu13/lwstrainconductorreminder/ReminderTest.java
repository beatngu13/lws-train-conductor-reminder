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
						LocalDateTime.of(2025, 10, 6, 3, 0),
						Cycle.R3_W1
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 7, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 8, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 9, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 10, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 11, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 12, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 13, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 14, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 15, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 16, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 17, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 18, 3, 0),
						Cycle.R4
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 19, 3, 0),
						Cycle.R4
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 20, 3, 0),
						Cycle.R4
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 21, 3, 0),
						Cycle.R4
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 22, 3, 0),
						Cycle.R4
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 23, 3, 0),
						Cycle.R4
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 24, 3, 0),
						Cycle.R4
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 25, 3, 0),
						Cycle.R4
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 26, 3, 0),
						Cycle.R4
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 27, 3, 0),
						Cycle.R4
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 28, 3, 0),
						Cycle.R4
				),
				Arguments.of(
						LocalDateTime.of(2025, 10, 29, 3, 0),
						Cycle.R3_W1
				)
		);
	}

	@Test
	void test() {
		var cut = new Reminder();

		var weeklyMessage = cut.createWeeklyMessage(LocalDateTime.of(2025, 11, 11, 3, 0));

		assertThat(weeklyMessage).isEqualTo("""
				Please pick your train conductor for next week.
				
				Mon. WretčhedEgg: R3 cycle (round 2)
				Tue. Killiz: R3 cycle (round 2)
				Wed. Sharky1972: R3 cycle (round 2)
				Thu. Nervengift: R4 cycle
				Fri. Emeral Four: R4 cycle
				Sat. Dieser eine Lauch: R4 cycle
				Sun. Amboss1919: R4 cycle
				
				Link: [Train conductor.xlsb](https://docs.google.com/spreadsheets/d/1eyDVzal1BUNez5Ffo4cT6wvQJrbKiJI1AdxTiH2VowQ/edit?gid=1854922681#gid=1854922681&range=A65https://docs.google.com/spreadsheets/d/1eyDVzal1BUNez5Ffo4cT6wvQJrbKiJI1AdxTiH2VowQ/edit?gid=1854922681#gid=1854922681&range=A65)""");
	}

	@Disabled
	@Test
	void smokeTest() {
		var today = LocalDateTime.now();
		var cut = new Reminder();

		var cycle = cut.determineCycle(today);
		var trainConductor = cut.determineTrainConductor(today);

		assertThat(trainConductor.lwsUsername()).isEqualTo("Pavwla");
		assertThat(cycle).isEqualTo(Cycle.R3_W1);
	}

}
