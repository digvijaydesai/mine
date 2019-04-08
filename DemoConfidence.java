package com.howtodoinjava;

import java.io.File;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.TessAPI1;
import net.sourceforge.tess4j.util.LoggHelper;
import net.sourceforge.tess4j.util.Utils;
import net.sourceforge.tess4j.util.ImageIOHelper;

import com.ochafik.lang.jnaerator.runtime.NativeSize;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.StringArray;
import com.sun.jna.ptr.PointerByReference;

import net.sourceforge.lept4j.Box;
import net.sourceforge.lept4j.Boxa;
import static net.sourceforge.lept4j.ILeptonica.L_CLONE;
import net.sourceforge.lept4j.Leptonica1;
import net.sourceforge.lept4j.Pix;
import net.sourceforge.lept4j.util.LeptUtils;
import net.sourceforge.tess4j.ITessAPI.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.sourceforge.tess4j.ITessAPI.FALSE;
import static net.sourceforge.tess4j.ITessAPI.TRUE;

public class DemoConfidence {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String testResourcesDataPath = "C:\\Users\\Yogini\\testdata";
		String datapath = "C:\\Users\\Yogini\\testdata";
	    String language = "mcr";
	    String expOCRResult = "The (quick) [brown] {fox} jumps!\nOver the $43,456.78 <lazy> #90 dog";
	    TessBaseAPI handle =TessAPI1.TessBaseAPICreate();
		// logger.info("TessBaseAPIGetComponentImages");
	        File image = new File( "C:\\bankImage\\b.jpg");
	        int expResult = 12; // number of lines in the test image
	        Pix pix = Leptonica1.pixRead(image.getPath());
	        TessAPI1.TessBaseAPIInit3(handle, datapath, language);
	        TessAPI1.TessBaseAPISetImage2(handle, pix);
	        
	       
	        PointerByReference pixa = null;
	        PointerByReference blockids = null;
	        Boxa boxes = TessAPI1.TessBaseAPIGetComponentImages(handle, TessPageIteratorLevel.RIL_TEXTLINE, TRUE, pixa, blockids);
	        //Boxa boxes = TessAPI1.TessBaseAPIGetRegions(handle, pixa); // equivalent to TessPageIteratorLevel.RIL_BLOCK
	        int boxCount = Leptonica1.boxaGetCount(boxes);
	        int conf = TessAPI1.TessBaseAPIMeanTextConf(handle);
	        System.out.println("Conf"+conf);
	        for (int i = 0; i < boxCount; i++) {
	            Box box = Leptonica1.boxaGetBox(boxes, i, L_CLONE);
	            if (box == null) {
	                continue;
	            }
	            TessAPI1.TessBaseAPISetRectangle(handle, box.x, box.y, box.w, box.h);
	            Pointer utf8Text = TessAPI1.TessBaseAPIGetUTF8Text(handle);
	            String ocrResult = utf8Text.getString(0);
	            TessAPI1.TessDeleteText(utf8Text);
	            conf = TessAPI1.TessBaseAPIMeanTextConf(handle);
	            System.out.print(String.format("Box[%d]: x=%d, y=%d, w=%d, h=%d, confidence: %d, text: %s", i, box.x, box.y, box.w, box.h, conf, ocrResult));
	            LeptUtils.dispose(box);
	        }

	}

}
