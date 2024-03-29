package com.generalsoft.util;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrimaryGenerator {

		private static final String SERIAL_NUMBER = "XXXX"; // 流水号格式
		private static PrimaryGenerator primaryGenerater = null;

		private PrimaryGenerator() {
		}

		/**
		 * 取得PrimaryGenerater的单例实现
		 * 
		 * @return
		 */
		public static PrimaryGenerator getInstance() {
			if (primaryGenerater == null) {
				synchronized (PrimaryGenerator.class) {
					if (primaryGenerater == null) {
						primaryGenerater = new PrimaryGenerator();
					}
				}
			}
			return primaryGenerater;
		}

		/**
		 * 生成下一个编号
		 */
		public synchronized String generaterNextNumber(String sno) {
			String id = null;
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			if (sno == null || sno == "") {
				id = formatter.format(date) + "0001";
			} else {
				int count = SERIAL_NUMBER.length();
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < count; i++) {
					sb.append("0");
				}
				DecimalFormat df = new DecimalFormat("0000");
				id = formatter.format(date)
						+ df.format(1 + Integer.parseInt(sno.substring(8, 12)));
			}
			return id;
		}
	
}
