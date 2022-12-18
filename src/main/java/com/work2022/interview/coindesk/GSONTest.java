package com.work2022.interview.coindesk;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.work2022.interview.coindesk.model.CoindeskTime;
import com.work2022.interview.coindesk.utils.adapter.LocalDateAdapter;
import com.work2022.interview.coindesk.utils.adapter.LocalDateTimeAdapter;

public class GSONTest {

	public static void main(String[] args) {
		System.out.println("it's main.");

		String timeJson = "{\n" + "    \"updated\": \"Dec 18, 2022 09:41:00 UTC\",\n"
				+ "    \"updatedISO\": \"2022-12-18T09:41:00+00:00\",\n"
				+ "    \"updateduk\": \"Dec 18, 2022 at 09:41 GMT\"\n" + "  }";
		
		timeJson = "{\"updated\":\"Dec 18, 2022 09:41:00 UTC\",\"updatedISO\":\"2022-12-18T09:41:00+00:00\",\"updateduk\":\"Dec 18, 2022 at 09:41 GMT\"}";
		
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(LocalDate.class, new LocalDateAdapter(DateTimeFormatter.ISO_DATE))
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter(DateTimeFormatter.ISO_DATE_TIME))
				.setPrettyPrinting().create();

		CoindeskTime cdTime = gson.fromJson(timeJson, new TypeToken<CoindeskTime>() {
		}.getType());

		System.out.println(
				"time = " + cdTime + ", updated = " + cdTime.getUpdated() + ", updatedISO = " + cdTime.getUpdatedISO());
	}
}
