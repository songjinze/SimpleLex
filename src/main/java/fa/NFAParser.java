package fa;

import fa.util.NFA;
import fa.util.NFAState;

public class NFAParser {
    public NFA getNFAByNormalRE(String normalRE){
        NFA nfa =new NFA();
        int currentIndex=0;
        int length=normalRE.length();
        NFA preState= nfa;
        while(currentIndex<length){
            char c=normalRE.charAt(currentIndex);
            switch (c){
                case '*':
                    NFA temp=new NFA();
                    preState.getEndNFAState().addNewLink(null,preState.getStartNFAState());
                    temp.linkTwoNFA(preState);
                    NFAState newEndNFAState =new NFAState();
                    temp.addNewNode(temp.getEndNFAState(), newEndNFAState,null);
                    temp.setEndNFAState(newEndNFAState);
                    temp.getStartNFAState().addNewLink(null, newEndNFAState);
                    preState=temp;
                    break;
                case '|':
                    nfa.linkTwoNFA(preState);
                    NFA tempState1= nfa;
                    NFA tempState2=getNFAByNormalRE(normalRE.substring(currentIndex+1));
                    NFAState newTwoEndNFAState =new NFAState();
                    nfa =new NFA();
                    nfa.linkTwoNFA(tempState1);
                    nfa.getEndNFAState().addNewLink(null, newTwoEndNFAState);
                    nfa.setEndNFAState(nfa.getStartNFAState());
                    nfa.linkTwoNFA(tempState2);
                    nfa.addNewNode(nfa.getEndNFAState(), newTwoEndNFAState,null);
                    nfa.setEndNFAState(newTwoEndNFAState);
                    break;
                case '(':
                    nfa.linkTwoNFA(preState);
                    int tempIndex=currentIndex+1;
                    while(normalRE.charAt(tempIndex)!=')'){
                        tempIndex++;
                    }
                    preState=getNFAByNormalRE(normalRE.substring(currentIndex+1,tempIndex));
                    currentIndex=tempIndex+1;
                    break;
                default:
                    nfa.linkTwoNFA(preState);
                    preState=new NFA();
                    NFAState newNFAState =new NFAState();
                    preState.addNewNode(preState.getStartNFAState(), newNFAState,c+"");
                    preState.setEndNFAState(newNFAState);
                    break;
            }
            nfa.linkTwoNFA(preState);
        }
        return nfa;
    }
}
