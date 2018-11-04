package fa;

import fa.util.NFAState;
import fa.util.Node;

import java.util.ArrayList;

public class NFAParser {
    public NFAState getNFAByNormalRE(String normalRE){
        NFAState nfaState=new NFAState();
        int currentIndex=0;
        int length=normalRE.length();
        NFAState preState=nfaState;
        while(currentIndex<length){
            char c=normalRE.charAt(currentIndex);
            switch (c){
                case '*':
                    NFAState temp=new NFAState();
                    preState.getEndNode().addNewLink(null,preState.getStartNode());
                    temp.linkTwoNFA(preState);
                    Node newEndNode=new Node();
                    temp.addNewNode(temp.getEndNode(),newEndNode,null);
                    temp.setEndNode(newEndNode);
                    temp.getStartNode().addNewLink(null,newEndNode);
                    preState=temp;
                    break;
                case '|':
                    nfaState.linkTwoNFA(preState);
                    NFAState tempState1=nfaState;
                    NFAState tempState2=getNFAByNormalRE(normalRE.substring(currentIndex+1));
                    Node newTwoEndNode=new Node();
                    nfaState=new NFAState();
                    nfaState.linkTwoNFA(tempState1);
                    nfaState.getEndNode().addNewLink(null,newTwoEndNode);
                    nfaState.setEndNode(nfaState.getStartNode());
                    nfaState.linkTwoNFA(tempState2);
                    nfaState.addNewNode(nfaState.getEndNode(),newTwoEndNode,null);
                    nfaState.setEndNode(newTwoEndNode);
                    break;
                case '(':
                    nfaState.linkTwoNFA(preState);
                    int tempIndex=currentIndex+1;
                    while(normalRE.charAt(tempIndex)!=')'){
                        tempIndex++;
                    }
                    preState=getNFAByNormalRE(normalRE.substring(currentIndex+1,tempIndex));
                    currentIndex=tempIndex+1;
                    break;
                default:
                    nfaState.linkTwoNFA(preState);
                    preState=new NFAState();
                    Node newNode=new Node();
                    preState.addNewNode(preState.getStartNode(),newNode,c+"");
                    preState.setEndNode(newNode);
                    break;
            }
            nfaState.linkTwoNFA(preState);
        }
        return nfaState;
    }
}
