/**
 * Author : Siddhant Swarup Mallick
 * Github : https://github.com/siddhant2002
 * Program description - To find all possible paths from source to destinationWikipedia link -> https://en.wikipedia.org/wiki/Shortest_path_problem
 */

/** Program description - To find all possible paths from source to destination*/

/**Wikipedia link -> https://en.wikipedia.org/wiki/Shortest_path_problem */
package fileCloned;

import java.util.ArrayList;
import java.util.List;

public class AllPathsFromSourceToTarget {

    // No. of vertices in graph
    private final int v;

    // To store the paths from source to destination
    static List<List<Integer>> nm = new ArrayList<>();
    // adjacency list
    private ArrayList<Integer>[] adjList;

    // Constructor
    public AllPathsFromSourceToTarget(int vertices) {
        Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
        Mark.print("line-in-function$4#offset$94#statement$this.v=vertices;");
        this.v = vertices;
        Mark.print("line-in-function$7#offset$159#statement$initAdjList();");
        initAdjList();
        Mark.print("statement$}#line-of-block-in-function$1");
    }

    // utility method to initialise adjacency list
    private void initAdjList() {
        Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
        Mark.print("line-in-function$2#offset$37#statement$adjList=new ArrayList[v];");
        adjList = new ArrayList[v];
        Mark.print("line-in-function$4#offset$74#surrounding-control-block$for");
        Mark.print("line-in-function$4#offset$79#statement$int i=0");
        for (int i = 0; (Mark.print("line-in-function$4#offset$90#statement$i < v") && i < v); i++) {
            Mark.print("statement${#line-of-block-in-function$4");
            Mark.print("line-in-function$5#offset$116#statement$adjList[i]=new ArrayList<>();");
            adjList[i] = new ArrayList<>();
            Mark.print("statement$}#line-of-block-in-function$4");
            Mark.print("line-in-function$4#offset$97#statement$i++");
        }
        Mark.print("statement$}#line-of-block-in-function$1");
    }

    // add edge from u to v
    public void addEdge(int u, int v) {
        Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
        Mark.print("line-in-function$3#offset$74#statement$adjList[u].add(v);");
        adjList[u].add(v);
        Mark.print("statement$}#line-of-block-in-function$1");
    }

    public void storeAllPaths(int s, int d) {
        Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
        Mark.print("line-in-function$2#offset$50#statement$boolean[] isVisited=new boolean[v];");
        boolean[] isVisited = new boolean[v];
        Mark.print("line-in-function$3#offset$96#statement$ArrayList<Integer> pathList=new ArrayList<>();");
        ArrayList<Integer> pathList = new ArrayList<>();
        Mark.print("line-in-function$6#offset$186#statement$pathList.add(s);");
        pathList.add(s);
        Mark.print("line-in-function$8#offset$245#statement$storeAllPathsUtil(s,d,isVisited,pathList);");
        storeAllPathsUtil(s, d, isVisited, pathList);
        Mark.print("statement$}#line-of-block-in-function$1");
    }

    // A recursive function to print all paths from 'u' to 'd'.
    // isVisited[] keeps track of vertices in current path.
    // localPathList<> stores actual vertices in the current path
    private void storeAllPathsUtil(Integer u, Integer d, boolean[] isVisited, List<Integer> localPathList) {
        Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
        Mark.print("line-in-function$3#offset$114#surrounding-control-block$if");
        if ((Mark.print("line-in-function$3#offset$118#statement$u.equals(d)") && u.equals(d))) {
            Mark.print("statement${#line-of-block-in-function$3");
            Mark.print("line-in-function$4#offset$145#statement$nm.add(new ArrayList<>(localPathList));");
            nm.add(new ArrayList<>(localPathList));
            Mark.print("line-in-function$5#offset$197#statement$return;");
            Mark.print("statement$}#line-of-block-in-function$3");
            Mark.print("statement$}#line-of-block-in-function$1");
            return;
        }
        Mark.print("line-in-function$9#offset$257#statement$isVisited[u]=true;");
        isVisited[u] = true;
        Mark.print("line-in-function$13#offset$357#surrounding-control-block$forEach");
        Mark.print("line-in-function$13#offset$362#statement$Integer i : adjList[u]");
        for (Integer i : adjList[u]) {
            Mark.print("statement${#line-of-block-in-function$13");
            Mark.print("line-in-function$14#offset$400#surrounding-control-block$if");
            if ((Mark.print("line-in-function$14#offset$404#statement$!isVisited[i]") && !isVisited[i])) {
                Mark.print("statement${#line-of-block-in-function$14");
                Mark.print("line-in-function$16#offset$485#statement$localPathList.add(i);");
                localPathList.add(i);
                Mark.print("line-in-function$17#offset$523#statement$storeAllPathsUtil(i,d,isVisited,localPathList);");
                storeAllPathsUtil(i, d, isVisited, localPathList);
                Mark.print("line-in-function$20#offset$640#statement$localPathList.remove(i);");
                localPathList.remove(i);
                Mark.print("statement$}#line-of-block-in-function$14");
            }
            Mark.print("statement$}#line-of-block-in-function$13");
            Mark.print("line-in-function$13#offset$362#statement$Integer i : adjList[u]");
        }
        Mark.print("line-in-function$25#offset$731#statement$isVisited[u]=false;");
        isVisited[u] = false;
        Mark.print("statement$}#line-of-block-in-function$1");
    }

    // Driver program
    public static List<List<Integer>> allPathsFromSourceToTarget(int vertices, int[][] a, int source, int destination) {
        Mark.print("statement${#line-of-block-in-function$1#opening-function$true");
        Mark.print("line-in-function$3#offset$158#statement$AllPathsFromSourceToTarget g=new AllPathsFromSourceToTarget(vertices);");
        AllPathsFromSourceToTarget g = new AllPathsFromSourceToTarget(vertices);
        Mark.print("line-in-function$4#offset$239#surrounding-control-block$forEach");
        Mark.print("line-in-function$4#offset$244#statement$int[] i : a");
        for (int[] i : a) {
            Mark.print("statement${#line-of-block-in-function$4");
            Mark.print("line-in-function$5#offset$271#statement$g.addEdge(i[0],i[1]);");
            g.addEdge(i[0], i[1]);
            Mark.print("statement$}#line-of-block-in-function$4");
            Mark.print("line-in-function$4#offset$244#statement$int[] i : a");
        }
        Mark.print("line-in-function$8#offset$343#statement$g.storeAllPaths(source,destination);");
        g.storeAllPaths(source, destination);
        Mark.print("line-in-function$10#offset$440#statement$return nm;");
        Mark.print("statement$}#line-of-block-in-function$1");
        return nm;
    }
}
