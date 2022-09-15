public class InputValidator {
    // Syftet med denna klass är att validera inputs

    public static boolean isValidUserName(String name) {
        if(name.length() >= 2) {
            return true;
        }
        return false;
    }

    public static boolean isEmail(String email) {
        if (!email.contains(".")) {
            return false;
        }

        int lastIndexOfDot = email.lastIndexOf(".");
        boolean hasAlpha = email.contains("@");
        String lastBit = email.substring(lastIndexOfDot);

        return hasAlpha && lastBit.equals(".com") || lastBit.equals(".se"); // Har @ och .COM ELLER .SE
    }

}
