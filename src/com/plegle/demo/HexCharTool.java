package com.plegle.demo;

import java.io.ByteArrayOutputStream;

public class HexCharTool {

	/*
	 * 16���������ַ���
	 */
	private static String hexString = "0123456789ABCDEF";

	/*
	 * ���ַ��������16��������,�����������ַ����������ģ�
	 */
	public static String encode(String str) {

		// ����Ĭ�ϱ����ȡ�ֽ�����
		byte[] bytes = str.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		// ���ֽ�������ÿ���ֽڲ���2λ16��������
		for (int i = 0; i < bytes.length; i++) {
			// int d = (bytes[i] & 0x0f) >> 0;
			// /System.out.println("bytes["+i+"]:"+bytes[i]+"_"+d);//bytes[i]�õ����Ƕ�Ӧ���ַ�ASCIIֵ
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));// 1-4
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));// 1-2

			if (i != (bytes.length - 1)) {
				sb.append(" ");
			}
		}
		return sb.toString();
	}

	/*
	 * ��16�������ֽ�����ַ���,�����������ַ����������ģ� decode(String
	 * bytes)���������bytes�ַ��������д����toUpperCase()
	 */

	public static String decode(String bytes) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(
				bytes.length() / 2);
		// ��ÿ2λ16����������װ��һ���ֽ�
		for (int i = 0; i < bytes.length(); i += 3) {
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
					.indexOf(bytes.charAt(i + 1))));
		}
		return new String(baos.toByteArray());
	}

	public static void main(String[] args) {
		System.out.println(HexCharTool.encode("���".toUpperCase()));
		System.out.println(HexCharTool.decode("C4 E3 BA C3".toUpperCase()));

	}

}
