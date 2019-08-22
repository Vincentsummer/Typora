package offerTest;

public class ReplaceBlank_5 {

	public static void main(String[] args) {
		String a = "Welcome to Hangzhou!";
		int len = a.length();
		String s = replaceBlank(a, len);
		System.out.println(s);
	}
	
	public static String replaceBlank(String a, int len) {
		if (a == null && len <= 0)
			return "";
		String s = a.replaceAll(" ", "%20");
		return s;
	}

}
