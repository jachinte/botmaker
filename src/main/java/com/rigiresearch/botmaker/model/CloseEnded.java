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

import com.rigiresearch.util.StringJoiner;
import java.util.List;

/**
 * Represents a multiple choice question. Number of allowed answers is
 * assumed to be implicit.
 * <p>
 * Example of usage: <br>
 * <b>statement</b>: would you prefer a <br>
 * <b>options</b>: blue car, green car, red car <br>
 * <b>formatted question</b>: Would you prefer a blue car, green car, or red
 * car?
 * @author Miguel Jimenez (miguel@uvic.ca)
 * @date 2017-05-09
 * @version $Id$
 * @since 0.0.1
 */
public class CloseEnded implements Question {

    /**
     * This question's statement.
     */
    private final String statement;

    /**
     * List of possible answers.
     */
    private final List<String> possibleAnswers;

    /**
     * Default constructor.
     * @param statement this question's statement
     * @param possibleAnswers list of possible answers
     */
    public CloseEnded(final String statement,
        final List<String> possibleAnswers) {
        this.statement = statement;
        this.possibleAnswers = possibleAnswers;
    }

    /* (non-Javadoc)
     * @see com.rigiresearch.botmaker.model.Question#format()
     */
    @Override
    public String format() {
        return String.format(
            "%s %s?",
            this.statement,
            new StringJoiner(", ", "or")
                .join(this.possibleAnswers)
        );
    }

}
