package registrationScheduler.thrdmgmt;

import registrationScheduler.store.Results;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.util.Logger.DebugLevel;

public class WorkerThread implements Runnable {

	public JOB jobType;
	FileProcessor fileProcessorRegPref, fileProcessorAddD;
	Results rslt;
	Callback callback;
	String output;

	public enum JOB {
		ALL, STUDENTS, ADDDROP, ADDDROP_PRINT, PRINT;
	}

	/**
	 * WorkerThread constructor
	 * 
	 * @param fileProcessorPref
	 *            - FileProcessor Reference for RegPreference File
	 * @param fileProcessorAddD
	 *            - FileProcessor Referernce for AddDrop File
	 * @param rslt
	 *            - Result instance
	 * @param out
	 *            - Output String
	 * @param callback
	 *            - Callback Interface instance
	 */
	public WorkerThread(FileProcessor fileProcessorPref,
			FileProcessor fileProcessorAddD, Results rslt, String out,
			Callback callback) {
		fileProcessorRegPref = fileProcessorPref;
		this.fileProcessorAddD = fileProcessorAddD;
		this.rslt = rslt;
		this.callback = callback;
		output = out;
		Logger.writeMessage("Worker Thread Constructor Called",
				Logger.DebugLevel.CONSTRUCTOR);
	}

	/**
	 * run method is used to run threads
	 */
	public void run() {
		PreferenceAddDrop pf = new PreferenceAddDrop();
		Logger.writeMessage("In run Method of Thread", Logger.DebugLevel.IN_RUN);
		switch (jobType) {
		case ALL:
			pf.parseStudents(fileProcessorRegPref);
			pf.parseAddDorp(fileProcessorAddD);
			rslt.addResult();
			rslt.writeScheduleToStdout();
			rslt.writeScheduletoFile(output);
			break;
		case STUDENTS:
			pf.parseStudents(fileProcessorRegPref);
			break;
		case ADDDROP:
			pf.parseAddDorp(fileProcessorAddD);
			break;
		case ADDDROP_PRINT:
			pf.parseAddDorp(fileProcessorAddD);
			rslt.addResult();
			rslt.writeScheduleToStdout();
			rslt.writeScheduletoFile(output);
			break;
		case PRINT:
			rslt.addResult();
			rslt.writeScheduleToStdout();
			rslt.writeScheduletoFile(output);
			break;
		default:
			break;
		}

		if (callback != null)
			callback.onComplete();

	}

}
