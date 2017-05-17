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

/**
 * Represents an entity.
 * 
 * @author Miguel Jiménez
 * @date 2017-02-02
 */
public interface Entity extends Term {

    /**
     * Returns this entity's name.
     * @return a name
     */
    String name();

    /**
     * Returns this entity's values and their synonyms.
     * @return a map of values and synonyms
     */
    Map<String, List<String>> values();
    
    /**
     * Adds a value allowed to {@code this} entity.
     * 
     * @param value
     *            A possible value of this entity
     * @param synonyms
     *            Synonyms of the given value
     */
    void addValue(String value, List<String> synonyms);


    /**
     * Simple entity.
     * 
     * @author Miguel Jimenez (miguel@uvic.ca)
     * @date 2017-05-09
     * @version $Id$
     * @since 0.0.1
     */
	class Simple implements Entity {

	    /**
	     * The name of this entity.
	     */
	    private final String name;

	    /**
	     * The values allowed to this entity, along with possible synonyms.
	     */
	    private final Map<String, List<String>> values;

	    /**
	     * Instantiates a named entity with its possible values.
	     * 
	     * @param name
	     *            The name of the entity
	     * @param values
	     *            The values allowed to the entity
	     */
	    public Simple(final String name, final Map<String, List<String>> values) {
	        this.name = name;
	        this.values = values;
	    }

	    /**
	     * Instantiates an entity with the name only.
	     * 
	     * @param name
	     *            The name of the entity
	     */
	    public Simple(final String name) {
	        this(name, new HashMap<String, List<String>>());
	    }

	    /**
	     * Adds a value allowed to {@code this} entity.
	     * 
	     * @param value
	     *            A possible value of this entity
	     * @param synonyms
	     *            Synonyms of the given value
	     */
        public void addValue(String value, String... synonyms) {
	        this.values.put(value, Arrays.asList(synonyms));
	    }

        /* (non-Javadoc)
         * @see com.rigiresearch.botmaker.model.Entity#name()
         */
        @Override
        public String name() {
            return this.name;
        }

        /*
         * (non-Javadoc)
         * @see com.rigiresearch.botmaker.model.Entity#addValue(java.lang.String, java.util.List)
         */
	    @Override
        public void addValue(String value, List<String> synonyms) {
	        addValue(value, synonyms.toArray(new String[0]));
	    }

	    /* (non-Javadoc)
         * @see com.rigiresearch.botmaker.model.Entity#values()
         */
        @Override
        public Map<String, List<String>> values() {
            return this.values;
        }

        /*
         * (non-Javadoc)
         * @see com.rigiresearch.botmaker.model.Term#value()
         */
        @Override
        public Object value() {
            return this.name;
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

}
