package registrationScheduler.driver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import registrationScheduler.objectPool.ObjectPool;
import registrationScheduler.scheduling.Courses;
import registrationScheduler.store.Results;
import registrationScheduler.thrdmgmt.CreateWorkers;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;

/**
 * Driver Class containing main method
 * 
 * @author shubham
 * 
 */
public class Driver {

	/**
	 * main method to start the program
	 * 
	 * @param args
	 *            = 4 arguments are passed from command line
	 */
	
	private static String regPreference, addDrop, output;
	private static int debug_value, num_Threads;
	public static BufferedReader bufferedReaderRegPreference,
			bufferedReaderAddDrop;
	public static BufferedWriter bufferedWriterOutput;
    
	
	public static void main(String args[]) {
		Driver dr = new Driver();
		dr.validateArgs(args);
		dr.dumpCourses();
		Logger.setDebugValue(debug_value);
		FileProcessor fileProcessorRegPreference = new FileProcessor(
				bufferedReaderRegPreference);
		FileProcessor fileProcessorAddDrop = new FileProcessor(
				bufferedReaderAddDrop);
		Results rslt = new Results();
		CreateWorkers createWorkers = new CreateWorkers(
				fileProcessorRegPreference, fileProcessorAddDrop, rslt,output);
		
		try {
			createWorkers.startWorkers(num_Threads);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		createWorkers.startThread();
		
	}

	private void validateArgs(String args[]) {
		if (args.length == 5) {
			regPreference = args[0];
			addDrop = args[1];
			output = args[2];
			try {
				num_Threads = Integer.parseInt(args[3]);
				debug_value = Integer.parseInt(args[4]);
				if (num_Threads < 1 || num_Threads > 3) {
					System.err.println("Debug value should be between 1 to 3");
					System.exit(-1);
				}
				if (debug_value < 0 || debug_value > 4) {
					System.err.println("Debug value should be between 0 to 4");
					System.exit(-1);
				}
				File regPreferenceFile = new File(regPreference);
				File addDropFile = new File(addDrop);
				if (regPreferenceFile.length() == 0	|| addDropFile.length() == 0) {
					System.err.println("One of the file is empty");
					System.exit(-1);
				}
				FileInputStream fileInputStreamRegPreferenece = new FileInputStream(
						regPreferenceFile);
				FileInputStream fileInputStreamAddDrop = new FileInputStream(
						addDropFile);
				bufferedReaderRegPreference = new BufferedReader(
						new InputStreamReader(fileInputStreamRegPreferenece));
				bufferedReaderAddDrop = new BufferedReader(
						new InputStreamReader(fileInputStreamAddDrop));

			} catch (IllegalArgumentException ex) {
				System.err
						.println("NumberFormatException-Cannot parse to integer.");
				ex.printStackTrace();
				System.exit(-1);
			} catch (FileNotFoundException e) {
				System.err
						.println("FIleNotFoundException- File not found in arguments passed at command line");
				e.printStackTrace();
				System.exit(-1);
			}
		} else {
			System.err
					.println("Invalid number of arguments. Expected 5 arguments");
			System.exit(-1);
		}
		
	}
	
	/**
	 * dumpCourses method is used to initialize
	 * Courses object with 8 courses
	 * 
	 */
	public void dumpCourses() {
		String[] courses = { "A", "B", "C", "D", "E", "F", "G", "H" };
		for (int i = 0; i < courses.length; i++) {
			Courses course = new Courses(courses[i]);
			ObjectPool.getInstance().addCourses(course);
		}

	}
}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	
	public static void main(String args[]) {
		if (args.length < 5 || args.length > 5) {
			System.err.println("4 Arguments required");
			System.exit(-1);
		}
		String outputFile = args[2];
		int debug_value = 0;
		if (isInteger(args[4])) {
			debug_value = Integer.parseInt(args[4]);
			if (debug_value < 0 || debug_value > 4) {
				System.err.println("Debug value should be between 0 to 4");
			}
		}
		/**
		 * Initialize all the 8 courses
		 
		FileProcessor.dumpCourses();
		
		
		CreateWorkers  createWorkers = new CreateWorkers(args[0], args[1]);
		
		try {
			createWorkers.createThreads(Integer.parseInt(args[3]));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Execute
		
		/**
		 * Logger value is set
		 
		//Logger.setDebugValue(debug_value);
		//Logger.writeMessage("Debug value initialized", DebugLevel.CONSTRUCTOR);

		//InputParserInterface input = new InputParser();
		
		//input.parseStudents(args[0]);
		
		
		//input.parseAddDorp(args[1]);

		//Registrar reg = new Registrar();
		
		if (0 == debug_value) {
			FileDisplayInterface result_file = new Results();
			result_file.output();
			result_file.writeScheduletoFile(outputFile);
		}

	}// end main(...)

	/**
	 * 
	 * isInteger is used to check argument passed is integer or not
	 * 
	 * @param string
	 *            = String argument passed to check if it is integer or not
	 * @return true if it is integer otherwise false
	 
	private static boolean isInteger(String string) {

		try {
			Integer.parseInt(string);
		} catch (NumberFormatException e) {
			System.err.println("Debug value should be integer");
			e.printStackTrace();
			System.exit(-1);
			return false;
		} finally {
			
		}
		return true;
	}
} // end public class Driver
*/
