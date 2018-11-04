package fa;

import fa.util.DFA;
import fa.util.DFAState;
import fa.util.NFA;

import java.util.ArrayList;
import java.util.List;

public class FAServiceImpl implements FAService {
    public NFA getNFAByRE(String re) {
        return new NFAParser().getNFAByNormalRE(re);
    }

    public DFA getDFAByNFA(NFA nfa) {
        return new DFAParser().getDFAFromNFA(nfa);
    }

    public List<String> getTokens(List<DFA> DFAs, String inputString) {
        List<String> res=new ArrayList<String>();
        if(inputString==null){
            return res;
        }
        int preIndex=0;
        int currentIndex=1;
        while(preIndex!=inputString.length()){
            String temp=inputString.substring(preIndex,currentIndex);
            while(isToken(DFAs,temp)!=-1&&currentIndex<inputString.length()){
                currentIndex++;
                temp=inputString.substring(preIndex,currentIndex);
            }
            if(currentIndex==inputString.length()){
                res.add(temp);
                break;
            }else{
                res.add(inputString.substring(preIndex,currentIndex-1));
                preIndex=currentIndex;
                currentIndex=preIndex+1;
            }
        }
        return res;
    }

    /**
     * 判断字符串是否匹配某个DFA
     * @return 匹配则返回对应list的下标，不匹配则返回-1
     * 若有多个匹配，则下表最小优先。
     */
    private int isToken(List<DFA> DFAs,String inputString){
        for(int i=0;i<DFAs.size();i++){
            DFA dfa=DFAs.get(i);
            DFAState currentState=dfa.getStartState();
            for(char c:inputString.toCharArray()){
                if(currentState==null){
                    break;
                }
                currentState=dfa.getStateById(currentState.getStateIdByInput(c+""));
            }
            if(currentState.isFinish()){
                return i;
            }
        }
        return -1;
    }
}
