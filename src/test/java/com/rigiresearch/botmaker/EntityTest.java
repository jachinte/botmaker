/**
 * Copyright 2017 University of Victoria
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */
package com.rigiresearch.botmaker;

import com.rigiresearch.botmaker.model.Entity;
import com.rigiresearch.botmaker.model.watson.WatsonEntity;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @author Miguel Jiménez
 * @date 2017-02-02
 */
public final class EntityTest {
	
	private Entity getEntity1() {
		Entity.Simple greeting = new Entity.Simple("greeting");
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
		return new Entity.Simple("food_type", values);
	}

	/**
	 * Entity can transform an instance to its JSON representation
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
				+ "}", new WatsonEntity(getEntity1()).json());
	}
	
	/**
	 * Entity can transform an instance to its JSON representation
	 */
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
				+ "}", new WatsonEntity(getEntity2()).json());
	}

}
