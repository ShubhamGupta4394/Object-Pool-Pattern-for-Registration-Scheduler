package registrationScheduler.thrdmgmt;

import registrationScheduler.objectPool.ObjectPool;
import registrationScheduler.scheduling.Registrar;
import registrationScheduler.scheduling.Student;
import registrationScheduler.util.FileProcessor;

public class PreferenceAddDrop {
	Registrar registrar = new Registrar();

	/**
	 * Parse prefernce file
	 * 
	 * @param fp2
	 *            FileProcessor of referencefile passed
	 */
	public void parseStudents(FileProcessor fp2) {

		String read;
		while ((read = fp2.readLineFromFile()) != null) {
			Student student = new Student(read);
			ObjectPool.getInstance().addStudent(student);
			registrar.doAllotment(student);

		}
	}

	/**
	 * Parse addDrop File
	 * 
	 * @param fp
	 *            FileProcessor of addDropfile passed
	 */
	public void parseAddDorp(FileProcessor fp) {

		String read;
		while ((read = fp.readLineFromFile()) != null) {
			registrar.addDrop(read);

		}
	}

}
