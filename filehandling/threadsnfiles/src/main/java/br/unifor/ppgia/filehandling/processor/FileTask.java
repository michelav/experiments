package br.unifor.ppgia.filehandling.processor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class FileTask implements Runnable {

	List<String> textList;
	
	File dir;
	
	String fileName;
	
	

	FileTask(List<String> text, String baseDir, String baseName) {
		textList = text;
		dir = new File(baseDir);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		fileName = baseName;
	}

	@Override
	public void run() {
		File myFile = new File(dir, fileName);
		try {
			writeText(myFile);
			readText(myFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void writeText(File textFile) throws IOException {
		FileOutputStream outputStream = new FileOutputStream(textFile);
		byte[] quotesAsBytes = null;
		int counter = 0;
		for (String text : textList) {
			quotesAsBytes = text.getBytes();
			outputStream.write(quotesAsBytes);
			// outputStream.write("\n".getBytes());
			counter += quotesAsBytes.length;
		}
		outputStream.close();
		System.out.println(counter + " bytes written into " + textFile.getName());
	}

	private void readText(File textFile) throws IOException {
		FileInputStream inputStream = new FileInputStream(textFile);
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int content = 0;
		while((content = inputStream.read()) != -1 ) {
			buffer.write(content);
		}
		
		inputStream.close();
		
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("-----xxxxxxxxxx " + textFile.getName() + " - " + buffer.size() + " xxxxxxxxxx-----");
		System.out.println(buffer.toString());
		System.out.println("-----------------------------------------------------------------------------");
	}
}
