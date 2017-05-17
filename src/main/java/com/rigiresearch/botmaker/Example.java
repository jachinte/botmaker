package com.rigiresearch.botmaker;


import com.rigiresearch.botmaker.model.Entity;
import com.rigiresearch.botmaker.model.OpenEnded;
import com.rigiresearch.botmaker.model.Question;
import com.rigiresearch.botmaker.model.watson.WatsonEntity;
import com.rigiresearch.botmaker.model.watson.WatsonWorkspace;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example {
	
	public Map<Entity, Question> data() {
		Map<Entity, Question> data = new HashMap<Entity, Question>();
		// Food type
		data.put(
				new Entity.Simple("food_type",
						values("breakfast", "main course", "dessert", "side dish", "appetizer", "salad", "bread",
								"lunch", "dinner", "drink")),
				new OpenEnded("Which type of food would you like to cook?"));
		// Cuisine
		data.put(new Entity.Simple("cuisine", values("african", "american", "british", "cajun", "french")),
				new OpenEnded("Which type of cuisine are you feeling like cooking today?"));
		// Whatever
		data.put(new Entity.Simple("whatever", values("value1", "value2")),
				new OpenEnded("Whatever question?"));
		data.put(new Entity.Simple("another", values("value3", "value4")),
				new OpenEnded("Another whatever question?"));
		return data;
	}
	
	public Map<WatsonEntity, Question> decoratedData() {
	    Map<Entity, Question> data = data();
	    return data
	        .keySet()
	        .stream()
	        .collect(
	            Collectors.toMap(
	                key -> new WatsonEntity(key),
	                key -> data.get(key) // FIXME DOES THIS WORK? -> hashcode, equals of class Simple
	            )
	        );
	}
	
	public Map<String, List<String>> values(String... names) {
		Map<String, List<String>> values = new HashMap<String, List<String>>();
		Stream.of(names).forEach(value -> values.put(value, Collections.emptyList()));
		return values;
	}
	
	public static void main(String[] args) {
		Example main = new Example();
		WatsonWorkspace workspace = new WatsonWorkspace("recipes", main.decoratedData());
		System.out.println(workspace.json());
		System.out.println(workspace.superSet());
	}

}
