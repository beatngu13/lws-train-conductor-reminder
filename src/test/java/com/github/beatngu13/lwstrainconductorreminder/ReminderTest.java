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
						LocalDateTime.of(2026, 1, 12, 3, 0),
						"Sunny"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 13, 3, 0),
						"Amboss1919"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 14, 3, 0),
						"Bloodångel"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 15, 3, 0),
						"Emeral Four"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 16, 3, 0),
						"Jimbô"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 17, 3, 0),
						"Killiz"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 18, 3, 0),
						"Ohob"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 19, 3, 0),
						"Sharky1972"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 20, 3, 0),
						"topnut"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 21, 3, 0),
						"WretčhedEgg"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 22, 3, 0),
						"Xaver 123"
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 23, 3, 0),
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
						LocalDateTime.of(2026, 1, 12, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 13, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 14, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 15, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 16, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 17, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 18, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 19, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 20, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 21, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 22, 3, 0),
						Cycle.R3_W2
				),
				Arguments.of(
						LocalDateTime.of(2026, 1, 23, 3, 0),
						Cycle.R4
				)
		);
	}

	@Test
	void createWeeklyMessage() {
		var cut = new Reminder();

		var weeklyMessage = cut.createWeeklyMessage(LocalDateTime.of(2026, 1, 14, 3, 0));

		var weeklyMessageUnescaped = weeklyMessage.replaceAll("\\\\n", "\n");
		assertThat(weeklyMessageUnescaped).isEqualTo("""
				Please choose the train conductors and VIPs for *next week*.
				
				* **Mon.** <@157903563803066368>: R3 cycle (round 2)
				* **Tue.** <@1278403662556958782>: R3 cycle (round 2)
				* **Wed.** <@334730033132339200>: R3 cycle (round 2)
				* **Thu.** <@1335254915819376641>: R3 cycle (round 2)
				* **Fri.** <@242602233475104768>: R4 cycle
				* **Sat.** <@810059372066897950>: R4 cycle
				* **Sun.** <@1302749583931084870>: R4 cycle
				
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
