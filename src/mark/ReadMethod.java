package mark;

import org.eclipse.jdt.core.dom.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadMethod {
    public List<Method> methodList = new ArrayList<>();

    public ReadMethod(String filePath) {
        try {
            String fileContent = Utils.readFileContent(filePath);
            parse(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parse(String fileContent) {
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setSource(fileContent.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        cu.accept(new ASTVisitor() {
            // Thăm khai báo một phương thức

            String className = null;

            public boolean visit(TypeDeclaration node) {
                className = node.getName().getIdentifier();
                return super.visit(node);
            }

            public boolean visit(MethodDeclaration node) {
                // Lay vi tri bat dau va ket thuc cua method
                int startPosition = node.getStartPosition();
                int endPosition = startPosition + node.getLength();

                // Lay string method
                String methodString = fileContent.substring(startPosition, endPosition);

                // Lay body method
                Block body = node.getBody();

                // Lay ten method
                SimpleName name = node.getName();

                // Lay kieu tra ve cua phuong thuc: void, boolean
                Type returnValueType = node.getReturnType2();


                // Lấy danh sách tham số tryền vào method
                List<MethodDeclaration> parameters = node.parameters();

                // Lấy danh sách modifier: public/private/protected, @Override
                List<Modifier> modifiers = node.modifiers();

                Method method = new Method(className, modifiers, returnValueType, name, parameters, body, startPosition,
                        endPosition, methodString);
                methodList.add(method);

                return true;
            }

            public void Huy(){}
        });
    }

    public static List<Method> getMethodList(String filePath) {
        ReadMethod readMethod = new ReadMethod(filePath);
        return readMethod.methodList;
    }


}
