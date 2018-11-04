package fa;

import fa.util.DFA;
import fa.util.DFAState;
import fa.util.NFA;
import fa.util.NFAState;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFAParser {
    public DFA getDFAFromNFA(NFA nfa){
        Set<NFAState> nfaStates=nfa.getNFAStateList();
        NFAState startState=nfa.getStartNFAState();
        DFA resDFA=new DFA();
        boolean isFinish=false;
        // 获得输入字符串集合
        Set<String>inputStrings=getInputStrings(nfa);
        // 起始状态
        Set<NFAState> startStateSet=new HashSet<NFAState>();
        startStateSet.add(startState);
        Set<NFAState> startNFAState=getNullColsure(startStateSet);
        DFAState startDFAState=new DFAState(0,startNFAState,false);
        resDFA.setStartState(startDFAState);
        // 循环获得每个状态
        while(!isFinish){

        }
        return null;
    }

    /**
     * 获得输入字符串集合
     * @param nfa
     * @return
     */
    private Set<String> getInputStrings(NFA nfa){
        Set<NFAState> nfaStates=nfa.getNFAStateList();
        Set<String> res=new HashSet<String>();
        for(NFAState nfaState:nfaStates){

        }
        return res;
    }
    private Set<NFAState> getMov(Set<NFAState> NFAStates,String inputStr){
        Set<NFAState> nfaStates=new HashSet<NFAState>();
        for(NFAState nfaState:NFAStates){
            List<String> nextStrings=nfaState.getNextStrings();
            List<NFAState> nextStates=nfaState.getNextStates();
            if(nextStrings.contains(inputStr)){
                int index=nextStrings.indexOf(inputStr);
                NFAState nextNFAState=nextStates.get(index);
                nfaStates.add(nextNFAState);
            }
        }
        return getNullColsure(nfaStates);
    }

    private Set<NFAState> getNullColsure(Set<NFAState> NFAStates){
        Set<NFAState> tempContainer=new HashSet<NFAState>();
        Set<NFAState> tempAddContainer=new HashSet<NFAState>();
        boolean isFinish=false;
        while(!isFinish){
            for(NFAState nfaState:NFAStates){
                if(!tempContainer.contains(nfaState)){
                    tempContainer.add(nfaState);
                    List<String> nextStrings=nfaState.getNextStrings();
                    List<NFAState> nextStates=nfaState.getNextStates();
                    for(int i=0;i<nextStrings.size();i++){
                        if(nextStrings.get(i)==null){
                            tempAddContainer.add(nextStates.get(i));
                        }
                    }
                }
            }
            NFAStates.addAll(tempAddContainer);
            isFinish=tempAddContainer.isEmpty();
            tempAddContainer.clear();
        }
        return NFAStates;
    }

}
