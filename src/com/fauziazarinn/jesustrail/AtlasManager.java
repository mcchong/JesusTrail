package com.fauziazarinn.jesustrail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.os.Environment;


/*
 * @brief - Loads the atlas .zip file
 * into the correct directory on the
 * sd card.
 * N.B.  If the atlas directory becomes
 * larger than 50MB, consider using
 * APK expansion files:
 * http://developer.android.com/google/play/expansion-files.html
 */

// TODO: Download only if absent
// TODO: Move file in chunks 
// http://stackoverflow.com/questions/8820837/how-to-write-a-large-file-from-an-applications-assets-folder-to-the-sd-card-with?rq=1
// TODO: Make Async task
public class AtlasManager {
	
	// @brief osmdroid directory
	private static final String osmdroid_dir = "osmdroid";
	
	/*
	 * @brief - loads the atlas from res/raw
	 * to 
	 */
	public static boolean loadAtlas(Context context) {
		String state = Environment.getExternalStorageState();
		if (!Environment.MEDIA_MOUNTED.equals(state)) {
			// cannot read/write to SD card
			return false;
		}
		
		File osmdroidDir = new File(Environment.getExternalStorageDirectory(), osmdroid_dir);
				
		if (!osmdroidDir.isDirectory() && !osmdroidDir.mkdirs()) {
			// directory does not exist
			return false;
		}
		
		try {
			InputStream inStream = context.getResources().openRawResource(R.raw.atlas);
			int size = inStream.available();
			byte[] buffer = new byte[size];
			
			FileOutputStream outStream = new FileOutputStream(new File(osmdroidDir, 
					context.getResources().getString(R.string.raw_res_atlas)));
			
			inStream.read(buffer);
			outStream.write(buffer);
			
			inStream.close();
			outStream.close();
		} catch (FileNotFoundException e) {
			// File not found
			e.printStackTrace();
			return false;
		} catch (NotFoundException e) {
			// Not found
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// IO exception
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
