public class Transposition {
    public static void main(String[] args) {
        String plainText = "Common sense is not so common.";
        String rst = transpositionEncrypt(plainText, 8);

        System.out.println("plain text: " + plainText);
        System.out.println("result: " + rst);
    }

    public static String transpositionEncrypt(String plain, int key) {
        int col = 0;
        String result = "";

        int rowCount = (plain.length() / key) + 1;
        for (col = 0; col < key; col++) {
            for(int row = 0; row < rowCount; row++) {
                if(col + key * row < plain.length())
                    result += plain.charAt(col + key * row);
            }
        }

        return result;
    }
}

