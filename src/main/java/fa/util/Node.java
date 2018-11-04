package fa.util;

import java.util.ArrayList;
import java.util.List;

public class Node{
    private List<String> nextStrings;
    private List<Node> nextStates;

    public Node(List<String> nextStrings,List<Node> nextStates){
        this.nextStates=nextStates;
        this.nextStrings=nextStrings;
    }

    public Node(){
        nextStates=new ArrayList<Node>();
        nextStrings=new ArrayList<String>();
    }


    public List<String> getNextStrings() {
        return nextStrings;
    }

    public List<Node> getNextStates() {
        return nextStates;
    }

    public void addNewLink(String newLinkStr,Node node){
        nextStrings.add(newLinkStr);
        nextStates.add(node);
    }
}