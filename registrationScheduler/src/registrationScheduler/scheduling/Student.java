package registrationScheduler.scheduling;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import registrationScheduler.objectPool.ObjectPool;

/**
 * Student class is used to store regTime, name, prefscore and also implements
 * StudentInterface
 * 
 * @author shubham
 * 
 */
public class Student implements StudentInterface {

	public int regTime;
	public String name;
	public int prefscore;
	public LinkedHashMap<String, Integer> prefScores;
	public List<Courses> preference;
	public List<Courses> allocated;
	public String pref[];

	public Student(String regDetails) {
		String regDetail[] = regDetails.trim().split(" ");
		name = regDetail[0];

		preference = new ArrayList<Courses>();
		allocated = new ArrayList<Courses>();
		prefScores = new LinkedHashMap<String, Integer>();

		for (int i = 1; i < regDetail.length; i++) {

			Courses course = ObjectPool.getInstance().getAllCourses()
					.get(regDetail[i]);

			/**
			 * Increase the course rating or preferences for students
			 */

			course.incrementPref(i - 1);

			preference.add(course);
		}
	}

	public int getRegTime() {
		return regTime;
	}

	public void setRegTime(int regTime) {
		this.regTime = regTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrefscore() {
		return prefscore;
	}

	public void setPrefscore(int prefscore) {
		this.prefscore = prefscore;
	}

	public List<Courses> getPreference() {
		return preference;
	}

	public void setPreference(List<Courses> preference) {
		this.preference = preference;
	}

	public List<Courses> getAllocated() {
		return allocated;
	}

	public void setAllocated(List<Courses> allocated) {
		this.allocated = allocated;
	}

	public String[] getPref() {
		return pref;
	}

	public void setPref(String[] pref) {
		this.pref = pref;
	}

	public LinkedHashMap<String, Student> readStudents() {
		return ObjectPool.getInstance().getAllStudent();

	}

	public Courses getCourseAtPref(int choice) {
		
		return getPreference().get(choice);
	}

	public void updatePreferenceScore(int score) {
		this.prefscore = score;
	}

	@Override
	public String toString() {
		return "Student []";
	}

}
