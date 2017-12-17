package pl.kluczify.server.utils;

import com.google.gson.Gson;

public class JsonConverter {

	private static Gson gson;

	private JsonConverter() {
		gson = new Gson();

		// gson settings
	}

	public static String toJson(Jsonable  object) {
		return gson.toJson(object);
	}

	public static <ReturnClass extends Jsonable> ReturnClass fromJson(String json, Class<ReturnClass> returnClass) {
		return gson.fromJson(json, returnClass);
	}

}
