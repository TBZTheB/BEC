import cfg.ICFG;
import cfg.generation.CFGGeneration;
import cfg.generation.CFGGenerationSubCondition;
import cfg.generation.testpath.Check.FullTestpaths;
import cfg.generation.testpath.TestpathGeneration;
import cfg.nodes.ConditionCfgNode;
import cfg.nodes.ICfgNode;
import mark.Utils;
import org.eclipse.jdt.core.dom.*;
import symbolicExecution.SymbolicExecutionTestpath;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ThucThiTuongTrungC1 extends Object implements IJdtParser  {
	public static void main(String[] args) throws IOException{
//		long startTime = System.currentTimeMillis();

		// Lấy bộ nhớ sử dụng ban đầu
		Runtime runtime = Runtime.getRuntime();
		runtime.gc(); // Gọi Garbage Collector để giải phóng bộ nhớ không sử dụng
		double startMemory = runtime.totalMemory() - runtime.freeMemory();

		String filePath = "JdtBase/src/Test.java";
		new ThucThiTuongTrungC1(filePath);

		new ASTAnalyzer(filePath);


		// Kết thúc thời gian
//		long endTime = System.currentTimeMillis();

		// Lấy bộ nhớ sử dụng sau khi chạy chương trình
		double endMemory = runtime.totalMemory() - runtime.freeMemory();

		// Tính toán thời gian chạy và bộ nhớ sử dụng
//		long timeElapsed = endTime - startTime;
		double memoryUsed = (endMemory - startMemory) / 1048576;
//		System.out.println("Thời gian chạy: " + timeElapsed  + " milliseconds");
		System.out.println("Bộ nhớ sử dụng: " + memoryUsed  + " bytes");
		try {
			// Tạo đối tượng FileWriter với tên tệp tin
			FileWriter fileWriter = new FileWriter("JdtBase/src/dungluong.txt", true);

			// Sử dụng BufferedWriter để ghi dữ liệu
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// Ghi dữ liệu vào tệp tin
			bufferedWriter.write( String.valueOf(memoryUsed) );
			bufferedWriter.newLine();

			// Đóng BufferedWriter để hoàn tất việc ghi
			bufferedWriter.close();

			System.out.println("Dữ liệu đã được ghi vào tệp tin thành công.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ThucThiTuongTrungC1(String filePath) {
		try {
			String fileContent = Utils.readFileContent(filePath);
			parse(fileContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static class Variable {
		private String type;
		private String name;

		public Variable(String type, String name) {
			this.type = type;
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public String getName() {
			return name;
		}

		public void setType(String type) {
			this.type = type;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Type: " + type + ", Name: " + name;
		}
	}

	public int doUuTien(String c) {
		if (c.equals("+") || c.equals("-")) return 1;
		if (c.equals("*") || c.equals("/") || c.equals("%")) return 2;
		return 0;
	}

	public boolean laToanTu(String c) {
		return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") || c.equals("%") || c.equals("!") || c.equals(">") || c.equals("<") || c.equals("==") || c.equals("<=") || c.equals(">=") || c.equals("!=");

	}

	public boolean laToanTu(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '!' || c == '>' || c == '<' || c == '=' || c == '<' || c == '>' || c == '!';
	}

	public boolean laToanTuSoSanh(String c) {
		return c.equals(">") || c.equals("<") || c.equals("==") || c.equals("<=") || c.equals(">=") || c.equals("!=");
	}

	public boolean laToanTuSoSanh(char c) {
		return c == '>' || c == '<' || c == '=' || c == '<' || c == '>' || c == '!';
	}



	public Stack<String> chuyenVeHauTo(List<String> s) {
		Stack<String> stack = new Stack<>();
		Stack<String> result = new Stack<>();
		for (int i = 0; i < s.size(); i++) {
			String c = s.get(i);
			if (c.equals(" ")) continue;
			if (c.equals("(")) {
				stack.push(c);
			} else if (c.equals(")")) {
				while (!stack.isEmpty() && stack.peek() != "(") {
					result.push(stack.pop());
				}
				stack.pop();
			}
			else if (laToanTuSoSanh(c)) {
				while (!stack.isEmpty() && laToanTu(stack.peek())) {
					result.push(stack.pop());
				}
				stack.push(c);
			}
			else if (laToanTu(c)) {
				while (!stack.isEmpty() && laToanTu(stack.peek()) && doUuTien(c) <= doUuTien(stack.peek())) {
					result.push(stack.pop());
				}
				stack.push(c);
			}
			else {
				result.push(c);
			}
			//Print stack;
			for (int j = 0; j < stack.size(); j++) {
//				System.out.print(stack.get(j));
			}
//			System.out.print(" | ");
			for (int j = 0; j < result.size(); j++) {
//				System.out.print(result.get(j));
			}
//			System.out.println();
		}
		while (!stack.isEmpty()) {
			result.push(stack.pop());
		}
		return result;
	}

	public Nodes chuyenVeCayBieuThuc(Stack<String> s) {
		Stack<Nodes> stack = new Stack<>();
		for (int i = 0; i < s.size(); i++) {
			String c = s.get(i);
			Nodes node = new Nodes(c);
			if (!laToanTu(c)) {
				stack.push(node);
			} else {
				node.setRight(stack.pop());
				node.setLeft(stack.pop());
				stack.push(node);
			}
//			System.out.println(stack.peek().toString() + " ----- " + stack.size());

		}
		return stack.pop();
	}

	public String chuyenVeTienTo(Nodes root) {
		String Retstr = "", left = "", right = "";
		String c = root.getData();
		if (laToanTu(c)) {
			left = chuyenVeTienTo(root.getLeft());
			right = chuyenVeTienTo(root.getRight());
		}
		if (c.equals("!=")) {
			Retstr = "(or ( > " + left + " " + right + " ) ( < " + left + " " + right + " )) ";
		}
		else if (laToanTu(c)) {
			Retstr = "( " + c + " " + left + " " + right + " )";
		}
		else {
			Retstr = c + "";
		}
		Retstr = Retstr.replace("==", "=");
//		System.out.println(Retstr);
		return Retstr;
	}

	public void writeToFile(String data, File file) {
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(data);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Variable> parseResult(String result) {
		List<Variable> variables = new ArrayList<>();
		boolean inCurrentVariable = false;

		// Parse result
		for (String line : result.split("\n")) {
			if (line.contains("define-fun")) {
				// Get variable name and type
				// Example: (define-fun A () Int
				String[] parts = line.strip().split(" ");
				String name = parts[1].strip();
				String type = parts[3].strip();
				inCurrentVariable = true;
				Variable variable = new Variable(name, type);
				variables.add(variable);
			}
			else if (inCurrentVariable) {
				// Get variable value
				// Example: 3)
				String value = line.substring(0, line.length() - 1).strip();
				inCurrentVariable = false;
			}
		}
		return variables;
	}

	public List<String> chuyenBieuThucThanhInfix(String s) {
		//Example: s = "A*3>6*((D-4)+5)/2";
		List<String> stack = new ArrayList<>();
		Boolean isSoSanh = false;
		String temp = "";
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ' ') continue;
			if (c == '(') {
				if (temp != "") stack.add(temp);
				stack.add("(");
				temp = "";
			} else if (c == ')') {
				if (temp != "") stack.add(temp);
				stack.add(")");
				temp = "";
			} else if (laToanTu(c)) {
				if (laToanTuSoSanh(c)) {
					if (!isSoSanh) {
						if (temp != "") stack.add(temp);
						temp = c + "";
						isSoSanh = true;
					} else {
						temp += c;
						stack.add(temp);
						isSoSanh = false;
						temp = "";
					}
				}
				else {
					if (temp != "") stack.add(temp);
					stack.add(c + "");
					temp = "";
				}
			} else {
				if (isSoSanh) {
					stack.add(temp);
					temp = c + "";
					isSoSanh = false;
				} else {
					temp += c;
				}
			}
		}
		if (temp != "") stack.add(temp);
		return stack;
	}

	public String giaiDieuKienRangBuoc(String s) {
		List<String> infix = chuyenBieuThucThanhInfix(s);
		Stack<String> stack = chuyenVeHauTo(infix);
		Nodes root = chuyenVeCayBieuThuc(stack);
		String tienTo = chuyenVeTienTo(root);
		String SMTCall = "(assert " + tienTo + ")";
		return SMTCall;
	}

	public InfixExpression.Operator changeOperator(InfixExpression.Operator operator) {
		if (operator.equals(InfixExpression.Operator.LESS)) {
			return InfixExpression.Operator.GREATER_EQUALS;
		}
		if (operator.equals(InfixExpression.Operator.LESS_EQUALS)) {
			return InfixExpression.Operator.GREATER;
		}
		if (operator.equals(InfixExpression.Operator.GREATER)) {
			return InfixExpression.Operator.LESS_EQUALS;
		}
		if (operator.equals(InfixExpression.Operator.GREATER_EQUALS)) {
			return InfixExpression.Operator.LESS;
		}
		if (operator.equals(InfixExpression.Operator.EQUALS)) {
			return InfixExpression.Operator.NOT_EQUALS;
		}
		if (operator.equals(InfixExpression.Operator.NOT_EQUALS)) {
			return InfixExpression.Operator.EQUALS;
		}
		return operator;
	}

	public String convert(String type) {
		if (type.equals("int")) {
			return "Int";
		}
		if (type.equals("boolean")) {
			return "Bool";
		}
		if (type.equals("double")) {
			return "Real";
		}
		return type;
	}

	public String createSMTStart(List parameters) {
		String SMTStart = "";
		for (int i = 0; i < parameters.size(); i++) {
			SingleVariableDeclaration parameter = (SingleVariableDeclaration) parameters.get(i);
			String type = parameter.getType().toString();
			String name = parameter.getName().toString();
			SMTStart += "(declare-fun " + name + " () " + convert(type) + ")\n";
		}
		return SMTStart;
	}

	@Override
	public void parse(String fileContent) {
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setSource(fileContent.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		int notAvailable = 0;

		List<NodeCondition> nodeConditionList = new ArrayList<>();


		cu.accept(new ASTVisitor() {

			// Thăm khai báo một phương thức

			public boolean visit(MethodDeclaration node) {


	 			// Lay body method
				CFGGeneration cfgGenerration = new CFGGeneration(node);
				// Lay tham so truyen vao
				String currentPath = Paths.get("").toAbsolutePath().toString();
				List parameters = node.parameters();
				String SMTStart = createSMTStart(parameters);
				String SMTEnd = "(check-sat)\n(get-model)\n(exit)";
				ICFG cfg = cfgGenerration.generateCFG();
				CFGGenerationSubCondition cfgGenerrationforsubcondition = new CFGGenerationSubCondition(node,1);
				ICFG cfgsubcondition = cfgGenerrationforsubcondition.generateCFG();

				List<NodeC1> allNodesC1 = new ArrayList<>();
				List<ICfgNode> allNodesC3 = cfgsubcondition.getAllNodes();

				for (ICfgNode cfgNodeC3 : allNodesC3){
					if(cfgNodeC3.isCondition()){
						for(ICfgNode cfgNodeC1 : cfg.getAllNodes()){
							if(cfgNodeC1.isCondition() && cfgNodeC1.getContent().contains(cfgNodeC3.getContent())){
								boolean check = false;
								for(NodeC1 nodeC1 : allNodesC1){
									if(nodeC1.getNode() == cfgNodeC1){
										nodeC1.getSubconditionsNode().add(cfgNodeC3);
										check = true;
									}
								}
								if(!check){
									NodeC1 nodeC1 = new NodeC1(cfgNodeC1);
									nodeC1.getSubconditionsNode().add(cfgNodeC3);
									allNodesC1.add(nodeC1);
								}
							}
						}
					}else {
						allNodesC1.add(new NodeC1(cfgNodeC3));
					}
				}

				TestpathGeneration testpathGen = new TestpathGeneration(cfgsubcondition);
				testpathGen.generateTestpaths();

				FullTestpaths testpaths = testpathGen.getPossibleTestpaths();


				for (int i = testpaths.size() - 1; i >= 0; i--){
//				for (int i = 0; i < testpaths.size(); i++){
					if(!checkCoveredC1Testpath(allNodesC1, testpaths.getTestpathAt(i).getAllCfgNodes())){
						SymbolicExecutionTestpath sym = new SymbolicExecutionTestpath(testpaths.getTestpathAt(i).getAllCfgNodes(), parameters);
						List<Expression> conditions = new ArrayList<>();
						List<String> SMTCalls = new ArrayList<>();
						conditions = sym.symbolicExecution();
						if (conditions.size() != 0) {
							for (int j = 0; j < conditions.size(); j++) {
								//Get the condition of the testpath
								Expression condition = conditions.get(j);
//							System.out.println("Condition: " + condition.getClass());
								if (condition instanceof Assignment) {
									//Get the left operand of the condition
									Expression e1 = ((Assignment) condition).getLeftHandSide();
//								((InfixExpression) e1).setOperator(changeOperator(((InfixExpression) e1).getOperator()));
									//Get the right operand of the condition
									Expression e2 = ((Assignment) condition).getRightHandSide();

									if (e1 instanceof InfixExpression) {
										if (e2 instanceof BooleanLiteral) {
											if (((BooleanLiteral) e2).booleanValue() == false) {
												((InfixExpression) e1).setOperator(changeOperator(((InfixExpression) e1).getOperator()));
												((BooleanLiteral) e2).setBooleanValue(true);
											}
										}
									}
//								System.out.println("Left operand: " + ((Assignment) condition).getLeftHandSide().toString());
//								System.out.println("Right operand: " + e2 + "\n");
									String SMTCall = giaiDieuKienRangBuoc(e1.toString());
									SMTCalls.add(SMTCall);
								}
								else {
								}
							}
							String SMT = SMTStart + String.join("\n", SMTCalls) + "\n" + SMTEnd;
							File file = new File(currentPath + "\\JdtBase\\src\\SMT\\SMT" + i + ".smt2");
							try {
								file.createNewFile();
							} catch (IOException e) {
								e.printStackTrace();
							}
							writeToFile(SMT, file);
							Z3Solving z3 = new Z3Solving();
							String result = "";
							try {
								result = z3.solve(currentPath + "\\JdtBase\\src\\SMT\\SMT" + i + ".smt2");
							} catch (IOException e) {
								e.printStackTrace();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							if(!result.contains("error")){
								System.out.println(result);
								updateCoveredC1Testpath(allNodesC1, testpaths.getTestpathAt(i).getAllCfgNodes());
							}
						}

					}
				}
				System.out.println("Độ phủ của bộ test: " + getCovered(allNodesC1));


				int startPosition = node.getStartPosition();
				int endPosition = startPosition + node.getLength();
				String methodString = fileContent.substring(startPosition,endPosition);
				Block body = node.getBody();

				// Lấy cha của node khai báo trong cây AST
				ASTNode parent = node.getParent();

				return true;
			}
		});
	}

	public double getCovered(List<NodeC1> allNodesC1){
		double numberBranchCovered = 0;
		for(NodeC1 nodeC1 : allNodesC1){
			if(nodeC1.covered){
				numberBranchCovered += 1;
			}
		}
		return numberBranchCovered * 100 / (allNodesC1.size());
	}

	public boolean checkCoveredC1Testpath(List<NodeC1> allNodeC1, List<ICfgNode> testpath){
        for (ICfgNode node : testpath) {
            boolean check = false;
            for (NodeC1 nodeC1 : allNodeC1) {
                if (node.isCondition() && nodeC1.getNode().isCondition()) {
                    for (ICfgNode subconditionNode : nodeC1.getSubconditionsNode()) {
                        if (subconditionNode.getId() == node.getId() && nodeC1.isCovered()) {
                            check = true;
                            break;
                        }
                    }
                } else if (!node.isCondition() && !nodeC1.getNode().isCondition()) {
                    if (node.getId() == nodeC1.getNode().getId() && nodeC1.isCovered()) {
                        check = true;
                        break;
                    }
                }
            }
            if (!check) {
                return false;
            }
        }
		return true;
	}

	public void updateCoveredC1Testpath(List<NodeC1> allNodeC1, List<ICfgNode> testpath){
        for (ICfgNode node : testpath) {
            for (NodeC1 nodeC1 : allNodeC1) {
                if (node.isCondition() && nodeC1.getNode().isCondition()) {
                    for (ICfgNode subconditionNode : nodeC1.getSubconditionsNode()) {
                        if (subconditionNode.getId() == node.getId()) {
                            nodeC1.setCovered(true);
                            break;
                        }
                    }
                } else if (!node.isCondition() && !nodeC1.getNode().isCondition()) {
                    if (node.getId() == nodeC1.getNode().getId()) {
                        nodeC1.setCovered(true);
                        break;
                    }
                }
            }

        }
	}

	public boolean checkCoveredC3Testpath(List<NodeCondition> nodeConditionList, List<ICfgNode> testpath){
		for(int i = 0 ; i < testpath.size(); i++){
			if (testpath.get(i).isCondition()){
				for(NodeCondition nodeCondition: nodeConditionList){
					if (nodeCondition.getNode().getId() == testpath.get(i).getId()){
						if(testpath.get(i).getTrueNode() == testpath.get(i + 1)){
							if(!nodeCondition.isTrueNode()){
								return false;
							}
						} else {
							if(!nodeCondition.isFalseNode()){
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}

	public void updateCoveredC3Testpath(List<NodeCondition> nodeConditionList, List<ICfgNode> testpath){
		for(int i = 0 ; i < testpath.size(); i++){
			if (testpath.get(i).isCondition()){
				for(NodeCondition nodeCondition: nodeConditionList){
					if (nodeCondition.getNode().getId() == testpath.get(i).getId()){
						if(testpath.get(i).getTrueNode() == testpath.get(i + 1)){
							nodeCondition.setTrueNode(true);
						} else {
							nodeCondition.setFalseNode(true);
						}
					}
				}
			}
		}
	}

	public void updateCoveredC2Testpath(List<NodeCondition> nodeConditionList, List<ICfgNode> testpath){
		for(int i = 0 ; i < testpath.size(); i++){
			if (testpath.get(i).isCondition()){
				for(NodeCondition nodeCondition: nodeConditionList){
					List<ICfgNode> subConditions = nodeCondition.getSubConditions();
					for(int j = 0; j < subConditions.size(); j++ ){
						if (subConditions.get(j).getId() == testpath.get(i).getId()){
							if(testpath.get(i).getTrueNode() == testpath.get(i + 1)){
								if(!checkSubconditionInConditionC2(subConditions,testpath.get(i).getTrueNode())){
									nodeCondition.setTrueNode(true);
								}
							} else {
								if(!checkSubconditionInConditionC2(subConditions,testpath.get(i).getFalseNode())){
									nodeCondition.setFalseNode(true);
								}
							}
							break;
						}
					}
				}
			}
		}
	}

	public boolean checkCoveredC2Testpath(List<NodeCondition> nodeConditionList, List<ICfgNode> testpath){
		for(int i = 0 ; i < testpath.size(); i++){
			if (testpath.get(i).isCondition()){
				for(NodeCondition nodeCondition: nodeConditionList){
					boolean check = false;
					List<ICfgNode> subConditions = nodeCondition.getSubConditions();
					for(int j = 0; j < subConditions.size(); j++ ){
						if (subConditions.get(j).getId() == testpath.get(i).getId()){
							if(testpath.get(i).getTrueNode() == testpath.get(i + 1)){
								if(!nodeCondition.isTrueNode() && !checkSubconditionInConditionC2(subConditions,testpath.get(i).getTrueNode())){
									return false;
								}
							} else {
								if(!nodeCondition.isFalseNode() && !checkSubconditionInConditionC2(subConditions,testpath.get(i).getTrueNode())){
									return false;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}

	public boolean checkSubconditionInConditionC2(List<ICfgNode> subConditions, ICfgNode node){
		for (ICfgNode iCfgNode : subConditions){
			if(node == iCfgNode){
				return true;
			}
		}
		return false;
	}


	public void rewriteStatement(ICfgNode node, ArrayList M, ArrayList S){
		if (node instanceof ConditionCfgNode){
			Expression expression = ((ConditionCfgNode) node).getAstCondition();
			if(expression instanceof InfixExpression){
				Expression e1 = ((InfixExpression) expression).getLeftOperand();
				Expression e2 = ((InfixExpression) expression).getRightOperand();
				InfixExpression.Operator op = ((InfixExpression) expression).getOperator();

			}
		}
	}



}
