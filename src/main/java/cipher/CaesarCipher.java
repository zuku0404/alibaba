package cipher;

public class CaesarCipher {
    private static final int SHIFT = 4;
    private static final int NUMBER_SIGNS_IN_ASCII = 127;

    public static String encryptPassword(String password) {
        StringBuilder encryptedPassword = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            char sign =  password.charAt(i);
            if (sign + SHIFT > NUMBER_SIGNS_IN_ASCII) {
                encryptedPassword.append((char) (sign - (NUMBER_SIGNS_IN_ASCII - SHIFT)));
            } else {
                encryptedPassword.append((char) (sign + SHIFT));
            }
        }
        return encryptedPassword.toString();
    }

    public static String decryptPassword(String encryptedPassword) {
        int shift = 4;
        int numberSignsInAscii = 127;
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < encryptedPassword.length(); i++) {
            char sign = encryptedPassword.charAt(i);
            if (sign - shift < 0) {
                password.append((char) (sign + (numberSignsInAscii - shift)));
            } else {
                password.append((char) (sign - shift));
            }
        }
        return password.toString();
    }
}

