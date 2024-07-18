import org.eclipse.jdt.core.dom.*;
import java.util.ArrayList;
import java.util.List;

public class EquivalencePartitioningTestCaseGenerator {

    public static void main(String[] args) {
        String source =
                "public class ScoreCalculator {" +
                        "    public int calculateScore(int value) {" +
                        "        if (value < 0 || value > 100) {" +
                        "            throw new IllegalArgumentException(\"Value must be between 0 and 100\");" +
                        "        }" +
                        "        return value * 2;" +
                        "    }" +
                        "    public double calculateDoubleScore(double value) {" +
                        "        if (value < 0.0 || value > 100.0) {" +
                        "            throw new IllegalArgumentException(\"Value must be between 0.0 and 100.0\");" +
                        "        }" +
                        "        return value * 2;" +
                        "    }" +
                        "    public String echo(String value) {" +
                        "        return value;" +
                        "    }" +
                        "}";

        generateEquivalencePartitioningTests(source);
    }

    public static void generateEquivalencePartitioningTests(String source) {
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setSource(source.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        cu.accept(new ASTVisitor() {
            @Override
            public boolean visit(MethodDeclaration node) {
                generateTestCases(node);
                return super.visit(node);
            }
        });
    }

    private static void generateTestCases(MethodDeclaration method) {
        List<?> parameters = method.parameters();
        for (Object parameter : parameters) {
            SingleVariableDeclaration svd = (SingleVariableDeclaration) parameter;
            String type = svd.getType().toString();

            // Phân tích các điều kiện trong thân phương thức để lấy các vùng tương đương
            List<Object> equivalenceClasses = extractEquivalenceClasses(method.getBody(), svd.getName().getIdentifier(), type);

            switch (type) {
                case "int":
                    generateIntEquivalenceTestCases(method, svd, equivalenceClasses);
                    break;
                case "double":
                    generateDoubleEquivalenceTestCases(method, svd, equivalenceClasses);
                    break;
                case "String":
                    generateStringEquivalenceTestCases(method, svd, equivalenceClasses);
                    break;
                // Add other types as needed
                default:
                    System.out.println("Unsupported type: " + type);
            }
        }
    }

    private static List<Object> extractEquivalenceClasses(Block body, String variableName, String type) {
        List<Object> equivalenceClasses = new ArrayList<>();
        body.accept(new ASTVisitor() {
            @Override
            public boolean visit(InfixExpression node) {
                if (node.getLeftOperand() instanceof SimpleName &&
                        ((SimpleName) node.getLeftOperand()).getIdentifier().equals(variableName)) {
                    if (node.getRightOperand() instanceof NumberLiteral) {
                        String operator = node.getOperator().toString();
                        addEquivalenceClass(equivalenceClasses, operator, ((NumberLiteral) node.getRightOperand()).getToken(), type);
                    }
                } else if (node.getRightOperand() instanceof SimpleName &&
                        ((SimpleName) node.getRightOperand()).getIdentifier().equals(variableName)) {
                    if (node.getLeftOperand() instanceof NumberLiteral) {
                        String operator = node.getOperator().toString();
                        addEquivalenceClass(equivalenceClasses, operator, ((NumberLiteral) node.getLeftOperand()).getToken(), type);
                    }
                }
                return super.visit(node);
            }
        });
        return equivalenceClasses;
    }

    private static void addEquivalenceClass(List<Object> equivalenceClasses, String operator, String token, String type) {
        switch (type) {
            case "int":
                int intValue = Integer.parseInt(token);
                switch (operator) {
                    case "<":
                        equivalenceClasses.add(new int[]{Integer.MIN_VALUE, intValue - 1});
                        break;
                    case "<=":
                        equivalenceClasses.add(new int[]{Integer.MIN_VALUE, intValue});
                        break;
                    case ">":
                        equivalenceClasses.add(new int[]{intValue + 1, Integer.MAX_VALUE});
                        break;
                    case ">=":
                        equivalenceClasses.add(new int[]{intValue, Integer.MAX_VALUE});
                        break;
                    case "==":
                        equivalenceClasses.add(new int[]{intValue});
                        break;
                }
                break;
            case "double":
                if (token.equals("Double.NEGATIVE_INFINITY")) {
                    equivalenceClasses.add(Double.NEGATIVE_INFINITY);
                } else if (token.equals("Double.POSITIVE_INFINITY")) {
                    equivalenceClasses.add(Double.POSITIVE_INFINITY);
                } else {
                    double doubleValue = Double.parseDouble(token);
                    switch (operator) {
                        case "<":
                            equivalenceClasses.add(new double[]{Double.NEGATIVE_INFINITY, doubleValue - 0.1});
                            break;
                        case "<=":
                            equivalenceClasses.add(new double[]{Double.NEGATIVE_INFINITY, doubleValue});
                            break;
                        case ">":
                            equivalenceClasses.add(new double[]{doubleValue + 0.1, Double.POSITIVE_INFINITY});
                            break;
                        case ">=":
                            equivalenceClasses.add(new double[]{doubleValue, Double.POSITIVE_INFINITY});
                            break;
                        case "==":
                            equivalenceClasses.add(new double[]{doubleValue});
                            break;
                    }
                }
                break;
        }
    }

    private static void generateIntEquivalenceTestCases(MethodDeclaration method, SingleVariableDeclaration svd, List<Object> equivalenceClasses) {
        AST ast = method.getAST();
        for (Object ec : equivalenceClasses) {
            int[] values = (int[]) ec;
            for (int val : values) {
                MethodInvocation testMethodInvocation = ast.newMethodInvocation();
                testMethodInvocation.setName(ast.newSimpleName(method.getName().toString()));
                testMethodInvocation.arguments().add(ast.newNumberLiteral(String.valueOf(val)));

                ExpressionStatement statement = ast.newExpressionStatement(testMethodInvocation);
                MethodDeclaration testMethod = createTestMethod(ast, method.getName().toString(), String.valueOf(val));
                testMethod.getBody().statements().add(statement);

                // Print the test method (You can also save it to a file)
                System.out.println(testMethod);
            }
        }
    }

    private static void generateDoubleEquivalenceTestCases(MethodDeclaration method, SingleVariableDeclaration svd, List<Object> equivalenceClasses) {
        AST ast = method.getAST();
        for (Object ec : equivalenceClasses) {
            if (ec instanceof Double) {
                double val = (Double) ec;
                MethodInvocation testMethodInvocation = ast.newMethodInvocation();
                testMethodInvocation.setName(ast.newSimpleName(method.getName().toString()));
                if (val == Double.NEGATIVE_INFINITY) {
                    testMethodInvocation.arguments().add(ast.newSimpleName("Double.NEGATIVE_INFINITY"));
                } else if (val == Double.POSITIVE_INFINITY) {
                    testMethodInvocation.arguments().add(ast.newSimpleName("Double.POSITIVE_INFINITY"));
                } else {
                    testMethodInvocation.arguments().add(ast.newNumberLiteral(String.valueOf(val)));
                }

                ExpressionStatement statement = ast.newExpressionStatement(testMethodInvocation);
                MethodDeclaration testMethod = createTestMethod(ast, method.getName().toString(), String.valueOf(val).replace('.', '_'));
                testMethod.getBody().statements().add(statement);

                // Print the test method (You can also save it to a file)
                System.out.println(testMethod);
            } else if (ec instanceof double[]) {
                double[] values = (double[]) ec;
                for (double val : values) {
                    MethodInvocation testMethodInvocation = ast.newMethodInvocation();
                    testMethodInvocation.setName(ast.newSimpleName(method.getName().toString()));
                    if (val == Double.NEGATIVE_INFINITY) {
                        testMethodInvocation.arguments().add(ast.newSimpleName("Double.NEGATIVE_INFINITY"));
                    } else if (val == Double.POSITIVE_INFINITY) {
                        testMethodInvocation.arguments().add(ast.newSimpleName("Double.POSITIVE_INFINITY"));
                    } else {
                        testMethodInvocation.arguments().add(ast.newNumberLiteral(String.valueOf(val)));
                    }

                    ExpressionStatement statement = ast.newExpressionStatement(testMethodInvocation);
                    MethodDeclaration testMethod = createTestMethod(ast, method.getName().toString(), String.valueOf(val).replace('.', '_'));
                    testMethod.getBody().statements().add(statement);

                    // Print the test method (You can also save it to a file)
                    System.out.println(testMethod);
                }
            }
        }
    }

    private static void generateStringEquivalenceTestCases(MethodDeclaration method, SingleVariableDeclaration svd, List<Object> equivalenceClasses) {
        // For strings, we'll just create test cases for non-empty and empty strings
        AST ast = method.getAST();
        String[] values = {"", "a", "boundaryTestString"};
        for (String value : values) {
            MethodInvocation testMethodInvocation = ast.newMethodInvocation();
            testMethodInvocation.setName(ast.newSimpleName(method.getName().toString()));
            StringLiteral literal = ast.newStringLiteral();
            literal.setLiteralValue(value);
            testMethodInvocation.arguments().add(literal);

            ExpressionStatement statement = ast.newExpressionStatement(testMethodInvocation);
            MethodDeclaration testMethod = createTestMethod(ast, method.getName().toString(), value);
            testMethod.getBody().statements().add(statement);

            // Print the test method (You can also save it to a file)
            System.out.println(testMethod);
        }
    }

    private static MethodDeclaration createTestMethod(AST ast, String methodName, String value) {
        MethodDeclaration testMethod = ast.newMethodDeclaration();
        testMethod.setName(ast.newSimpleName("test_" + methodName));
        testMethod.setBody(ast.newBlock());
        return testMethod;
    }
}

