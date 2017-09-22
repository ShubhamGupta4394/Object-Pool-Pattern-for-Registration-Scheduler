package registrationScheduler.store;

import java.util.Vector;

import registrationScheduler.objectPool.ObjectPool;
import registrationScheduler.scheduling.Student;
import registrationScheduler.util.FileDisplayInterface;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.util.StdoutDisplayInterface;

/**
 * Result class used to write Output to File by implementing
 * FileDisplayInterface
 * 
 * @author shubham
 * 
 */

public class Results implements StdoutDisplayInterface, FileDisplayInterface {
	
	private Vector<String> result = new Vector<String>();

	public void addResult() {
		for (Student stud : ObjectPool.getInstance().getAllStudent().values()) {
			String output = stud.name + "   " + stud.allocated + "   "
					+ stud.prefscore;
			result.add(output);
			Logger.writeMessage("Entry is added to DataStructure" + output,
					Logger.DebugLevel.IN_RESULTS);
		}
		checkEfficiency();
	}

	public void checkEfficiency() {
		int sum = 0;
		float average = 0;

		for (Student student : ObjectPool.getInstance().getAllStudent()
				.values()) {
			sum += student.prefscore;
		}

		average = sum / ObjectPool.getInstance().getAllStudent().size();
		String average_score = "Average Preference score is: " + average;
		result.add(average_score);
		Logger.writeMessage(average_score, Logger.DebugLevel.RELEASE);

	}

	public void writeScheduleToStdout() {
		for (int i = 0; i < result.size(); i++) {
			Logger.writeMessage(result.get(i), Logger.DebugLevel.FROM_RESULTS);

		}
	}

	@Override
	public String toString() {
		return "Results []";
	}
	/**
	 * Output is written to file
	 */
	public void writeScheduletoFile(String outfile) {
		FileProcessor fileProcessorwrite = new FileProcessor(outfile);
		for (int i = 0; i < result.size(); i++) {
			fileProcessorwrite.writeLineToFile(result.get(i));
		}
		fileProcessorwrite.close();
	}

}
