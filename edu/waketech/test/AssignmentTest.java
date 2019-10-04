package edu.waketech.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.waketech.academic.Assignment;

class AssignmentTest {

	@Test
	void testGetScorePercent() {
		Assignment lab = new Assignment("l", 5, 4);
		int actual = lab.getScorePercent();
		assertEquals(80, actual);
	}
}
