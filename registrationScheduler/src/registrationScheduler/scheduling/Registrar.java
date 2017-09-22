package registrationScheduler.scheduling;

import java.util.ArrayList;
import java.util.List;

import registrationScheduler.objectPool.ObjectPool;

/**
 * Registrar class implements RegistrarInterface which is allocating course to
 * students
 * 
 * @author shubham
 * 
 */
public class Registrar {

	public Registrar() {

	}

	public void doAllotment(Student student) {
		int choice = 0;

		/**
		 * Allocate 4 courses to at most students
		 */
		while (choice < 5) {

			/**
			 * Iterate through all the students and assign the best available
			 * course
			 */

			/**
			 * Check students first preference and assign if sear is available
			 */
			Courses course = student.getCourseAtPref(choice);

			/**
			 * If there is seat available in course. Register student for the
			 * class
			 */
			if (course.isSeatAvailable()) {
				course.registerStudent(student);
				/**
				 * Update score
				 */

				int score = (7 - (choice + 1));

				student.updatePreferenceScore(student.getPrefscore() + score);
				student.prefScores.put(course.coursename, score);
				student.allocated.add(course);
			}

			else {
				course = Courses.getNextAvailableCourse(student.allocated);

				if (course == null) {
					// student.prefscore = student.prefscore += 6;
					choice++;
					continue;
				}

				student.prefscore = student.prefscore += 1;

				student.allocated.add(course);
				student.prefScores.put(course.coursename, 1);
				course.registerStudent(student);
			}

			choice += 1;
		}
	}

	/**
	 * AddDrop Subject
	 * 
	 * @param updateString
	 *            - input from the readFile
	 */
	public void addDrop(String updateString) {

		String[] values = updateString.split(" ");

		String name = values[0];
		int modFlag = Integer.parseInt(values[1]);

		List<Courses> courses = new ArrayList<Courses>();

		for (int i = 2; i < values.length; i++) {
			Courses course = ObjectPool.getInstance().getAllCourses()
					.get(values[i]);
			courses.add(course);
		}

		Student student = ObjectPool.getInstance().getAllStudent().get(name);

		for (Courses c : courses) {

			if (modFlag == 0) {
				int value = student.prefScores.containsKey(c.coursename) ? student.prefScores
						.get(c.coursename) : 0;
				student.getAllocated().remove(c);
				student.prefscore -= value;
				student.prefScores.remove(c.coursename);
			} else {
				if (c.isSeatAvailable()) {
					c.registerStudent(student);
					student.allocated.add(c);
					student.prefscore = student.prefscore + 1;
					student.prefScores.put(c.coursename, 1);
				}

			}
		}
	}

	@Override
	public String toString() {
		return "Registrar []";
	}

}
