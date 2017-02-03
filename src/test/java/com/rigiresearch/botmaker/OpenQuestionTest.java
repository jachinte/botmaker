/**
 * 
 */
package com.rigiresearch.botmaker;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rigiresearch.botmaker.model.OpenQuestion;

/**
 * @author Miguel Jiménez
 * @date 2017-02-02
 */
public class OpenQuestionTest {

	/**
	 * Test method for {@link com.rigiresearch.botmaker.model.OpenQuestion#toJson()}.
	 */
	@Test
	public void testToJson() {
		OpenQuestion question = new OpenQuestion("How are you?");
		assertEquals(question.toJson(), "{\"statement\":\"How are you?\"}");
	}

}
