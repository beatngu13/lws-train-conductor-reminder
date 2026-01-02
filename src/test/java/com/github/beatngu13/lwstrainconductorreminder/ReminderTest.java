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
						LocalDateTime.of(2026, 1, 5, 3, 0),
						"Sunny"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 6, 3, 0),
						"Amboss1919"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 7, 3, 0),
						"Xaver 123"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 8, 3, 0),
						"WretčhedEgg"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 9, 3, 0),
						"Killiz"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 10, 3, 0),
						"Sharky1972"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 11, 3, 0),
						"Emeral Four"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 12, 3, 0),
						"Sunny"
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
						LocalDateTime.of(2026, 1, 5, 3, 0),
						Cycle.R3_W1
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 6, 3, 0),
						Cycle.R3_W1
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 7, 3, 0),
						Cycle.R3_W1
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 8, 3, 0),
						Cycle.R3_W1
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 9, 3, 0),
						Cycle.R3_W1
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 10, 3, 0),
						Cycle.R3_W1
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 11, 3, 0),
						Cycle.R3_W1
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 12, 3, 0),
						Cycle.R3_W2
				)
		);
	}

	@Test
	void createWeeklyMessage() {
		var cut = new Reminder();

		var weeklyMessage = cut.createWeeklyMessage(LocalDateTime.of(2026, 1, 5, 3, 0));

		var weeklyMessageUnescaped = weeklyMessage.replaceAll("\\\\n", "\n");
		assertThat(weeklyMessageUnescaped).isEqualTo("""
				Please choose the train conductors and VIPs for *next week*.
				
				* **Mon.** <@242602233475104768>: R3 cycle (round 1)
				* **Tue.** <@810059372066897950>: R3 cycle (round 1)
				* **Wed.** <@1335254915819376641>: R3 cycle (round 1)
				* **Thu.** <@334730033132339200>: R3 cycle (round 1)
				* **Fri.** <@548515919333163023>: R3 cycle (round 1)
				* **Sat.** <@157903563803066368>: R3 cycle (round 1)
				* **Sun.** <@572154754675900434>: R3 cycle (round 1)
				
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
