package fa.util;

import java.util.ArrayList;
import java.util.List;

public class NFAState {
    private List<String> nextStrings;
    private List<NFAState> nextStates;

    public NFAState(List<String> nextStrings, List<NFAState> nextStates){
        this.nextStates=nextStates;
        this.nextStrings=nextStrings;
    }

    public NFAState(){
        nextStates=new ArrayList<NFAState>();
        nextStrings=new ArrayList<String>();
    }


    public List<String> getNextStrings() {
        return nextStrings;
    }

    public List<NFAState> getNextStates() {
        return nextStates;
    }

    public void addNewLink(String newLinkStr, NFAState NFAState){
        nextStrings.add(newLinkStr);
        nextStates.add(NFAState);
    }
}