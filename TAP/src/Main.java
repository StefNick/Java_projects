import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
    public static void main(String[] args){

        String lab1;
        String[] arr = new String[100];
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader bis = new BufferedReader(is);
        char[] cons = {'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','z'};
        int len = 0;

        try {
            System.out.println("Introduce text: ");
            lab1 = bis.readLine();
            arr = lab1.split(" ");
            lab1 = null;
            System.out.print("Introduce length: ");
            len = Integer.parseInt(bis.readLine());
            }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        for(int i = 0; i < arr.length; i++) {
            if (arr[i].length() == len) {
                for(int k = 0; k < cons.length; k++){
                    if (arr[i].charAt(0) == cons[k]) {
                        System.out.println(arr[i]);
                        break;
                    }
                }
            }
        }
    }
}




















