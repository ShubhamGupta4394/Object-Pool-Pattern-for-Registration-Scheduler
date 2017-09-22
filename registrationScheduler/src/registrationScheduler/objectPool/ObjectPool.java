package registrationScheduler.objectPool;

import java.util.LinkedHashMap;

import registrationScheduler.scheduling.Courses;
import registrationScheduler.scheduling.Student;

/**
 * Records is a singleton class used to store student and courses
 * 
 * @author shubham
 * 
 */
public class ObjectPool {

	private static ObjectPool _sharedInstance;

	private LinkedHashMap<String,Student> studentList;
	private LinkedHashMap<String, Courses> coursesList;
	public ObjectPool() {

	}

	public synchronized static ObjectPool getInstance() {
		if (_sharedInstance == null)
			_sharedInstance = new ObjectPool();

		return _sharedInstance;
	}

	public void addStudent(Student student) {
		if (studentList == null) {
			studentList = new LinkedHashMap<String,Student>();
		}
		studentList.put(student.name,student);
	}

	public void addCourses(Courses courses) {
		if (coursesList == null) {
			coursesList = new LinkedHashMap<String, Courses>();
		}
		coursesList.put(courses.coursename, courses);
	}

	public LinkedHashMap<String, Student> getAllStudent() {
		return studentList;
	}

	@Override
	public String toString() {
		return "Records []";
	}

	public LinkedHashMap<String, Courses> getAllCourses() {
		return coursesList;
	}

}
