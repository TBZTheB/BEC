import mark.Utils;
import org.eclipse.jdt.core.dom.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Example
 * 
 * @author DucAnh
 *
 */
public class Example extends Object implements IJdtParser {
	private List<MethodDeclaration> methodDeclarations_ = new ArrayList<MethodDeclaration>();

	public Example(String filePath) {
		try {
			String fileContent = Utils.readFileContent(filePath);
			parse(fileContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void parse(String fileContent) {
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setSource(fileContent.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);

		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

		cu.accept(new ASTVisitor() {

			// visit a method
			public boolean visit(MethodDeclaration node) {
				methodDeclarations_.add(node);
				return true;
			}
		});
	}

	public void p(Object o) {
		if (o != null)
			System.out.println("----\n" + (String) o.toString() + "\n------\n");
	}

	class nestClass {
		int x;

		void testNesyClass() {

		}
	}
}

class outerClass1 {
	int y;

	void testOuterClass1() {

	}
}

class outerClass2 {
	int x, y;

	void testOuterClass2() {

	}
}
