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

import java.util.List;

/**
 * Represents a user's intent in a conversation.
 * @author Miguel Jimenez (miguel@uvic.ca)
 * @date 2017-05-09
 * @version $Id$
 * @since 0.0.1
 */
public class Intent implements Term {

    /**
     * This intent's name.
     */
    private final String name;

    /**
     * This intent's training values.
     */
    private final List<String> values;

    /**
     * Default constructor.
     * @param name this intent's name
     * @param values this intent's training values
     */
    public Intent(final String name, final List<String> values) {
        this.name = name;
        this.values = values;
    }

    /**
     * This intent's name.
     * @return unique name associated to this intent
     */
    public String name() {
        return this.name;
    }

    /**
     * This intent's training values.
     * @return a list of possible intent values
     */
    public List<String> values() {
        return this.values;
    }

    /* (non-Javadoc)
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
        return String.format(
            "#%s",
            this.name
        );
    }
}
