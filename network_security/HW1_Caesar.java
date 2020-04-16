public class Caesar {
    public static void main(String[] args) {
        String plainText = "The quick brown fox jumps over the lazy dog.";
        String cipherText = Caesar(plainText, 2, "encrypt");
        String rst = Caesar(cipherText, 2, "decrypt");

        System.out.println("plain text: " + plainText);
        System.out.println("ciper text: " + cipherText);
        System.out.println("result: " + rst);
    }

    public static String Caesar(String plain, int key, String mode) {
        String word = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String upperPlain = plain.toUpperCase();
        String result = "";

        if(mode.equals("decrypt")) {
            key = (word.length() - key) % word.length();
            System.out.println("dec"+key);
        }
        else if (mode.equals("encrypt"))
            key = key % word.length();
        else
            return "err";

        for(int i = 0; i < upperPlain.length(); i++) {
            char temp = upperPlain.charAt(i);
            if ( temp >= 'A' && temp <= 'Z')
                result += word.charAt((word.indexOf(temp)+key)%word.length());
            else
                result += temp;
        }

        return result;
    }
}


