package com.github.beatngu13.lwstrainconductorreminder;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class Reminder {

	public record TrainConductor(String discordUserId, String lwsUsername) {

		public boolean hasDiscordAccount() {
			return discordUserId != null;
		}

	}

	public enum Cycle {

		R4,
		R3_W1,
		R3_W2;

		@Override
		public String toString() {
			return switch (this) {
				case R4 -> "R4";
				case R3_W1 -> "R3 (week 1)";
				case R3_W2 -> "R3 (week 2)";
			};
		}

	}

	private static final List<TrainConductor> TRAIN_CONDUCTORS = List.of(
			new TrainConductor("413827079688421376", "Nervengift"),
			new TrainConductor("572154754675900434", "Emeral Four"),
			new TrainConductor("568776713455271946", "Dieser eine Lauch"),
			new TrainConductor("810059372066897950", "Amboss1919"),
			new TrainConductor("284646412233211906", "beatngu13"),
			new TrainConductor("1335254915819376641", "Xaver 123"),
			new TrainConductor("242602233475104768", "Sunny"),
			new TrainConductor("207834501651496961", "Pavwla"),
			new TrainConductor("334730033132339200", "WretčhedEgg"),
			new TrainConductor("548515919333163023", "Killiz"),
			new TrainConductor("157903563803066368", "Sharky1972")
	);

	private static final LocalDateTime REFERENCE_DATE = LocalDateTime.of(2025, 2, 27, 3, 0);
	private static final int OFFSET = -2;
	private static final String APP_TOKEN = System.getenv("APP_TOKEN");
	private static final String CHANNEL_ID = System.getenv("CHANNEL_ID");
	private static final String CHANNEL_URI = "https://discord.com/api/v10/channels/" + CHANNEL_ID + "/messages";

	public static void main(String[] args) {
		var reminder = new Reminder();
		LocalDateTime today = reminder.getToday();
		var trainConductor = reminder.determineTrainConductor(today);
		var cycle = reminder.determineCycle(today);
		System.out.println(reminder.createMessage(cycle, trainConductor.lwsUsername()));
		if (isDryRun(args)) {
			return;
		}
		reminder.postOnDiscord(trainConductor, cycle);
	}

	private static boolean isDryRun(String[] args) {
		if (args.length == 0) {
			return false;
		}
		return Boolean.parseBoolean(args[0]);
	}

	private LocalDateTime getToday() {
		ZoneId europeBerlin = ZoneId.of("Europe/Berlin");
		return LocalDateTime.now(europeBerlin);
	}

	public TrainConductor determineTrainConductor(LocalDateTime today) {
		int index = getDaysSinceReferenceDate(today) % TRAIN_CONDUCTORS.size();
		return TRAIN_CONDUCTORS.get(index);
	}

	public Cycle determineCycle(LocalDateTime today) {
		int quotient = getDaysSinceReferenceDate(today) / TRAIN_CONDUCTORS.size();
		int cycle = quotient % 3;
		return switch (cycle) {
			case 0 -> Cycle.R4;
			case 1 -> Cycle.R3_W1;
			case 2 -> Cycle.R3_W2;
			default ->
					throw new IllegalStateException("Unexpected cycle: " + cycle + " (0 = R4, 1 = R3_W1, 2 = R3_W2)");
		};
	}

	private int getDaysSinceReferenceDate(LocalDateTime today) {
		return Math.toIntExact(Duration.between(REFERENCE_DATE, today).toDays()) + OFFSET;
	}

	public void postOnDiscord(TrainConductor trainConductor, Cycle cycle) {
		try (HttpClient client = HttpClient.newHttpClient()) {
			var requestBody = createRequestBody(trainConductor, cycle);
			var httpRequest = createHttpRequest(requestBody);
			System.out.println("Posting on discord...");
			var httpResponse = client.send(httpRequest, BodyHandlers.ofString());
			System.out.println("Request body: " + requestBody);
			System.out.println("Response body: " + httpResponse.body());
			System.out.println("Status code: " + httpResponse.statusCode());
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
	}

	private String createMessage(Cycle cycle, String userString) {
		String verb = cycle == Cycle.R4 ? "is" : "chooses";
		return String.format("%s cycle: %s %s today's train conductor.", cycle, userString, verb);
	}

	private String createRequestBody(TrainConductor trainConductor, Cycle cycle) {
		String userString = trainConductor.hasDiscordAccount()
				? "<@" + trainConductor.discordUserId() + ">"
				: trainConductor.lwsUsername() + " (has no Discord account)";
		return "{\"content\": \"" + createMessage(cycle, userString) + "\"}";
	}

	private HttpRequest createHttpRequest(String requestBody) {
		return HttpRequest.newBuilder()
				.uri(URI.create(CHANNEL_URI))
				.timeout(Duration.ofSeconds(10))
				.header("Content-Type", "application/json")
				.header("Accept", "application/json")
				.header("Authorization", "Bot " + APP_TOKEN)
				.POST(BodyPublishers.ofString(requestBody))
				.build();
	}

}
