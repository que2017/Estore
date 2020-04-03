package com.duiyi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtil {
	public static void inToOut(InputStream in, OutputStream out) throws IOException {
		byte[] buff = new byte[1024];
		int i = 0;
		while ((i = in.read(buff)) != -1) {
			out.write(buff, 0, i);
		}
	}
	
	public static void close(InputStream in, OutputStream out) {
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				in = null;
			}
		}
		if (out != null) {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				out = null;
			}
		}
	}
	
	private IOUtil() {
	}
}
