package mark;

import java.util.List;

public class  Mark {
    private List<Method> methods;
    public Mark(String filePath){
        methods = ReadMethod.getMethodList(filePath);
    }
    public void addMarkToClass(String coverType){
        for(Method method : methods){
            String newBody = addMarkToMethod(method, coverType);
//            System.out.println(newBody);
            method.setNewBody(newBody);
        }
    }

    private String addMarkToMethod(Method method, String coverType){
        BlockMark blockMark = new BlockMark(method.getBody(), method, true, coverType);
        return blockMark.addMark();
    }


    public List<Method> getMethods() {
        return methods;
    }

}
