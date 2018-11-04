package fa;

import fa.util.DFAState;
import fa.util.NFAState;

import java.util.List;

public interface FAService {

    /**
     * 通过正则表达式得到NFA
     * @param re
     * @return
     */
    NFAState getNFAByRE(String re);

    /**
     * 通过NFA得到DFA
     * @param nfaState
     * @return
     */
    DFAState getDFAByNFA(NFAState nfaState);

    /**
     * 使用一组DFA进行词法分析分析词法单元
     * @param DFAs
     * @param inputString
     * @return
     */
    List<String> getTokens(List<DFAState> DFAs,String inputString);

}
