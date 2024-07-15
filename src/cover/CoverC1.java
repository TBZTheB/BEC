package cover;

import mark.Method;

import java.util.List;

public class CoverC1 extends Cover{
    public CoverC1(List<String> pathMarks, String filePath, Method method){
        super(pathMarks,filePath, method);
    }
    public String getCover() {
        int numberNodePass = 0;
        for(String mark : marks){
            if(!lineInFunctions.contains(findLineInFunction(mark)) && !offsets.contains(findOffset(mark))) {
                if(markIsNode(mark)){
                    lineInFunctions.add(findLineInFunction(mark));
                    offsets.add(findOffset(mark));
                    numberNodePass++;
                    System.out.println(mark);
                }
            }
        }
        System.out.println("\n\n tat ca cac not \n" + getCoverAll());
        return numberNodePass + "/" + nodes.size();
    }

//    public String getNodeNoPassed(){
//
//    }
}
