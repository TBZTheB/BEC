package mark;

import org.eclipse.jdt.core.dom.*;

import java.util.List;

public class Method {
    private String className;
    private List<Modifier> modifiers;
    private Type returnValueType;
    private SimpleName name;
    private List<MethodDeclaration> parameters;
    private Block body;
    private int startPosition;
    private int endPosition;
    private String methodString;
    private String newBody;

    public Method(String className, List<Modifier> modifiers, Type returnValueType, SimpleName name,
                  List<MethodDeclaration> parameters, Block body, int startPosition, int endPosition, String methodString) {
        this.className = className;
        this.modifiers = modifiers;
        this.returnValueType = returnValueType;
        this.name = name;
        this.parameters = parameters;
        this.body = body;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.newBody = "";
        this.methodString = methodString;
    }

    public String getMethod() {
        String method = "";
        for (Object modifier : modifiers) {
            method += modifier.toString() + " ";
        }
        method += returnValueType.toString() + " ";
        method += name.toString() + "(";
        for (Object m : parameters) {
            method += m.toString() + ", ";
        }
        method = method.substring(0, method.length() - 1);
        method += "\b) " + body;
        return method;
    }

    public String getNewMethod() {
        int bodyPosition = body.getStartPosition() - startPosition;
        StringBuilder method = new StringBuilder(methodString);
        method = method.replace(bodyPosition, method.length(), getNewBody());
        return method.toString();
    }

    public String addStaticToNewMethod(String getNewMethod){
        StringBuilder method = new StringBuilder(getNewMethod);
        int positionStatic = 0;
        boolean checkStatic = false;
        for (Modifier modifier : modifiers) {
            if (modifier.toString().equals("static")) {
                checkStatic = true;
                break;
            }
        }
        if(!checkStatic){
            if(!modifiers.isEmpty()){
                positionStatic += modifiers.get(0).toString().length() + 1;
            }
            method = method.replace(positionStatic, positionStatic, "static ");
        }
        return method.toString();
    }

    public void setBody(String newBody){
        this.body =  stringToBlock(newBody);
    }

    private Block stringToBlock(String body) {
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setKind(ASTParser.K_STATEMENTS);
        parser.setSource(body.toCharArray());
        Block block = (Block) parser.createAST(null);
        return block;
    }

    public int getLineInMethod(int position){
        int lineNumber = 1;
        for (char c : methodString.substring(0, position).toCharArray()) {
            if (c == '\n') {
                lineNumber++;
            }
        }
        return lineNumber;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Modifier> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<Modifier> modifiers) {
        this.modifiers = modifiers;
    }

    public Type getReturnValueType() {
        return returnValueType;
    }

    public void setReturnValueType(Type returnValueType) {
        this.returnValueType = returnValueType;
    }

    public SimpleName getName() {
        return name;
    }

    public void setName(SimpleName name) {
        this.name = name;
    }

    public List<MethodDeclaration> getParameters() {
        return parameters;
    }

    public void setParameters(List<MethodDeclaration> parameters) {
        this.parameters = parameters;
    }

    public Block getBody() {
        return body;
    }

    public void setBody(Block body) {
        this.body = body;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getStartPosition() {
        return startPosition;
    }
    public String getNewBody() {
        return "{\n" + newBody + "\t}";
    }

    public void setNewBody(String newBody) {
        this.newBody = newBody;
    }
}
