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
	 * @see com.rigiresearch.botmaker.model.JsonObject#json()
	 */
	@Override
	public String json() {
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
	 * @see com.rigiresearch.botmaker.model.JsonObject#configureFromJson(java.lang.String)
	 */
	@Override
	public void configureFromJson(String json) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s", this.name);
	}

}
