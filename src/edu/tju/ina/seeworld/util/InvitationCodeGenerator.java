package edu.tju.ina.seeworld.util;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class InvitationCodeGenerator {
	public static final String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 *生成一字数目的随机字符串，由大写和小写字母以及数字组成
	 * 
	 * @param length
	 *            生成字符串的长度 
	 * @return 生成的随机字符串
	 */
	public static String generateString(int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(random.nextInt(chars.length())));
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Set<String> stringPool = new HashSet<String>();
		long start = new Date().getTime();
		while(true){
			String string = InvitationCodeGenerator.generateString(10);
			System.out.println(string);
			if(stringPool.contains(string)){
				long end = new Date().getTime();
				long duration = end-start;
				System.out.println("共执行了"+duration/1000+"秒，共生成"+stringPool.size()+"个字符串。");
				throw new RuntimeException("Duplicated ID!");
			}
			stringPool.add(string);
		}
	}
}
