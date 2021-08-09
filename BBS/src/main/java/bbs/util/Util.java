package bbs.util;

public class Util {
	public static int str2Int(String str) {
		// 120A이렇게 들어온다면 120을 리턴해줍니다.
		int result = 0;
		String temp = "";
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				temp += str.charAt(i);
			}
		}
		result = Integer.parseInt(temp);
		return result;
	}
	
	public static int str2Int2(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return 0;
		}
	}
}