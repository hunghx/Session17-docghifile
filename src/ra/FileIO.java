package ra;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileIO {
    public static void main(String[] args) throws IOException {
        // Bai tạp , tạo menu crud danh sách sinh viên sử dụng IO file để lưu trữ dữ liệu


//        File file = new File("text.pptx");
//        if(file.exists()){
//            System.out.println("file có tồn tại");
//        }
//        if (file.canRead()){
//            System.out.println("file có thể đọc");
//        }
//        if (file.canWrite()){
//            System.out.println("file có thể ghi");
//        }
//        if (file.isDirectory()){
//            System.out.println("là 1 directory");
//        }
//        if(file.isFile()){
//            System.out.println("là 1 file");
//        }
//        if (file.isAbsolute()){
//            System.out.println("file là tuyệt đối");
//        }
//        if (file.isHidden()){
//            System.out.println("file đang bị ẩn");
//        }
//        // lấy ra đường dẫn tuyết đối
//        System.out.println("đường dẫnn tuyệt đối là :"+file.getAbsolutePath());
//        // đường dẫn tương đối : \src\Main.java
//        // đường dẫn lí tưởng :
//        // C:\Users\hung1\OneDrive\Desktop\demoApi đường dẫn tuyệt đối
//        // C:\Users\hung1\OneDrive\Desktop\scan\..\demoApi : đường dẫn lí tưởng
//        System.out.println("đường dẫn lí tưởng là :"+file.getCanonicalPath());
//
//        System.out.println("Tên file "+file.getName());
//        System.out.println("Tên thư mục cha"+file.getParent());
//
//        // ĐỘ DÀI  nội dung của file
//        System.out.println("kích thước file "+file.length());
//
//        // list file
//        File file1 =new File("C:\\Users\\hung1\\OneDrive\\Desktop\\demoApi\\Session17-IOFile-Serializable\\list");
//        for (File f:file1.listFiles()) {
//            System.out.println(f.getName());
//        }
//
//        // xóa file
////        System.out.println(file1.listFiles()[0].delete());
//        // tạo thư mục
//        File file2 = new File("dirs");
//        if (!file2.exists()){
//            file2.mkdir();
//        }
//
//        // tạo file
//        File file3 = new File("text.pptx");
//        if (!file3.exists()){
//            file3.createNewFile();
//        }
//        String text =  readFromFileCharacter("demo.txt");
//        System.out.println(text);
//        String content = "• Bộ nhớ đệm (buffer) cho một nơi lưu trữ tạm thời để tăng hiệu quả\n" +
//                "củathao tácđọc/ghiđữliệu\n" +
//                "• Bộ nhớ đệm trong java được hỗ trợ qua việc sử dụng lớp\n" +
//                "BufferWriter trong góijava.io";
//        System.out.println(writeToFile("demo1.txt",content));
//        List<Student> students = new ArrayList<>();
//        students.add(new Student(1,"Hùng"));
//        writeToFileBinary("student.txt",students);
        List<Student> students = readFromFileBinary("student.txt");
//        students.add(new Student(3,"Khánh"));
//        students.add(new Student(4,"Phương"));
//        writeToFileBinary("student.txt",students);
        for (Student s:students
             ) {
            System.out.println(s);
        }
    }
    public static String readFromFileCharacter(String path){
        StringBuilder content = new StringBuilder();
        File file = new File(path);
        if (!file.exists()){
            System.err.println("file không tồn tại");
            return null;
        }
        FileReader fileReader= null;
        BufferedReader bufferedReader = null;
        try{
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            // đọc file
            String line = null;
            while ((line=bufferedReader.readLine())!=null){
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fileReader!=null)
                fileReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (bufferedReader!=null)
                bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return content.toString();
    }
    public  static boolean writeToFile(String pathName, String content){
        File file = new File(pathName); // có cần kiểm tra tồn tại không ?
//        if (!file.exists()){
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
        FileWriter fileWriter= null;
        BufferedWriter bufferedWriter = null;
        try{
            fileWriter = new FileWriter(file); // ghi đè
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }finally {
            if (bufferedWriter!=null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fileWriter!=null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        return true;
    }
    // List là 1 object
    public static <T> List<T> readFromFileBinary(String path){
        List<T> list = new ArrayList<>();
        StringBuilder content = new StringBuilder();
//        File file = new File(path);
//        if (!file.exists()){
//            System.err.println("file không tồn tại");
//            return new ArrayList<>();
//        }
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream(path);
            ois =new ObjectInputStream(fis);
            list = (List<T>) ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }finally {
            if(ois!=null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fis!=null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return list;
    }
    public static <T> boolean writeToFileBinary(String pathName, List<T> list){
        File file = new File(pathName); // có cần kiểm tra tồn tại không ?
        file.length();
        FileOutputStream fos = null;
        ObjectOutputStream oos =null;
        try{
            fos =new FileOutputStream(file);
            oos =new ObjectOutputStream(fos);
            oos.writeObject(list);

        }catch (IOException e){
            e.printStackTrace();
            return false;
        }finally {
            if(oos!=null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return true;
    }

}
