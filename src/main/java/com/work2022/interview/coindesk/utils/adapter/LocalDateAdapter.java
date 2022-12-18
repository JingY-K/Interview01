package com.work2022.interview.coindesk.utils.adapter;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

	DateTimeFormatter formatter;

//	public LocalDateAdapter() {
//	}
//	
//	public LocalDateAdapter(DateTimeFormatter formatter) {
//		this.formatter = formatter;
//	}

	@Override
	public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(date.format(formatter));
	}

	@Override
	public LocalDate deserialize(JsonElement element, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		String timestamp = element.getAsJsonPrimitive().getAsString();
		return LocalDate.parse(timestamp, formatter);
	}

}
