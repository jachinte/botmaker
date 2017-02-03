package com.rigiresearch.botmaker.model;

/**
 * Represents an open question
 * 
 * @author Miguel Jiménez
 * @date 2017-02-02
 */
public final class OpenQuestion implements Question {

	/**
	 * This question's statement
	 */
	private final String statement;

	/**
	 * Instantiates an open question based on a statement
	 * 
	 * @param statement
	 *            This question's statement
	 */
	public OpenQuestion(final String statement) {
		this.statement = statement;
	}

	/*
	 * (non-Javadoc)
	 * @see com.rigiresearch.botmaker.model.JsonObject#toJson()
	 */
	@Override
	public String toJson() {
		return String.format("{\"statement\":\"%s\"}", this.statement);
	}

	/*
	 * (non-Javadoc)
	 * @see com.rigiresearch.botmaker.model.JsonObject#fromJson(java.lang.String)
	 */
	@Override
	public void fromJson(String json) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String toString() {
		return this.statement;
	}

}
