package br.unifor.ppgia.filehandling.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class FileHandlingProcessor {

	String[] quotes = null;
	
	String _QUOTES_ = "/home/michel/devel/experiments/filehandling/threadsnfiles/src/main/resources/quotes.txt";

	int numOfThreads;

	int numOfQuotes;

	public FileHandlingProcessor() {
		// Default values
		numOfThreads = 5;
		numOfQuotes = 5;
	}

	public FileHandlingProcessor(int threads, int quotes) {
		numOfThreads = threads;
		numOfQuotes = quotes;
	}

	public void process() throws IOException, InterruptedException {
		readQuotes();
		processQuotes();
	}

	private void readQuotes() throws IOException {
		
		FileInputStream stream = new FileInputStream(new File(_QUOTES_));
		StringBuffer strBuffer = new StringBuffer();
		int content = 0;
		while((content = stream.read()) != -1) {
			strBuffer.append((char)content);
		}
		quotes = strBuffer.toString().split("---");
		System.out.println("Number of quotes: " + quotes.length);
		stream.close();
	}

	private void processQuotes() throws InterruptedException {
		Random gen = new Random();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Calendar cal = null;
		String fileName = null;
		
		List<String> quotesList = null;
		Set<Thread> tasks = new HashSet<Thread>(); 
		for (int i = 0; i < numOfThreads; i++) {
			quotesList = new ArrayList<String>();
			for(int j = 0; j < numOfQuotes; j++) {
				quotesList.add(quotes[gen.nextInt(quotes.length)]);
			}
			cal = Calendar.getInstance();
			fileName = "quotes_" + format.format(cal.getTime()) + "_00" + Integer.toString(i);  
			tasks.add(new Thread(new FileTask(quotesList, "output", fileName)));
		}
		
		for (Thread thread : tasks) {
			thread.start();
		}
		
		for (Thread thread : tasks) {
			thread.join();
		}
		
		System.out.println("=================== All quotes processed ===================");
	}
}
