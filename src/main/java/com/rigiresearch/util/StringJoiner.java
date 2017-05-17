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
package com.rigiresearch.util;

import java.util.List;
import java.util.function.Function;

/**
 * Customized string joiner that allows a different last separator.
 * @author Miguel Jimenez (miguel@uvic.ca)
 * @date 2017-05-09
 * @version $Id$
 * @since 0.0.1
 */
public final class StringJoiner {

    /**
     * The joiner function.
     */
    private final Function<List<String>, String> joiner;

    /**
     * Default constructor.
     * 
     * @param delimiter delimiter used for separating all strings except the
     *  last one
     * @param lastDelimiter delimiter used for separating the last string
     */
    public StringJoiner(final String delimiter, final String lastDelimiter) {
        this.joiner = list -> {
            int last = list.size() - 1;
            if (last < 1)
                return String.join("", list);
            if (last == 1)
                return String.join(
                    String.format(
                        " %s ",
                        lastDelimiter
                    ),
                    list
                );
            return String.join(
                String.format(
                    ", %s ",
                    lastDelimiter
                ),
                String.join(delimiter, list.subList(0, last)),
                list.get(last)
            );
        };
    }

    /**
     * Returns the joiner function.
     * @return the joiner function
     */
    public Function<List<String>, String> function() {
        return this.joiner;
    }

    public String join(List<String> strings) {
        return this.joiner.apply(strings);
    }

}
