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
		Map<String, List<String>> typeValues = new HashMap<String, List<String>>();
		Stream.of("breakfast", "main course", "dessert", "side dish",
				"appetizer", "salad", "bread", "lunch", "dinner", "drink")
				.forEach(value -> typeValues.put(value, Collections.emptyList()));
		data.put(new Entity("food_type", typeValues), new OpenQuestion("Which type of food would you like to cook?"));
		// Cuisine
		Map<String, List<String>> cuisineValues = new HashMap<String, List<String>>();
		Stream.of("african", "american", "british", "cajun", "french") // ...
				.forEach(value -> cuisineValues.put(value, Collections.emptyList()));
		data.put(new Entity("cuisine", cuisineValues), new OpenQuestion("Which type of cuisine are you feeling like cooking today?"));
		return data;
	}
	
	public static void main(String[] args) {
		Example main = new Example();
		Workspace workspace = new Workspace("recipes", main.data());
		System.out.println(workspace.toJson());
	}

}
