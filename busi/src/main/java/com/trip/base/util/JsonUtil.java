package com.trip.base.util;

import com.google.gson.*;
import com.google.gson.internal.StringMap;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;


public class JsonUtil {
	private static final Logger logger = Logger.getLogger(JsonUtil.class.getName());

	private static MapTypeAdapter mapTypeAdapter = new MapTypeAdapter();

	private static StringTypeAdapter stringTypeAdapter = new StringTypeAdapter();

	public static final String DATE_FORMAT = "yyyy-MM-dd";


	public static String encodeCmd(Object cmd){
		return GsonUtil.getDateFormatGson().toJson(cmd);
	}

	public static Gson getGson(String dateFormat) {
		return new GsonBuilder()
				.registerTypeAdapter(Map.class, mapTypeAdapter)
				.registerTypeAdapter(String.class, stringTypeAdapter)
				.disableHtmlEscaping().serializeNulls()
				.setDateFormat(dateFormat).create();
	}

	public static Gson getGson() {
		return getGson(DATE_FORMAT);
	}

	/**
	 * 修正Gson转map时， 数字按Double类型转换
	 * @author 范秋海
	 *
	 */
	private static class MapTypeAdapter implements JsonDeserializer<Map<String, Object>> {

		@Override
		public Map<String, Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			return deserialize(json, new HashMap<String, Object>());
		}

		private Map<String, Object> deserialize(JsonElement element, Map<String, Object> map) {
			JsonObject json = element.getAsJsonObject();
			Set<Map.Entry<String, JsonElement>> entrySet = json.entrySet();
			for (Map.Entry<String, JsonElement> entry : entrySet) {
				if(entry.getValue().isJsonPrimitive()) {
					JsonPrimitive primitive = (JsonPrimitive) entry.getValue();
					if(primitive.isString()) {
						map.put(entry.getKey(), entry.getValue().getAsString());
					} else {
						map.put(entry.getKey(), entry.getValue().getAsString());
					}
				} else if(entry.getValue().isJsonNull()) {
					map.put(entry.getKey(), null);
				} else if(entry.getValue().isJsonArray()) {
					JsonArray array = (JsonArray) entry.getValue();
					List<Object> list = new ArrayList<Object>();
					for(int i = 0; i < array.size(); i++) {
						JsonElement el = array.get(i);
						list.add(deserialize(el, new StringMap<Object>()));
					}
					map.put(entry.getKey(), list);
				} else {
					map.put(entry.getKey(), deserialize(entry.getValue(), new StringMap<Object>()));
				}
			}
			return map;
		}

	}

	private static class StringTypeAdapter extends TypeAdapter<String> {

		@Override
		public void write(JsonWriter out, String value) throws IOException {
			if (value == null) {
				out.value(""); // 序列化时将 null 转为 ""
			} else {
				out.value(value);
			}
		}

		@Override
		public String read(JsonReader in) throws IOException {
			if (in.peek() == JsonToken.NULL) {
				in.nextNull();
				return null;
			}
			String str = in.nextString();
			if (str.equals("")) {
				return null;
			} else {
				return str;
			}
		}


	}

//	public static void main(String[] args) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("id",1);
//		Gson gson = JsonUtil.getGson();
//	}
}
