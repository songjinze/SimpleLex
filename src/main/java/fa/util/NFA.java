package fa.util;

import java.util.*;

public class NFA {

    private NFAState startNFAState;
    private NFAState currentNFAState;
    private NFAState endNFAState;
    private Set<NFAState> NFAStateList;
    public NFA(){
        startNFAState =new NFAState(new ArrayList<String>(),new ArrayList<NFAState>());
        currentNFAState = startNFAState;
        endNFAState = startNFAState;
        NFAStateList =new HashSet<NFAState>();
        NFAStateList.add(startNFAState);
    }
    public NFAState getStartNFAState(){
        return startNFAState;
    }
    public NFAState getEndNFAState(){
        return endNFAState;
    }
    public NFAState getCurrentNFAState(){
        return currentNFAState;
    }

    public void setEndNFAState(NFAState endNFAState) {
        this.endNFAState = endNFAState;
    }

    public void setStartNFAState(NFAState startNFAState) {
        this.startNFAState = startNFAState;
    }
    public void setCurrentNFAState(NFAState currentNFAState){
        this.currentNFAState = currentNFAState;
    }
    public Set<NFAState> getNFAStateList(){
        return NFAStateList;
    }
    public void addNewNode(NFAState index, NFAState newNFAState, String parse){
        index.addNewLink(parse, newNFAState);
        NFAStateList.add(newNFAState);
    }

    /**
     * 将另个NFA连接到尾部
     */
    public void linkTwoNFA(NFA nextNFA){
        NFAState newEndNFAState =nextNFA.getEndNFAState();
        endNFAState.addNewLink(null,nextNFA.getStartNFAState());
        endNFAState = newEndNFAState;
        NFAStateList.addAll(nextNFA.getNFAStateList());
    }
}
