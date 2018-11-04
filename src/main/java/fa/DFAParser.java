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

        // 循环完成每个状态，从起始状态开始
        Set<DFAState> stateToComplete=new HashSet<DFAState>();
        stateToComplete.add(startDFAState);
        int idCount=1;
        while(!isFinish){
            Set<DFAState> newStates=new HashSet<DFAState>();
            for(DFAState dfaState:stateToComplete){
                resDFA.addState(dfaState);
                for(String str:inputStrings){
                    Set<NFAState> mov=getMov(dfaState.getNFAStates(),str);
                    Set<NFAState> dtran=getNullColsure(mov);
                    Set<DFAState> dfaStates=resDFA.getStates();
                    boolean isDuplicate=false;
                    for(DFAState tempDfaState:dfaStates){
                        Set<NFAState> tempNFAStates=tempDfaState.getNFAStates();
                        if(dtran.containsAll(tempNFAStates)&&dtran.size()==tempNFAStates.size()){
                            dfaState.addTran(str,tempDfaState.getStateId());
                            isDuplicate=true;
                            break;
                        }
                    }
                    if(!isDuplicate){
                        boolean isFinishState=dtran.contains(nfa.getEndNFAState());
                        DFAState newState=new DFAState(idCount,dtran,isFinish);
                        if(isFinishState){
                            resDFA.setFinishState(newState);
                        }
                        idCount++;
                        newStates.add(newState);
                    }
                }
            }
            isFinish=newStates.isEmpty();
            stateToComplete=newStates;
        }
        return resDFA;
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
            List<String> inputStrings=nfaState.getNextStrings();
            for(String str:inputStrings){
                if(str!=null){
                    res.add(str);
                }
            }
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
