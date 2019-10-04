package edu.waketech.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.waketech.academic.Assignment;
import edu.waketech.academic.Course;
import edu.waketech.common.Student;
import edu.waketech.common.StudentBody;

class CourseTest {

	@Test
	void testGetAssignmentsForStudent() {
		Course c = new Course("CSC", 251);

		Student s1 = StudentGenerator.genStudent();
		Student s2 = StudentGenerator.genStudent();
		Student s3= StudentGenerator.genStudent();
		StudentBody.getInstance().add(s1);
		StudentBody.getInstance().add(s2);
		StudentBody.getInstance().add(s3);

		Assignment lab1 = new Assignment("lab1", 100, 90);
		Assignment lab2 = new Assignment("lab2", 100, 91);
		Assignment lab3 = new Assignment("lab3", 100, 92);
		c.addAssignment(s1.getId(), lab1);
		c.addAssignment(s1.getId(), lab2);
		c.addAssignment(s1.getId(), lab3);
		c.addAssignment(s2.getId(), lab1);
		c.addAssignment(s2.getId(), lab2);
		c.addAssignment(s3.getId(), lab1);
		
		List<Assignment> l = c.getAssignmentsForStudent(s1.getId());
		
		assertTrue(l.contains(lab1), "does not contain " + lab1);
		assertTrue(l.contains(lab2), "does not contain " + lab2);
		assertTrue(l.contains(lab3), "does not contain " + lab3);

	}

	@Test
	void testGetAssignment() {
		Course c = new Course("CSC", 251);

		Student s1 = StudentGenerator.genStudent();
		Student s2 = StudentGenerator.genStudent();
		Student s3= StudentGenerator.genStudent();
		StudentBody.getInstance().add(s1);
		StudentBody.getInstance().add(s2);
		StudentBody.getInstance().add(s3);

		Assignment lab1 = new Assignment("lab1", 100, 90);
		Assignment lab2 = new Assignment("lab2", 100, 91);
		Assignment lab3 = new Assignment("lab3", 100, 92);
		c.addAssignment(s1.getId(), lab1);
		c.addAssignment(s1.getId(), lab2);
		c.addAssignment(s1.getId(), lab3);
		c.addAssignment(s2.getId(), lab1);
		c.addAssignment(s2.getId(), lab2);
		c.addAssignment(s3.getId(), lab1);
		
		List<Assignment> l = c.getAssignment(s2.getId(), "lab2");
		assertEquals(1, l.size(), "size is wrong: " + l);
		assertEquals(lab2, l.get(0));
	}
	

	@Test
	void testGetStudentsForAssignment() {
		Course c = new Course("CSC", 251);

		Student s1 = StudentGenerator.genStudent();
		Student s2 = StudentGenerator.genStudent();
		Student s3= StudentGenerator.genStudent();
		StudentBody.getInstance().add(s1);
		StudentBody.getInstance().add(s2);
		StudentBody.getInstance().add(s3);

		Assignment lab1 = new Assignment("lab1", 100, 90);
		Assignment lab2 = new Assignment("lab2", 100, 91);
		Assignment lab3 = new Assignment("lab3", 100, 92);

		c.addAssignment(s1.getId(), lab3);
		c.addAssignment(s2.getId(), lab3);
		c.addAssignment(s3.getId(), lab1);
		c.addAssignment(s3.getId(), lab2);
		
		Map<Integer, List<Assignment>> m = c.getStudentsForAssignment("lab3");
		
		
		assertEquals(2, m.size(), "size wrong " + m);
		Integer[] expectedKeys = {s1.getId(), s2.getId()};
		for (int k: expectedKeys) {
			for (Assignment a: m.get(k)) {
				assertTrue(a.isNamed("lab3"), " not named " + a);
			}
		}
	}

	@Test
	void testFilterRoster() {
		Course c = new Course("CSC", 251);

		Student s1 = StudentGenerator.genStudent();
		Student s2 = StudentGenerator.genStudent();
		Student s3= StudentGenerator.genStudent();
		StudentBody.getInstance().add(s1);
		StudentBody.getInstance().add(s2);
		StudentBody.getInstance().add(s3);

		Assignment lab1 = new Assignment("lab1", 100, 90);
		Assignment lab2 = new Assignment("lab2", 100, 91);
		Assignment lab3 = new Assignment("lab3", 100, 92);
		c.addAssignment(s1.getId(), lab1);
		c.addAssignment(s1.getId(), lab2);
		c.addAssignment(s1.getId(), lab3);
		c.addAssignment(s2.getId(), lab1);
		c.addAssignment(s2.getId(), lab2);
		c.addAssignment(s3.getId(), lab1);
		
		Map<Integer, List<Assignment>> m = c.filterRoster(e -> (e == s1.getId() || e == s3.getId()), 
				a -> a.getScore() == 90);
		
		assertEquals(2, m.size(), "size wrong " + m);
		Integer[] expectedKeys = {s1.getId(), s3.getId()};
		for (int k: expectedKeys) {
			for (Assignment a: m.get(k)) {
				assertTrue(a.isNamed("lab1"), "not named lab1 " + a);
			}
		}
	}
	
	@Test
	void testAllAssignmentNames() {
		Course c = new Course("CSC", 251);

		Student s1 = StudentGenerator.genStudent();
		Student s2 = StudentGenerator.genStudent();
		Student s3= StudentGenerator.genStudent();
		StudentBody.getInstance().add(s1);
		StudentBody.getInstance().add(s2);
		StudentBody.getInstance().add(s3);

		Assignment lab1 = new Assignment("lab1", 100, 90);
		Assignment lab3 = new Assignment("lab3", 100, 92);
		Assignment lab2 = new Assignment("lab2", 100, 91);
		c.addAssignment(s1.getId(), lab1);
		c.addAssignment(s1.getId(), lab2);
		c.addAssignment(s1.getId(), lab3);
		c.addAssignment(s2.getId(), lab1);
		c.addAssignment(s2.getId(), lab2);
		c.addAssignment(s3.getId(), lab1);
		
		List<String> allLabs = c.getAllAssignmentNames();
		List<String> expected = new ArrayList<>();
		expected.add("lab1");
		expected.add("lab2");
		expected.add("lab3");
		assertEquals(expected, allLabs);
		
	}

}
