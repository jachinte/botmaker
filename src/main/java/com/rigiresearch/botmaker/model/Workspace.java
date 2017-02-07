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
	 * @see com.rigiresearch.botmaker.model.JsonObject#toJson()
	 */
	@Override
	public String toJson() {
		String entities = this.data.keySet()
				.stream()
				.map(entity -> entity.toJson())
				.collect(Collectors.joining(","));
		return String.format("{\"name\":\"%s\", \"entities\": [%s]}", name,
				entities);
	}

	/*
	 * (non-Javadoc)
	 * @see com.rigiresearch.botmaker.model.JsonObject#fromJson(java.lang.String)
	 */
	@Override
	public void fromJson(String json) {
		throw new UnsupportedOperationException();
	}

}
