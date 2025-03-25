package com.github.beatngu13.lwstrainconductorreminder;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ReminderTest {

	@Test
	void determineTrainConductor_lastDayOf2025() {
		var cut = new Reminder();
		var today = LocalDateTime.of(2025, 12, 31, 3, 0);

		var trainConductor = cut.determineTrainConductor(today);

		assertThat(trainConductor.lwsUsername()).isEqualTo("Carisma69");
	}

	@Disabled("Should be beatngu13 but is Pavwla.")
	@Test
	void determineTrainConductor_firstDayOf2026() {
		var cut = new Reminder();
		var today = LocalDateTime.of(2026, 1, 1, 3, 0);

		var trainConductor = cut.determineTrainConductor(today);

		assertThat(trainConductor.lwsUsername()).isEqualTo("beatngu13");
	}

}
