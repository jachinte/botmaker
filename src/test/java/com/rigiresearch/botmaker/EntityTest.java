/**
 * 
 */
package com.rigiresearch.botmaker;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.Test;

import com.rigiresearch.botmaker.model.Entity;

/**
 * @author Miguel Jiménez
 * @date 2017-02-02
 */
public final class EntityTest {
	
	private Entity getEntity1() {
		Entity greeting = new Entity("greeting");
		greeting.addValue("hello", "good afternoon", "good evening",
				"good morning", "hi", "howdy");
		greeting.addValue("how's it going?", "how are things?", "how are you?",
				"how’s everything?", "how’s life?", "what’s going on?",
				"what’s new?", "what’s up?");
		return greeting;
	}
	
	private Entity getEntity2() {
		Map<String, List<String>> values = new HashMap<String, List<String>>();
		Stream.of("breakfast", "main course", "dessert", "side dish",
				"appetizer", "salad", "bread", "lunch", "dinner", "drink")
				.forEach(value -> values.put(value, Collections.emptyList()));
		return new Entity("food_type", values);
	}

	/**
	 * Test method for {@link com.rigiresearch.botmaker.model.Entity#toJson()}.
	 */
	@Test
	public void testJson1() {
		assertEquals("{"
				+ "\"entity\":\"greeting\","
				+ "\"values\":["
				+ 		"{"
				+ 			"\"value\":\"how's it going?\","
				+ 			"\"synonyms\":["
				+ 				"\"how are things?\","
				+ 				"\"how are you?\","
				+ 				"\"how’s everything?\","
				+ 				"\"how’s life?\","
				+ 				"\"what’s going on?\","
				+ 				"\"what’s new?\","
				+ 				"\"what’s up?\""
				+ 			"]"
				+ 		"},"
				+ 		"{"
				+ 			"\"value\":\"hello\","
				+ 			"\"synonyms\":["
				+ 				"\"good afternoon\","
				+ 				"\"good evening\","
				+				"\"good morning\","
				+ 				"\"hi\","
				+ 				"\"howdy\""
				+ 			"]"
				+ 		"}"
				+ 	"]"
				+ "}", getEntity1().toJson());
	}
	
	@Test
	public void testJson2() {
		assertEquals("{"
				+ 	"\"entity\":\"food_type\","
				+ 	"\"values\":["
				+ 		"{"
				+ 			"\"value\":\"side dish\","
				+ 			"\"synonyms\":[]"
				+ 		"},"
				+ 		"{"
				+ 			"\"value\":\"bread\","
				+ 			"\"synonyms\":[]"
				+ 		"},"
				+ 		"{"
				+ 			"\"value\":\"lunch\","
				+ 			"\"synonyms\":[]"
				+ 		"},"
				+ 		"{"
				+ 			"\"value\":\"main course\","
				+ 			"\"synonyms\":[]"
				+ 		"},"
				+ 		"{"
				+ 			"\"value\":\"salad\","
				+ 			"\"synonyms\":[]"
				+ 		"},"
				+		"{"
				+ 			"\"value\":\"dessert\","
				+ 			"\"synonyms\":[]"
				+ 		"},"
				+ 		"{"
				+ 			"\"value\":\"appetizer\","
				+ 			"\"synonyms\":[]"
				+ 		"},"
				+ 		"{"
				+ 			"\"value\":\"breakfast\","
				+ 			"\"synonyms\":[]"
				+ 		"},"
				+ 		"{"
				+ 			"\"value\":\"dinner\","
				+ 			"\"synonyms\":[]"
				+ 		"},"
				+ 		"{"
				+ 			"\"value\":\"drink\","
				+ 			"\"synonyms\":[]"
				+ 		"}"
				+ 	"]"
				+ "}", getEntity2().toJson());
	}

}
