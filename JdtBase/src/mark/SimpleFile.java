package mark;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SimpleFile {
    private String filePath;
    public SimpleFile(String filePath) {
        this.filePath = filePath;
    }

    public String copy(String targetDirectoryPath) {
        String targetFilePath = "";
        try {
            FileInputStream inputStream = new FileInputStream(new File(filePath));
            byte[] buffer = new byte[1024];
            int bytesRead;

            // Tạo đường dẫn đến tệp đích
            targetFilePath = targetDirectoryPath + "/" + new File(filePath).getName();

            // Ghi dữ liệu vào tệp đích
            FileOutputStream outputStream = new FileOutputStream(new File(targetFilePath));
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Đóng luồng
            inputStream.close();
            outputStream.close();

            System.out.println("Sao chép tệp .java thành công.");
        } catch (
                IOException e) {
            e.printStackTrace();
            System.out.println("Không thể sao chép tệp .java.");
        }
        return targetFilePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
