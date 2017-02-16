package com.rigiresearch.botmaker;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.rigiresearch.botmaker.model.Entity;
import com.rigiresearch.botmaker.model.OpenQuestion;
import com.rigiresearch.botmaker.model.Question;
import com.rigiresearch.botmaker.model.Workspace;

public class Example {
	
	public Map<Entity, Question> data() {
		Map<Entity, Question> data = new HashMap<Entity, Question>();
		// Food type
		data.put(
				new Entity("food_type",
						values("breakfast", "main course", "dessert", "side dish", "appetizer", "salad", "bread",
								"lunch", "dinner", "drink")),
				new OpenQuestion("Which type of food would you like to cook?"));
		// Cuisine
		data.put(new Entity("cuisine", values("african", "american", "british", "cajun", "french")),
				new OpenQuestion("Which type of cuisine are you feeling like cooking today?"));
		// Whatever
		data.put(new Entity("whatever", values("value1", "value2")),
				new OpenQuestion("Whatever question?"));
		data.put(new Entity("another", values("value3", "value4")),
				new OpenQuestion("Another whatever question?"));
		return data;
	}
	
	public Map<String, List<String>> values(String... names) {
		Map<String, List<String>> values = new HashMap<String, List<String>>();
		Stream.of(names).forEach(value -> values.put(value, Collections.emptyList()));
		return values;
	}
	
	public static void main(String[] args) {
		Example main = new Example();
		Workspace workspace = new Workspace("recipes", main.data());
		System.out.println(workspace.json());
		System.out.println(workspace.superSet());
	}

}
