package com.howtodoinjava;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(crackImage("C:\\bankImage\\testocr.png"));

	}
	
	public static String crackImage(String filePath) {  
	    File imageFile = new File(filePath);  
	    ITesseract instance = new Tesseract();  
	    instance.setDatapath("C:\\Users\\Yogini\\testdata");
	    
	    try {  
	        String result = instance.doOCR(imageFile);  
	        return result;  
	    } catch (TesseractException e) {  
	        System.err.println(e.getMessage());  
	        return "Error while reading image";  
	    }  
	}

}
