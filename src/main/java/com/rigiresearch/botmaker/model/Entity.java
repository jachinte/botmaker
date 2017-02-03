package com.rigiresearch.botmaker.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents an entity
 * 
 * @author Miguel Jiménez
 * @date 2017-02-02
 */
public final class Entity implements JsonObject {

	/**
	 * The name of this entity
	 */
	private final String name;

	/**
	 * The values allowed to this entity, along with possible synonyms
	 */
	private final Map<String, List<String>> values;

	/**
	 * Instantiates a named entity with its possible values
	 * 
	 * @param name
	 *            The name of the entity
	 * @param values
	 *            The values allowed to the entity
	 */
	public Entity(final String name, final Map<String, List<String>> values) {
		this.name = name;
		this.values = values;
	}

	/**
	 * Instantiates an entity with the name only
	 * 
	 * @param name
	 *            The name of the entity
	 */
	public Entity(final String name) {
		this(name, new HashMap<String, List<String>>());
	}

	/**
	 * Adds a value allowed to {@code this} entity
	 * 
	 * @param value
	 *            The value's name
	 * @param synonyms
	 *            Synonyms of the value's name
	 */
	public void addValue(String value, String... synonyms) {
		this.values.put(value, Arrays.asList(synonyms));
	}

	/*
	 * (non-Javadoc)
	 * @see com.rigiresearch.botmaker.model.JsonObject#toJson()
	 */
	@Override
	public String toJson() {
		return String.format("{\"entity\":\"%s\",\"values\":[%s]}", 
				name.replace("\"", "\\\""),
				this.values.keySet().stream().map(key -> {
					String list = Entity.this.values
							.get(key)
							.stream()
							.map(synonym -> 
								String.format("\"%s\"", synonym.replace("\"", "\\\""))
							)
							.collect(Collectors.joining(","));
					return String.format("{\"value\":\"%s\",\"synonyms\":[%s]}", 
							key.replace("\"", "\\\""), list);
				})
				.collect(Collectors.joining(",")));
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.rigiresearch.botmaker.model.JsonObject#fromJson(java.lang.String)
	 */
	@Override
	public void fromJson(String json) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s: [%s]", name, this.values.keySet().stream().map(key -> {
			String list = Entity.this.values
					.get(key)
					.stream()
					.collect(Collectors.joining(","));
			return String.format("%s: %s", key, list);
		})
		.collect(Collectors.joining("; ")));
	}

}
