package com.plegle.demo;

import java.io.ByteArrayOutputStream;

public class HexCharTool {

	/*
	 * 16进制数字字符集
	 */
	private static String hexString = "0123456789ABCDEF";

	/*
	 * 将字符串编码成16进制数字,适用于所有字符（包括中文）
	 */
	public static String encode(String str) {

		// 根据默认编码获取字节数组
		byte[] bytes = str.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			// int d = (bytes[i] & 0x0f) >> 0;
			// /System.out.println("bytes["+i+"]:"+bytes[i]+"_"+d);//bytes[i]得到的是对应的字符ASCII值
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));// 1-4
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));// 1-2

			if (i != (bytes.length - 1)) {
				sb.append(" ");
			}
		}
		return sb.toString();
	}

	/*
	 * 将16进制数字解码成字符串,适用于所有字符（包括中文） decode(String
	 * bytes)方法里面的bytes字符串必须大写，即toUpperCase()
	 */

	public static String decode(String bytes) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(
				bytes.length() / 2);
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < bytes.length(); i += 3) {
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
					.indexOf(bytes.charAt(i + 1))));
		}
		return new String(baos.toByteArray());
	}

	public static void main(String[] args) {
		System.out.println(HexCharTool.encode("你好".toUpperCase()));
		System.out.println(HexCharTool.decode("C4 E3 BA C3".toUpperCase()));

	}

}
