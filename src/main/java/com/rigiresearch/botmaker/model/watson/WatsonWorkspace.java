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
package com.rigiresearch.botmaker.model.watson;

import com.rigiresearch.botmaker.model.Question;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a Watson conversation workspace.
 * 
 * @author Miguel Jiménez
 * @date 2017-02-02
 */
public final class WatsonWorkspace implements JsonObject {

	/**
     * Serial version UID.
     */
    private static final long serialVersionUID = -3706231931448542803L;

    /**
	 * This workspace's name.
	 */
	private final String name;

	/**
	 * The entities and their corresponding question.
	 */
	private final Map<WatsonEntity, Question> data;

	/**
	 * Instantiates a workspace with its entities and their associated
	 * question.
	 * 
	 * @param name this workspace's name
	 * @param data this workspace's data
	 */
	public WatsonWorkspace(final String name,
	    final Map<WatsonEntity, Question> data) {
		this.name = name;
		this.data = data;
	}
	
	/**
	 * Calculates the superset of this workspace's entities.
	 * 
	 * @return The superset
	 */
	public List<Set<WatsonEntity>> superSet() {
		List<WatsonEntity> entities = this.data
		    .keySet()
		    .stream()
		    .collect(Collectors.toList());
		int allMasks = (1 << entities.size());
		List<Set<WatsonEntity>> superSet = new ArrayList<Set<WatsonEntity>>();
		for (int i = 1; i < allMasks; i++) {
			Set<WatsonEntity> set = new HashSet<WatsonEntity>();
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
		return String.format(
		    "{\"name\":\"%s\", \"entities\": [%s]}",
		    name,
			entities
        );
	}

}
