package fa.util;

import java.util.List;
import java.util.Set;

public class DFAState {
    private int stateId;
    private List<TranTable> tranTables;
    private Set<NFAState> NFAStates;
    private boolean isFinish;

    public DFAState(int stateId,Set<NFAState> NFAStates,boolean isFinish){
        this.stateId=stateId;
        this.NFAStates=NFAStates;
        this.isFinish=isFinish;
    }
    public void addTran(String input,int toStateId){
        tranTables.add(new TranTable(input,toStateId));
    }

    public int getStateId() {
        return stateId;
    }

    public List<TranTable> getTranTables() {
        return tranTables;
    }

    public Set<NFAState> getNFAStates() {
        return NFAStates;
    }

    public boolean isFinish(){
        return isFinish;
    }
    class TranTable{
        String input;
        int toStateId;
        TranTable(String input,int toStateId){
            this.input=input;
            this.toStateId=toStateId;
        }
    }

    public int getStateIdByInput(String input){
        for(TranTable tranTable:tranTables){
            if(tranTable.input.equals(input)){
                return tranTable.toStateId;
            }
        }
        return -1;
    }
}
