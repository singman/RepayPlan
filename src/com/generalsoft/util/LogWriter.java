package com.generalsoft.util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
public class LogWriter {	
		// 可以写作配置：true写文件; false输出控制台	
		private static boolean fileLog = true;	
		private static String logFileName = "C:\\log.log";	
		private static File file = new File(logFileName);	
		public void log(String info) throws IOException 
		{		
			OutputStream out = getOutputStream();	
			out.write(info.getBytes("utf-8"));
		}		
		public  OutputStream getOutputStream() throws IOException
		{		
			if (fileLog) {
				if (!file.exists())			
					file.createNewFile();		
				return new FileOutputStream(file);	
				}
			else {
				return System.out;	
				}	
			}
		}
	

