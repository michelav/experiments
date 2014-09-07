package br.unifor.ppgia.filehandling;

import java.io.IOException;

import br.unifor.ppgia.filehandling.processor.FileHandlingProcessor;

public class FileHandlingApp {
	

	public static void main(String[] args) throws IOException, InterruptedException {
		
		int numOfThreads = Integer.parseInt(args[0]);
		int numOfQuotes = Integer.parseInt(args[1]);
		
		FileHandlingProcessor processor = new FileHandlingProcessor(numOfThreads, numOfQuotes);
		processor.process();
	}
}
