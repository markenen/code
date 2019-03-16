package capture;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Fileutil {

	public static byte[] readFile(File file) throws Exception {
		// 内存 -> 缓冲
		// Buffered
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream iis = null;
		try {
			iis = new BufferedInputStream(new FileInputStream(file));
			int length = -1;
			byte[] buffer = new byte[1024];
			while ((length = iis.read(buffer, 0, 1024)) != -1) {
				baos.write(buffer, 0, length);
				baos.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (iis != null) {
				iis.close();
			}
		}
		return baos.toByteArray();
	}
}
