package com.howtodoinjava;

import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import static org.opencv.imgproc.Imgproc.*;
import static org.opencv.imgcodecs.Imgcodecs.imread;

public class OpenCvDemo {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		
		System.out.printf("java.library.path: %s%n",
                System.getProperty("java.library.path"));
		String libpath = System.getProperty("java.library.path");
    	libpath = libpath + ";C:/Users/Yogini/Downloads/opencv_java320/opencv_java320;";
    	System.setProperty("java.library.path",libpath);
    	System.out.println("");
    	System.out.printf("java.library.path: %s%n",
                System.getProperty("java.library.path"));
	
       System.loadLibrary("opencv_java320");
       
       String file = "a.jpg";
       File fileName1 = new File(file);
       Mat src = Imgcodecs.imread(fileName1.getAbsolutePath());
       System.out.println(src);
       //Creating an empty matrix to store the result
       Mat dst = new Mat();

       //Scaling the Image
       Imgproc.resize(src, dst, new Size(src.rows()/3, src.rows()/3), 0, 0, 
          Imgproc.INTER_AREA);

       //Writing the image
       Imgcodecs.imwrite("C:/opencv/scale_output.jpg", dst);
		
	
	}

}
