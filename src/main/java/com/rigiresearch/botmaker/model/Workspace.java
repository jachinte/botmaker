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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a Watson conversation workspace
 * 
 * @author Miguel Jiménez
 * @date 2017-02-02
 */
public final class Workspace implements JsonObject {
	
	/**
	 * This workspace's name
	 */
	private final String name;
	
	/**
	 * The entities and their corresponding question
	 */
	private final Map<Entity, Question> data;
	
	/**
	 * Instantiates a workspace with its entities and their associated question
	 * 
	 * @param data
	 */
	public Workspace(final String name, final Map<Entity, Question> data) {
		this.name = name;
		this.data = data;
	}
	
	/**
	 * Calculates the superset of this workspace's entities
	 * 
	 * @return The superset
	 */
	public List<Set<Entity>> superSet() {
		List<Entity> entities = this.data.keySet().stream().collect(Collectors.toList());
		int allMasks = (1 << entities.size());
		List<Set<Entity>> superSet = new ArrayList<Set<Entity>>();
		for (int i = 1; i < allMasks; i++) {
			Set<Entity> set = new HashSet<Entity>();
		    for (int j = 0; j < entities.size(); j++)
		    	if ((i & (1 << j)) > 0)
			        set.add(entities.get(j));
		    superSet.add(set);
		}
		return superSet;
	}

	/*
	 * (non-Javadoc)
	 * @see com.rigiresearch.botmaker.model.JsonObject#json()
	 */
	@Override
	public String json() {
		String entities = this.data.keySet()
				.stream()
				.map(entity -> entity.json())
				.collect(Collectors.joining(","));
		return String.format("{\"name\":\"%s\", \"entities\": [%s]}", name,
				entities);
	}

	/*
	 * (non-Javadoc)
	 * @see com.rigiresearch.botmaker.model.JsonObject#configureFromJson(java.lang.String)
	 */
	@Override
	public void configureFromJson(String json) {
		throw new UnsupportedOperationException();
	}

}
