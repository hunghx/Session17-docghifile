package ra;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Baitap3 {
    public static void main(String[] args) {
        String text = "";
        // đọc file
        try {
            FileReader fileReader =  new FileReader("source.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line=bufferedReader.readLine())!=null){
                text+=" " +line;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(text);
        // chuyển đoạn văn thành 1 mảng các từ
        String[] arr  = text.split("\\s+");
        System.out.println(Arrays.toString(arr));
        int max = arr[0].length(); // gỉa sử từ đầu tiên có độ dài lớn nhất
        int indexMax = 0;
        for (int i = 1; i < arr.length; i++) {
            if (max<arr[i].length()){
                max = arr[i].length();
                indexMax=i;
            }
        }

        for (String s:arr
             ) {
            if (s.length()==max){
                System.out.println(s);
            }
        }

    }
}
