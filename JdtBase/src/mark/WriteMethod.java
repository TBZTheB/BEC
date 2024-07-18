package mark;

import org.eclipse.jdt.core.dom.Modifier;

import java.io.*;
import java.util.List;

public class WriteMethod {
    public WriteMethod(String filePath, List<Method> methods) {
        String oldPackage = "package fileTest;";
        String newPackage = "package fileCloned;";

        try {
            // Đọc nội dung từ tệp văn bản
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
            reader.close();

            StringBuilder newMethod = new StringBuilder();
            int changePosition = 0;
            // Thực hiện thay đổi nội dung
            for(Method method : methods){
                boolean checkStatic = false;
                List<Modifier> modifiers = method.getModifiers();
                for (Modifier modifier : modifiers) {
                    if (modifier.toString().equals("static")) {
                        checkStatic = true;
                        break;
                    }
                }
                int positionStatic = method.getStartPosition() + 3;
                if(!checkStatic){
                    changePosition += 7;
                    if(!modifiers.isEmpty()){
                        positionStatic += modifiers.get(0).toString().length() + 1;
                    }
                    content = content.replace(positionStatic, positionStatic, "static ");
                }

                newMethod = content.replace(changePosition + method.getBody().getStartPosition() + 3, changePosition + method.getEndPosition() + 3, method.getBody().toString());
                changePosition += method.getNewBody().length() - (method.getEndPosition() - method.getBody().getStartPosition());
            }
            String newContent = newMethod.toString().replace(oldPackage, newPackage);

            // Ghi nội dung mới vào tệp văn
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(newContent);
            writer.close();

            System.out.println("Thay đổi nội dung tệp thành công.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Không thể thay đổi nội dung tệp.");
        }
    }

    public static void write(String filePath,  List<Method> methods) {
        new WriteMethod(filePath, methods);
    }
}
