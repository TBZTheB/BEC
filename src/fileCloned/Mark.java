package fileCloned;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Mark {
    public static boolean print(String append){
        try {
            // Mở file để ghi (nếu file không tồn tại, nó sẽ được tạo ra)
            //Setup start
            FileWriter fileWriter = new FileWriter("JdtBase/src/cover/mark4.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(append + '\n');

            bufferedWriter.close();
            fileWriter.close();

//            System.out.println("Ghi file thành công!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
