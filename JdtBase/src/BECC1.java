import java.io.IOException;
import java.nio.file.Paths;

public class BECC1 {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        // Lấy bộ nhớ sử dụng ban đầu
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // Gọi Garbage Collector để giải phóng bộ nhớ không sử dụng
        double startMemory = runtime.totalMemory() - runtime.freeMemory();

        String currentPath = Paths.get("").toAbsolutePath().toString();
        String filePath = currentPath + "\\Test.java";

        new ASTAnalyzerBVAECP(filePath);
        new ThucThiTuongTrungC1(filePath);

        // Kết thúc thời gian
        long endTime = System.currentTimeMillis();

        // Lấy bộ nhớ sử dụng sau khi chạy chương trình
        double endMemory = runtime.totalMemory() - runtime.freeMemory();

        // Tính toán thời gian chạy và bộ nhớ sử dụng
        long timeElapsed = endTime - startTime;
        double memoryUsed = (endMemory - startMemory) / 1048576;
        System.out.println("Time for test data generation: " + timeElapsed  + " milliseconds");
        System.out.println("Use memory: " + memoryUsed  + " bytes");
    }
}
