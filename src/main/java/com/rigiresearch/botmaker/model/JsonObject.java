package com.rigiresearch.botmaker.model;

/**
 * Utility interface to enforce JSON support
 * 
 * @author Miguel Jiménez
 * @date 2017-02-02
 */
public interface JsonObject {

	/**
	 * @return The JSON representation of {@code this} object
	 */
	public String toJson();

	/**
	 * Populates this object's data from its JSON representation
	 * 
	 * @param json
	 *            The JSON representation of {@code this} object
	 */
	public void fromJson(String json);

}
