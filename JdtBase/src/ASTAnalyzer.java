import mark.Utils;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import java.io.*;
import java.util.*;

public class ASTAnalyzer {

    public static void main(String[] args) throws IOException {
        String filePath = "Dulieutestbien.java";
        String source = readFileToString(filePath);
        parse(source);
    }

    public ASTAnalyzer(String source) {
        try {
            String fileContent = Utils.readFileContent(source);
            parse(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void parse(String source) {

        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setSource(source.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        cu.accept(new ASTVisitor() {
            @Override
            public boolean visit(MethodDeclaration method) {
                long start = System.currentTimeMillis();
                System.out.println("Method: " + method.getName());
                Map<String, List<Number>> variableBounds = new LinkedHashMap<>();
                Map<String, String> variableTypes = new LinkedHashMap<>();
                List<SingleVariableDeclaration> parameters = method.parameters();
                System.out.println("Parameters: " + parameters);
                for (SingleVariableDeclaration param : parameters) {
//                    System.out.println("Param: " + param.getName().getIdentifier() + " " + param.getType().toString());
                    String varName = param.getName().getIdentifier();
                    String varType = param.getType().toString();
                    variableBounds.put(varName, new ArrayList<>());
                    variableTypes.put(varName, varType);
                }
                //Get the name of all maps
//                System.out.println("Variable name: " + variableBounds.keySet());
                Map<String, List<Number>> variableBetweenValues = new LinkedHashMap<>();


                method.getBody().accept(new ASTVisitor() {
//                    @Override
//                    public boolean visit(VariableDeclarationFragment node) {
//                        String varName = node.getName().getIdentifier();
//                        if (node.getParent() instanceof VariableDeclarationStatement) {
//                            String varType = ((VariableDeclarationStatement) node.getParent()).getType().toString();
//                            variableBounds.put(varName, new ArrayList<>());
//                            variableTypes.put(varName, varType);
//                        }
//                        return super.visit(node);
//                    }


                    @Override
                    public boolean visit(InfixExpression node) {
                        if (node.getOperator() == InfixExpression.Operator.LESS
                                || node.getOperator() == InfixExpression.Operator.LESS_EQUALS
                                || node.getOperator() == InfixExpression.Operator.GREATER
                                || node.getOperator() == InfixExpression.Operator.GREATER_EQUALS
                                || node.getOperator() == InfixExpression.Operator.EQUALS
                                || node.getOperator() == InfixExpression.Operator.NOT_EQUALS) {
                            Expression left = node.getLeftOperand();
                            Expression right = node.getRightOperand();
                            if (left instanceof SimpleName && right instanceof NumberLiteral) {
                                String varName = ((SimpleName) left).getIdentifier();
                                String varType = variableTypes.get(varName);
                                if (varType != null) {
                                    Number value = parseValue(((NumberLiteral) right).getToken(), varType);
                                    List<Number> bounds = variableBounds.get(varName);
                                    if (bounds != null) {
                                        bounds.add(value);
                                    }
                                }
                            }
                        }
                        return super.visit(node);
                    }
                });

                for (Map.Entry<String, List<Number>> entry : variableBounds.entrySet()) {
                    String varName = entry.getKey();
                    List<Number> bounds = entry.getValue();
                    String varType = variableTypes.get(varName);
                    if (!bounds.isEmpty() || varType.equals("int") || varType.equals("float") || varType.equals("double") || varType.equals("short") || varType.equals("long")) {
                        bounds.add(getMinValueOfType(varType));
                        bounds.add(getMaxValueOfType(varType));
                    }

                    //Remove duplicates and sort the bounds
                    Set<Number> set = new HashSet<>(bounds);
                    bounds.clear();
                    bounds.addAll(set);
                    bounds.sort(Comparator.comparing(Number::doubleValue));
//                    System.out.println("Variable: " + varName + "\nVar Type: " + varType +  "\nBounds: " + bounds);
                    List<Number> betweenValues = generateBetweenValues(bounds);
//                    System.out.println("Between values: " + betweenValues);
                    set = new HashSet<>(betweenValues);
                    betweenValues.clear();
                    betweenValues.addAll(set);
                    betweenValues.sort(Comparator.comparing(Number::doubleValue));
                    variableBetweenValues.put(varName, betweenValues);
                }
                // Add bound+, bound- for each variable other than the first and last value of the bound list
                for (Map.Entry<String, List<Number>> entry : variableBounds.entrySet()) {
                    String varName = entry.getKey();
                    List<Number> bounds = entry.getValue();
                    List<Number> newBounds = new ArrayList<>(bounds);
                    for (int i = 1; i < bounds.size() - 1; i++) {
                        Number bound = bounds.get(i);
                        if (bound instanceof Integer) {
                            newBounds.add(bound.intValue() + 1);
                            newBounds.add(bound.intValue() - 1);
                        } else if (bound instanceof Float) {
                            newBounds.add(bound.floatValue() + 0.0001f);
                            newBounds.add(bound.floatValue() - 0.0001f);
                        } else if (bound instanceof Double) {
                            newBounds.add(i, bound.doubleValue() + 0.000001);
                            newBounds.add(i, bound.doubleValue() - 0.000001);
                        } else if (bound instanceof Long) {
                            newBounds.add(i, bound.longValue() + 1);
                            newBounds.add(i, bound.longValue() - 1);
                        } else if (bound instanceof Short) {
                            newBounds.add(i, (short) (bound.shortValue() + 1));
                            newBounds.add(i, (short) (bound.shortValue() - 1));
                        }
                    }
                    bounds.clear();
                    bounds.addAll(newBounds);
                    //Remove duplicates and sort the bounds
                    Set<Number> set = new HashSet<>(bounds);
                    bounds.clear();
                    bounds.addAll(set);
                    bounds.sort(Comparator.comparing(Number::doubleValue));
//                    System.out.println("Bounds: " + bounds);
                }
                // Add min-1, max+1 for each variable for EQC error detection
                for (Map.Entry<String, List<Number>> entry : variableBounds.entrySet()) {
                    String varName = entry.getKey();
                    List<Number> bounds = entry.getValue();
                    String varType = variableTypes.get(varName);
                    if (varType != null) {
                        bounds.add(getMinMValueOfType(varType));
                        bounds.add(getMaxPValueOfType(varType));
                        System.out.println("MinMValue: " + getMinMValueOfType(varType));
                        System.out.println("MaxPValue: " + getMaxPValueOfType(varType));
                        if (varType.equals("float") || varType.equals("double")) {
                            bounds.add(getMinDValueOfType(varType));
                            System.out.println("MinDValue: " + getMinDValueOfType(varType));
                        }
                    }
                    //Remove duplicates and sort the bounds
                    Set<Number> set = new HashSet<>(bounds);
                    bounds.clear();
                    bounds.addAll(set);
                    bounds.sort(Comparator.comparing(Number::doubleValue));
                }

                // Generate test cases, for each bounds value of each variable, randomly select a between value for all other variables, then generate a test case.
                List<List<Number>> testCases = new ArrayList<>();
                List<String> variableNames = new ArrayList<>(variableTypes.keySet());
                for (String variables : variableNames) {
//                    System.out.println("Variable: " + variables);
                    List<Number> bounds = variableBounds.get(variables);
                    for (Number value : bounds) {
                        List<Number> testCase = new ArrayList<>();
                        for (String variable : variableNames) {
                            if (variable.equals(variables)) {
                                testCase.add(value);
                            } else {
                                List<Number> betweenValues = variableBetweenValues.get(variable);
                                Random random = new Random();
                                testCase.add(betweenValues.get(random.nextInt(betweenValues.size())));
                            }
                        }
                        testCases.add(testCase);
                    }
                }
                System.out.println("Number of test cases: " + testCases.size());
                System.out.println("Test cases: " + testCases);
                long end = System.currentTimeMillis();
                System.out.println("Time: " + (end - start) + "ms");
                return super.visit(method);
            }
        });
    }

    private static String readFileToString(String filePath) throws IOException {
        StringBuilder fileData = new StringBuilder(1000);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            char[] buf = new char[10];
            int numRead;
            while ((numRead = reader.read(buf)) != -1) {
                fileData.append(buf, 0, numRead);
            }
        }
        return fileData.toString();
    }

    private static Number parseValue(String token, String varType) {
        switch (varType) {
            case "int":
                return Integer.parseInt(token);
            case "float":
                return Float.parseFloat(token);
            case "double":
                return Double.parseDouble(token);
            case "short":
                return Short.parseShort(token);
            case "long":
                return Long.parseLong(token);
            default:
                throw new IllegalArgumentException("Unsupported type: " + varType);
        }
    }

    private static Number getMinValueOfType(String varType) {
        switch (varType) {
            case "int":
                return Integer.MIN_VALUE + 1;
            case "float":
                return -Float.MAX_VALUE + 0.0001f;
            case "double":
                return -Double.MAX_VALUE + 0.0001;
            case "short":
                return (short) (Short.MIN_VALUE + 1);
            case "long":
                return Long.MIN_VALUE + 1;
            default:
                throw new IllegalArgumentException("Unsupported type: " + varType);
        }
    }

    private static Number getMaxValueOfType(String varType) {
        switch (varType) {
            case "int":
                return Integer.MAX_VALUE - 1;
            case "float":
                return Float.MAX_VALUE - 0.0001f;
            case "double":
                return Double.MAX_VALUE - 0.0001;
            case "short":
                return (short) (Short.MAX_VALUE - 1);
            case "long":
                return Long.MAX_VALUE - 1;
            default:
                throw new IllegalArgumentException("Unsupported type: " + varType);
        }
    }

    private static Number getMinMValueOfType(String varType) {
        switch (varType) {
            case "int":
                long templ = (long) Integer.MIN_VALUE;
                return templ - 1;
            case "float":
                double tempd = (double) -Float.MAX_VALUE;
                return tempd - 1;
            case "double":
                return -Double.MAX_VALUE - 1;
            case "short":
                return (int) (Short.MIN_VALUE) - 1;
            case "long":
                tempd = (double) Long.MIN_VALUE;
                return tempd - 1;
            default:
                throw new IllegalArgumentException("Unsupported type: " + varType);
        }
    }

    private static Number getMaxPValueOfType(String varType) {
        long templ;
        double tempd;
        switch (varType) {
            case "int":
                templ = (long) Integer.MAX_VALUE;
                return templ + 1;
            case "float":
                tempd = (double) Float.MAX_VALUE;
                return tempd + 0.0001;
            case "double":
                return Double.MAX_VALUE + 1;
            case "short":
                return (int) (Short.MAX_VALUE) + 1;
            case "long":
                tempd = (double) Long.MAX_VALUE;
                return tempd + 1;
            default:
                throw new IllegalArgumentException("Unsupported type: " + varType);
        }
    }

    private static Number getMinDValueOfType(String varType) {
        switch (varType) {
            case "float":
                return Float.MIN_VALUE * 0.99;
            case "double":
                return Double.MIN_VALUE;
            default:
                return 0;
        }
    }

    public static List<Number> generateBetweenValues(List<Number> a) {
        List<Number> b = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < a.size() - 1; i++) {
            Number x = a.get(i);
            Number y = a.get(i + 1);

            // Calculate the midpoint
            Number midValue = calculateMidValue(x, y);
            b.add(midValue);

            // Generate a random number between x and y
            Number randomValue = generateRandomValueBetween(x, y, random);
            b.add(randomValue);
        }

        return b;
    }

    private static Number calculateMidValue(Number x, Number y) {
        if (x instanceof Integer && y instanceof Integer) {
            if (x.intValue() % 2 == 1 && y.intValue() % 2 == 1) {
                return x.intValue() / 2 + y.intValue() / 2 + 1;
            } else {
                return x.intValue() / 2 + y.intValue() / 2;
            }
        } else if (x instanceof Float && y instanceof Float) {
            return x.floatValue() / 2 + y.floatValue() / 2;
        } else if (x instanceof Double && y instanceof Double) {
            return x.doubleValue() / 2 + y.doubleValue() / 2;
        } else if (x instanceof Long && y instanceof Long) {
            if (x.longValue() % 2 == 1 && y.longValue() % 2 == 1) {
                return x.longValue() / 2 + y.longValue() / 2 + 1;
            } else {
                return x.longValue() / 2 + y.longValue() / 2;
            }
        } else if (x instanceof Short && y instanceof Short) {
            if (x.shortValue() % 2 == 1 && y.shortValue() % 2 == 1) {
                return (short) (x.shortValue() / 2 + y.shortValue() / 2 + 1);
            } else {
                return (short) (x.shortValue() / 2 + y.shortValue() / 2);
            }
        } else {
            throw new IllegalArgumentException("Unsupported number type");
        }
    }

    private static Number generateRandomValueBetween(Number x, Number y, Random random) {
        if (x instanceof Integer && y instanceof Integer) {
            Integer middle = calculateMidValue(x, y).intValue();
            Integer newLowerBound = calculateMidValue(x, middle).intValue();
            Integer newUpperBound = calculateMidValue(middle, y).intValue();
            return newLowerBound + random.nextInt(newUpperBound - newLowerBound + 1);
        } else if (x instanceof Float && y instanceof Float) {
            Float middle = calculateMidValue(x, y).floatValue();
            Float newLowerBound = calculateMidValue(x, middle).floatValue();
            Float newUpperBound = calculateMidValue(middle, y).floatValue();
            return newLowerBound + random.nextFloat() * (newUpperBound - newLowerBound);
        } else if (x instanceof Double && y instanceof Double) {
            Double middle = calculateMidValue(x, y).doubleValue();
            Double newLowerBound = calculateMidValue(x, middle).doubleValue();
            Double newUpperBound = calculateMidValue(middle, y).doubleValue();
            return newLowerBound + random.nextDouble() * (newUpperBound - newLowerBound);
        } else if (x instanceof Long && y instanceof Long) {
            Long middle = calculateMidValue(x, y).longValue();
            Long newLowerBound = calculateMidValue(x, middle).longValue();
            Long newUpperBound = calculateMidValue(middle, y).longValue();
            return newLowerBound + (long) (random.nextDouble() * (newUpperBound - newLowerBound));
        } else if (x instanceof Short && y instanceof Short) {
            Short middle = calculateMidValue(x, y).shortValue();
            Short newLowerBound = calculateMidValue(x, middle).shortValue();
            Short newUpperBound = calculateMidValue(middle, y).shortValue();
            return (short) (newLowerBound + random.nextInt(newUpperBound - newLowerBound + 1));
        } else {
            throw new IllegalArgumentException("Unsupported number type");
        }
    }
}
