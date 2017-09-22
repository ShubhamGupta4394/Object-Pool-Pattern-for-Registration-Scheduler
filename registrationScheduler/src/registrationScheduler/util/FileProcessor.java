package registrationScheduler.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Input Parser class is used to parse student and initialize Course object with
 * 8 course
 * 
 * @author shubham
 * 
 */
public class FileProcessor {
	BufferedReader in;
	BufferedWriter bw;
	String outfile;
	String read;

	/**
	 * parseStudents method is used to parse String into file object and
	 * retrieve the file contents
	 * 
	 * @param preferences
	 *            -> first file path passed as String
	 * @param regTime
	 *            -> second file path passed as String
	 */
	public FileProcessor(BufferedReader bufReaderIn) {
		Logger.writeMessage("In FileProcessor, BufferedReader constructor",
				Logger.DebugLevel.CONSTRUCTOR);
		in = bufReaderIn;
	}

	public FileProcessor(String outFileNameIn) {
		Logger.writeMessage("In FileProcessor, String Parameter constructor",
				Logger.DebugLevel.CONSTRUCTOR);
		
		outfile = outFileNameIn;
		File outputFile = new File(outfile);
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(outputFile);
			bw = new BufferedWriter(new OutputStreamWriter(fout));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public synchronized String readLineFromFile() {
		try {
			read = in.readLine();
		} catch (IOException e) {
			System.err.println("Error in Reading File");
			e.printStackTrace();
		}
		return read;
	}

	public synchronized void writeLineToFile(String data) {
		System.out.println(data);
		try {
			bw.write(data);
			bw.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return super.toString();
	}
}
