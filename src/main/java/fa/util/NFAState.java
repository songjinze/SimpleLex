package fa.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NFAState {

    private Node startNode;
    private Node currentNode;
    private Node endNode;
    private List<Node> nodeList;
    public NFAState(){
        startNode=new Node(new ArrayList<String>(),new ArrayList<Node>());
        currentNode=startNode;
        endNode=startNode;
        nodeList=new LinkedList<Node>();
        nodeList.add(startNode);
    }
    public Node getStartNode(){
        return startNode;
    }
    public Node getEndNode(){
        return endNode;
    }
    public Node getCurrentNode(){
        return currentNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }
    public void setCurrentNode(Node currentNode){
        this.currentNode=currentNode;
    }
    public List<Node> getNodeList(){
        return nodeList;
    }
    public void addNewNode(Node index,Node newNode,String parse){
        index.addNewLink(parse,newNode);
        nodeList.add(newNode);
    }

    /**
     * 将另个NFA连接到尾部
     */
    public void linkTwoNFA(NFAState nextNFA){
        Node newEndNode=nextNFA.getEndNode();
        endNode.addNewLink(null,nextNFA.getStartNode());
        endNode=newEndNode;
        nodeList.addAll(nextNFA.getNodeList());
    }
}
