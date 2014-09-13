package br.unifor.ppgia.detours.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public aspect FileHandlingAspect {
	pointcut fileConstructedOutputStream(File file) : call(FileOutputStream.new(File)) && 
	within(br.unifor.ppgia.filehandling.processor.*) &&
	args(file);
	
	before(File file) throws FileNotFoundException : fileConstructedOutputStream(file) {
		System.out.println("Capturing instantiation of FileOutputStream");
	}
}
