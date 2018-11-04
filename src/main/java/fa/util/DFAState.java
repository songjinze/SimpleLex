package fa.util;

import java.util.List;
import java.util.Set;

public class DFAState {
    private char stateId;
    private List<TranTable> tranTables;
    private Set<Integer> NFAStates;
    private boolean isFinish;

    public DFAState(char stateId,Set<Integer> NFAStates,boolean isFinish){
        this.stateId=stateId;
        this.NFAStates=NFAStates;
        this.isFinish=isFinish;
    }
    public void addTran(String input,char toStateId){
        tranTables.add(new TranTable(input,toStateId));
    }

    public char getStateId() {
        return stateId;
    }

    public List<TranTable> getTranTables() {
        return tranTables;
    }

    public Set<Integer> getNFAStates() {
        return NFAStates;
    }

    class TranTable{
        String input;
        char toStateId;
        TranTable(String input,char toStateId){
            this.input=input;
            this.toStateId=toStateId;
        }
    }

}
