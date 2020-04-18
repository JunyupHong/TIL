public class PBox {
    public static void main(String[] args) {
        String PlainText = "ABCDEFGH";
        int[] index1 = { 2, 6, 3, 1, 4, 8, 5, 7 };
        int[] index2 = { 4, 1, 3, 5, 7, 2, 8, 6 };

        String rst1 = PBox(PlainText, index1);
        String rst2 = PBox(rst1, index2);

        System.out.println(rst1);
        System.out.println(rst2);
    }


    public static String PBox(String plain, int[] index) {
        String result = "";
        if (plain.length() != 8) return "err";

        for (int i : index) {
            char c = plain.charAt(i-1);
            result += c;
        }
        return result;
    }
}


