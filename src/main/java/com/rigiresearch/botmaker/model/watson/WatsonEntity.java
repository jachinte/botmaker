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

import com.rigiresearch.botmaker.model.Entity;
import java.util.stream.Collectors;

/**
 * Entity decorator to represent a Watson conversation's entity.
 * @author Miguel Jimenez (miguel@uvic.ca)
 * @date 2017-05-09
 * @version $Id$
 * @since 0.0.1
 */
public final class WatsonEntity implements JsonObject {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -3593224078356942690L;

    /**
     * The decorated entity.
     */
    private final Entity origin;

    /**
     * Default constructor.
     * @param entity the decorated entity
     */
    public WatsonEntity(final Entity entity) {
        this.origin = entity;
    }

    /*
     * (non-Javadoc)
     * @see com.rigiresearch.botmaker.model.JsonObject#json()
     */
    @Override
    public String json() {
        return String.format("{\"entity\":\"%s\",\"values\":[%s]}", 
            this.origin.name().replace("\"", "\\\""),
            this.origin.values().keySet().stream().map(key -> {
                String list = this.origin.values()
                    .get(key)
                    .stream()
                    .map(synonym -> 
                        String.format("\"%s\"", synonym.replace("\"", "\\\""))
                    )
                    .collect(Collectors.joining(","));
                return String.format("{\"value\":\"%s\",\"synonyms\":[%s]}", 
                    key.replace("\"", "\\\""), list);
            })
            .collect(Collectors.joining(",")));
    }

}
