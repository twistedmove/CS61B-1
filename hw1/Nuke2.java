import java.io.*;

public class Nuke2 {
    public static void main(String[] arg) throws Exception {
        BufferedReader keyboard;
        String inputLine;

        keyboard = new BufferedReader(new InputStreamReader(System.in));

        inputLine = keyboard.readLine();

        int len = inputLine.length();
        String a = inputLine.substring(0, 1);
        String b = inputLine.substring(2, len);
        String c = a + b;

        System.out.println(c);
    }
}
