package com.github.beatngu13.lwstrainconductorreminder;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReminderTest {

	@Test
	void determineTodaysTrainConductor() {
		var cut = new Reminder();

		var trainConductor = cut.determineTodaysTrainConductor();

		assertThat(trainConductor).isNotNull();
	}

}
