import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class EquivalencePartitioningTestCaseGenerator3 {

    public static void main(String[] args) {
        String className = "Test1"; // Thay đổi thành tên lớp chứa hàm cần kiểm thử

        try {
            Class<?> clazz = Class.forName(className);
            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods) {
                List<Object[]> testCases = generateTestCases(method);
                writeTestCasesToFile(method.getName(), testCases);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Object[]> generateTestCases(Method method) {
        List<Object[]> testCases = new ArrayList<>();

        Parameter[] parameters = method.getParameters();
        int numParams = parameters.length;

        // Tạo một mảng chứa danh sách các giá trị đại diện cho từng kiểu dữ liệu
        Object[][] representativeValues = {
                {-1, 0, 1},                               // int
                {-1L, 0L, 1L},                             // long
                {(short) -1, (short) 0, (short) 1},         // short
                {-1.0f, 0.0f, 1.0f},                       // float
                {-1.0, 0.0, 1.0}                           // double
                // Các kiểu dữ liệu khác có thể mở rộng ở đây
        };

        // Duyệt qua từng tham số của hàm và thêm các giá trị đại diện vào testCases
        for (int i = 0; i < numParams; i++) {
            Class<?> type = parameters[i].getType();
            Object[] values = representativeValues[i];

            addEquivalenceClasses(testCases, values);
        }

        return testCases;
    }

    private static void addEquivalenceClasses(List<Object[]> testCases, Object[] values) {
        if (testCases.isEmpty()) {
            for (Object value : values) {
                testCases.add(new Object[]{value});
            }
        } else {
            List<Object[]> newTestCases = new ArrayList<>();
            for (Object[] existingCase : testCases) {
                for (Object value : values) {
                    Object[] newCase = new Object[existingCase.length + 1];
                    System.arraycopy(existingCase, 0, newCase, 0, existingCase.length);
                    newCase[existingCase.length] = value;
                    newTestCases.add(newCase);
                }
            }
            testCases.clear();
            testCases.addAll(newTestCases);
        }
    }

    private static void writeTestCasesToFile(String functionName, List<Object[]> testCases) {
        try (FileWriter writer = new FileWriter("Equivalence_" + functionName + ".txt")) {
            for (Object[] testCase : testCases) {
                for (Object param : testCase) {
                    writer.write(param.toString() + " ");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
