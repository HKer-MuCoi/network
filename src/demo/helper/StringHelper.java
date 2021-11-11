package demo.helper;

import org.jetbrains.annotations.NotNull;

public class StringHelper {

    // Tổng từ 1 chuỗi
    public static int sumFromString(@NotNull String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                sum = sum + Character.getNumericValue(s.charAt(i));
            }
        }
        return sum;
    }

    // loại bỏ số
    public static @NotNull String removeNumber(@NotNull String s) {
        return s.replaceAll("[0-9]", "");
    }

    // loại bỏ chữ cái
    public static @NotNull String removeAllCharacter(@NotNull String s) {
        return s.replaceAll("[a-zA-Z]", "").trim();
    }

    // đảo chuỗi
    public static @NotNull String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    // viết hoa mỗi từ
    public static @NotNull String capEachWord(@NotNull String str) {
        String words[] = str.split("\\s");
        String capitalizeWord = "";
        for (String w : words) {
            String first = w.substring(0, 1);
            String afterfirst = w.substring(1);
            capitalizeWord += first.toUpperCase() + afterfirst + " ";
        }
        return capitalizeWord.trim();
    }

    // viết hoa từ đầu tiên
    public static @NotNull String capFirstWord(final @NotNull String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    // đếm số lần xuất hiện của word trong string
    static int countWord(@NotNull String str, @NotNull String word) {
		int count = 0;
		str.trim();
		String result, sub;
		int lenWord = word.length();
		System.out.println(lenWord);
		for (int i = 0; i < str.length() - lenWord - 1; i++) {
			sub = str.substring(i, i + lenWord);
			if (sub.equals(word))
				count++;
		}
		return count;
	}

//    public static void main(String[] args){
//        String a = "abvnAD1234124afg";
//        String b = "alo anh la teo 13day";
//        System.out.println(StringHelper.sumFromString(b));
//    }
}
