package edu.waketech.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.waketech.academic.Assignment;
import edu.waketech.academic.Course;
import edu.waketech.common.Student;
import edu.waketech.common.StudentBody;
import edu.waketech.GradebookApp;

class GradebookAppTest {


	@Test
	void testCreateAssignment() {
		Course course = new Course("CSC", 251);
		Student s1 = new Student("last1", "first1", 2);
		Student s2 = new Student("last2", "first2", 3);
		StudentBody sb = StudentBody.getInstance();
		sb.add(s1);
		sb.add(s2);
		
		course.addStudent(s1.getId());
		course.addStudent(s2.getId());
		
		GradebookApp.createAssignment(course, "lab1", 50);
		List<Assignment> labList = course.getAssignment(s1.getId(), "lab1");
		Assignment foundLab = labList.get(0);
		assertEquals("lab1", foundLab.getName());
		assertEquals(50, foundLab.getPossiblePoints());
		assertTrue(foundLab.getScore() >= 35);
	}
	
	@Test
	void testCreateAssignment1() {
		Course course = new Course("CSC", 251);
		Student s1 = new Student("last1", "first1", 2);
		Student s2 = new Student("last2", "first2", 3);
		StudentBody sb = StudentBody.getInstance();
		sb.add(s1);
		sb.add(s2);
		
		course.addStudent(s1.getId());
		course.addStudent(s2.getId());
		
		GradebookApp.createAssignment(course, "lab1", 50);
		List<Assignment> labList = course.getAssignment(s2.getId(), "lab1");
		Assignment foundLab = labList.get(0);
		assertEquals("lab1", foundLab.getName());
		assertEquals(50, foundLab.getPossiblePoints());
		assertTrue(foundLab.getScore() >= 35);
	}
	
	@Test
	void getStudentsTakingEverything() {
		Course course = new Course("CSC", 251);
		Course course2 = new Course("CSC", 151);
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		courses.add(course2);
		Student s1 = new Student("last1", "first1", 2);
		Student s2 = new Student("last2", "first2", 3);
		StudentBody sb = StudentBody.getInstance();
		sb.add(s1);
		sb.add(s2);
		
		course.addAssignment(s1.getId(), new Assignment("lab1", 100, 50));
		course2.addAssignment(s1.getId(), new Assignment("lab2", 100, 60));
		course.addAssignment(s2.getId(), new Assignment("lab3", 100, 70));
		
		
		GradebookApp.createAssignment(course, "lab1", 50);
		GradebookApp.createAssignment(course2, "lab1", 60);
		List<Integer> students = new ArrayList<>();
		students = GradebookApp.getStudentsTakingEverything(courses);
		
		assertEquals(1, students.size());
		assertEquals(2, s1.getId());
	}
	
	@Test
	void courseAverageForAssignment() {
		Course course = new Course("CSC", 251);
		Course course2 = new Course("CSC", 151);
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		courses.add(course2);
		Student s1 = new Student("last1", "first1", 2);
		Student s2 = new Student("last2", "first2", 3);
		StudentBody sb = StudentBody.getInstance();
		sb.add(s1);
		sb.add(s2);
		
		course.addAssignment(s1.getId(), new Assignment("lab1", 100, 50));
		course.addAssignment(s2.getId(), new Assignment("lab1", 100, 90));
		course2.addAssignment(s1.getId(), new Assignment("lab2", 100, 60));
		course2.addAssignment(s2.getId(), new Assignment("lab2", 100, 70));
		
		
		
		double course_1 = GradebookApp.courseAverageForAssignment(course, "lab1");
		double course_2 = GradebookApp.courseAverageForAssignment(course2, "lab2");
		
		
		assertEquals(70, course_1);
		assertEquals(65, course_2);
	}
	
	@Test
	void calculateStudentAverageInOneCourse() {
		Course course = new Course("CSC", 251);
		Course course2 = new Course("CSC", 151);
		List<Course> courses = new ArrayList<>();
		courses.add(course);
		courses.add(course2);
		Student s1 = new Student("last1", "first1", 2);
		Student s2 = new Student("last2", "first2", 3);
		StudentBody sb = StudentBody.getInstance();
		sb.add(s1);
		sb.add(s2);
		
		course.addAssignment(s1.getId(), new Assignment("lab1", 100, 50));
		course.addAssignment(s1.getId(), new Assignment("lab2", 100, 90));
		course.addAssignment(s1.getId(), new Assignment("lab3", 300, 150));
		course.addAssignment(s1.getId(), new Assignment("lab4", 100, 70));
		
		
		
		double studentAverage = GradebookApp.calculateStudentAverageInOneCourse(course, s1.getId());
		
		
		assertEquals(65, studentAverage);
	}


}
