package registrationScheduler.thrdmgmt;

import java.util.ArrayList;
import java.util.List;

import registrationScheduler.store.Results;
import registrationScheduler.thrdmgmt.WorkerThread.JOB;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;

public class CreateWorkers implements Callback {

	int currentThreadIndex = 0;
	List<Thread> threads;
	FileProcessor fileProcessorRegPref,fileProcessorAddD;
	Results rslt;
	String output;
	
	public CreateWorkers(FileProcessor fileProcessorRegPref,FileProcessor fileProcessorAddD,Results rslt,String out) {
		threads = new ArrayList<Thread>();
		this.fileProcessorRegPref = fileProcessorRegPref;
		this.fileProcessorAddD = fileProcessorAddD;
		output = out;
		this.rslt=rslt;
		Logger.writeMessage("CreateWorkers Constructor Called",
				Logger.DebugLevel.CONSTRUCTOR);
	}

	public void startWorkers(int noOfThreads) throws InterruptedException {
		if(noOfThreads == 1) {
			WorkerThread workerThread = new WorkerThread(this.fileProcessorRegPref,this.fileProcessorAddD,this.rslt,this.output, this);
			workerThread.jobType = JOB.ALL;
			Thread thread = new Thread(workerThread);
			thread.setName("all");
			threads.add(thread);
		} else if(noOfThreads == 2 ) {
			WorkerThread workerThread = new WorkerThread(this.fileProcessorRegPref,this.fileProcessorAddD,this.rslt,this.output, this);
			workerThread.jobType = JOB.STUDENTS;
			Thread thread = new Thread(workerThread);
			thread.setName("students");
			threads.add(thread);

			workerThread = new WorkerThread(this.fileProcessorRegPref,this.fileProcessorAddD,this.rslt,this.output, this);
			workerThread.jobType = JOB.ADDDROP_PRINT;
			thread = new Thread(workerThread);
			thread.setName("adddrop-print");
			threads.add(thread);
		} else if(noOfThreads == 3) {
			WorkerThread workerThread = new WorkerThread(this.fileProcessorRegPref,this.fileProcessorAddD,this.rslt,this.output, this);
			workerThread.jobType = JOB.STUDENTS;
			Thread thread = new Thread(workerThread);
			thread.setName("students");
			threads.add(thread);

			workerThread = new WorkerThread(this.fileProcessorRegPref,this.fileProcessorAddD,this.rslt,this.output, this);
			workerThread.jobType = JOB.ADDDROP;
			thread = new Thread(workerThread);
			thread.setName("adddrop");
			threads.add(thread);

			workerThread = new WorkerThread(this.fileProcessorRegPref,this.fileProcessorAddD,this.rslt,this.output, this);
			workerThread.jobType = JOB.PRINT;
			thread = new Thread(workerThread);
			thread.setName("print");
			threads.add(thread);
		}
		
		for(Thread thread : threads) {
			thread.join();
		}
	}
	
	public void startThread() {
		if(currentThreadIndex >= threads.size()) return;
		threads.get(currentThreadIndex).start();
	}

	public void onComplete() {
		currentThreadIndex++;
		startThread();
	}
}
