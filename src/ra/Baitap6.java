package ra;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Baitap6 {
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

        // chuyển thành array
        String [] array =  text.split("\\s");
        Map<String,Integer> map = new HashMap<>();
        // đặc điểm sắp xếp ko theo thứ tự truyền vào
        for (String word: array
             ) {
            if (map.containsKey(word)){
                // lấy ra số lần xuất hiện trước đó
                int count = map.get(word);
                // đã tồn tại
                map.put(word,count+1);
            }else {
                map.put(word,1);
            }
        }
        int countMax = 1;
        for (Map.Entry<String , Integer> entry: map.entrySet()
             ) {
            if (entry.getValue()>countMax){
                countMax = entry.getValue();
            }
        }

        // hiheenr thị các từ có cùng tần suất xuất hiện
        for (Map.Entry<String , Integer> entry: map.entrySet()
        ) {
            if (entry.getValue()== countMax){
                System.out.println("Word "+ entry.getKey()  +"| Count = "+entry.getValue());
            }
        }
    }
}
