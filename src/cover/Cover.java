package cover;

import cfg.ICFG;
import cfg.generation.CFGGeneration;
import cfg.generation.CFGGenerationSubCondition;
import cfg.nodes.ICfgNode;
import mark.Method;
import mark.Utils;
import org.eclipse.jdt.core.dom.*;

import java.io.*;
import java.util.*;
import java.util.function.DoubleToIntFunction;

public abstract class Cover {
    protected Method method;
    protected ICFG cfg;
    protected ICFG cfgsubcondition;
    protected List<ICfgNode> nodes;
    protected List<String> marks;

    protected Set<Integer> offsets;
    protected Set<Integer> lineInFunctions;


    public Cover(List<String> pathMarks, String filePath, Method method) {
        this.method = method;
        nodes = new ArrayList<>();
        marks = new ArrayList<>();
        offsets = new HashSet<>();
        lineInFunctions = new HashSet<>();
        readMark(pathMarks);

        try {
            String fileContent = Utils.readFileContent(filePath);
            ASTParser parser = ASTParser.newParser(AST.JLS8);
            parser.setSource(fileContent.toCharArray());
            parser.setKind(ASTParser.K_COMPILATION_UNIT);

            final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

            cu.accept(new ASTVisitor() {

                // Thăm khai báo một phương thức

                public boolean visit(MethodDeclaration node) {
                    // Lay body method
                    CFGGeneration cfgGenerration = new CFGGeneration(node);
                    cfg = cfgGenerration.generateCFG();

                    CFGGenerationSubCondition cfgGenerrationforsubcondition = new CFGGenerationSubCondition(node,1);
                    cfgsubcondition = cfgGenerrationforsubcondition.generateCFG();

                    ArrayList<ICfgNode> nodesCFG = cfg.getAllNodes();
                    for(ICfgNode nodeCFG : nodesCFG){
                        String contentNode = nodeCFG.getContent();
                        if(!contentNode.equals("Begin") && !contentNode.equals("End") && !contentNode.equals("{") && !contentNode.equals("}")){
                            nodes.add(nodeCFG);
                        }
                    }
                    return true;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMark(List<String> pathMarks){
        try {
            for (String pathMark : pathMarks){
                FileReader fileReader = new FileReader(pathMark);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                // Đọc từng dòng từ file
                while ((line = bufferedReader.readLine()) != null) {
                    marks.add(line);
                }
                bufferedReader.close();
                fileReader.close();
            }
        } catch (IOException e) {
            System.err.println("Có lỗi khi đọc file: " + e.getMessage());
        }
    }



    public abstract String getCover();

    public StringBuilder executionPath(){
        StringBuilder result = new StringBuilder();
        for(String mark : marks){
            String isCondition = findIsCondition(mark);
            if(isCondition == null || !isCondition.equals("true")){
                String statement = findStatement(mark);
                if (statement != null){
                    result.append(statement).append(" -> ");
                }

                String surroundingControlBlock = findSurroundingControlBlock(mark);
                if(surroundingControlBlock != null){
                    result.append(surroundingControlBlock).append(" -> ");
                }
            }
        }
        return result;
    }

    private int getAllNumberNode() {
        int count = 0;
        ArrayList<ICfgNode> nodes = cfg.getAllNodes();
        for(ICfgNode node : nodes){
            String contentNode = node.getContent();
            if(!contentNode.equals("Begin") && !contentNode.equals("End") && !contentNode.equals("{") && !contentNode.equals("}")){
                count ++;
            }
        }
        return count;
    }

    protected String findStatement(String line) {
        String statement = null;
        if (line.contains("statement")) {
            statement = line.substring(line.indexOf("statement"));
        }
        if (statement != null) {
            int start = statement.indexOf("$");
            int end;
            if (statement.contains("#")) {
                end = statement.indexOf("#");
            } else if (statement.contains(";")) {
                end = statement.indexOf(";") + 1;
            } else {
                end = statement.length();
            }
            statement = statement.substring(start + 1, end);
        }
        return statement;
    }

    protected int findOffset(String line){
        String offset = "0";
        if (line.contains("offset")) {
            offset = line.substring(line.indexOf("offset"));
        }
        if (!offset.equals("0")) {
            int start = offset.indexOf("$");
            int end;
            if (offset.contains("#")) {
                end = offset.indexOf("#");
            } else if (offset.contains(";")) {
                end = offset.indexOf(";") + 1;
            } else {
                end = offset.length();
            }
            offset = offset.substring(start + 1, end);
        }
        return Integer.parseInt(offset);
    }
    
    protected  int findLineInFunction(String line){
        String lineInFunction = "0";
        if (line.contains("line-in-function")) {
            lineInFunction = line.substring(line.indexOf("line-in-function"));
        }
        if (!lineInFunction.equals("0")) {
            int start = lineInFunction.indexOf("$");
            int end;
            if (lineInFunction.contains("#")) {
                end = lineInFunction.indexOf("#");
            } else if (lineInFunction.contains(";")) {
                end = lineInFunction.indexOf(";") + 1;
            } else {
                end = lineInFunction.length();
            }
            lineInFunction = lineInFunction.substring(start + 1, end);
        }
        return Integer.parseInt(lineInFunction);
    }

    protected String findIsCondition(String line){
        String condition = null;
        if (line.contains("is_condition")) {
            condition = line.substring(line.indexOf("is_condition"));
        }
        if (condition != null) {
            int start = condition.indexOf("$");
            int end;
            if (condition.contains("#")) {
                end = condition.indexOf("#");
            } else if (condition.contains(";")) {
                end = condition.indexOf(";") + 1;
            } else {
                end = condition.length();
            }
            condition = condition.substring(start + 1, end);
        }
        return condition;
    }

    protected String findSurroundingControlBlock(String line){
        String surrounding = null;
        if (line.contains("surrounding-control-block")) {
            surrounding = line.substring(line.indexOf("surrounding-control-block"));
        }
        if (surrounding != null) {
            int start = surrounding.indexOf("$");
            int end;
            if (surrounding.contains("#")) {
                end = surrounding.indexOf("#");
            } else if (surrounding.contains(";")) {
                end = surrounding.indexOf(";") + 1;
            } else {
                end = surrounding.length();
            }
            surrounding = surrounding.substring(start + 1, end);
        }
        return surrounding;
    }

    protected boolean markIsNode(String mark){
        return findStatement(mark) != null && !findStatement(mark).equals("{") && !findStatement(mark).equals("}");
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getCoverAll() {
        StringBuilder coverAll = new StringBuilder();
        String[] lines = method.getNewBody().split("\n");
        for (String line : lines) {
            if (line.contains("Mark.print")) {
                line = line.substring(line.indexOf("Mark.print") + 12, line.length() - 3);
                coverAll.append(line).append("\n");
            }
        }
        return coverAll.toString();
    }
}
