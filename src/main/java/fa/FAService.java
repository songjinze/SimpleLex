package fa;

import fa.util.DFA;
import fa.util.NFA;

import java.util.List;

public interface FAService {

    /**
     * 通过正则表达式得到NFA
     * @param re
     * @return
     */
    NFA getNFAByRE(String re);

    /**
     * 通过NFA得到DFA
     * @param nfa
     * @return
     */
    DFA getDFAByNFA(NFA nfa);

    /**
     * 使用一组DFA进行词法分析分析词法单元
     * @param DFAs
     * @param inputString
     * @return
     */
    List<String> getTokens(List<DFA> DFAs, String inputString);

}
