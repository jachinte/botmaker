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
	 * @see com.rigiresearch.botmaker.model.JsonObject#json()
	 */
	@Override
	public String json() {
		return String.format("{\"statement\":\"%s\"}", this.statement);
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
		return this.statement;
	}

}
