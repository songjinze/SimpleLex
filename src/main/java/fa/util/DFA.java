package fa.util;

import java.util.HashSet;
import java.util.Set;

public class DFA {
    private DFAState startState;
    private Set<DFAState> finishStates;
    private Set<DFAState> states;

    public DFA(){
        startState=null;
        finishStates=new HashSet<DFAState>();
        states=new HashSet<DFAState>();
    }

    public DFAState getStartState() {
        return startState;
    }

    public Set<DFAState> getFinishStates() {
        return finishStates;
    }

    public Set<DFAState> getStates() {
        return states;
    }

    public void addState(DFAState dfaState){
        states.add(dfaState);
    }

    public void setFinishState(DFAState finishState){
        finishStates.add(finishState);
    }

    public void setStartState(DFAState startState){
        this.startState=startState;
    }
}
