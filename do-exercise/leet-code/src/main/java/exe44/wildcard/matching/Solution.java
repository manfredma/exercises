package exe44.wildcard.matching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    private static final char WILD_CARD_ASTERISK = '*';

    private static final char WILD_CARD_QUESTION = '?';

    public boolean isMatch(String s, String p) {
        if ("".equals(s) && "".equals(p)) {
            return true;
        }
        p = combineAsterisk(p);
        if ("".equals(s) && String.valueOf(WILD_CARD_ASTERISK).equals(p)) {
            return true;
        } else if ("".equals(s)) {
            return false;
        }
        List<Integer> asteriskPosition = new ArrayList<>();
        Map<Integer, Integer> asteriskPosition2S = new HashMap<>(p.length());
        int pIndex = 0;
        int sIndex = 0;
        while (true) {
            if (pIndex > p.length() - 1 || sIndex > s.length() - 1) {
                if (canLookBack(asteriskPosition, asteriskPosition2S)) {
                    // 回溯一下
                    pIndex = lookBackP(s, p, asteriskPosition, asteriskPosition2S);
                    sIndex = lookBackS(s, asteriskPosition, asteriskPosition2S);
                    printInfo(asteriskPosition, asteriskPosition2S, pIndex, sIndex, "7");
                    continue;
                } else {
                    printInfo(asteriskPosition, asteriskPosition2S, pIndex, sIndex, "8");
                    return false;
                }
            }
            // 需要判断是否匹配，如果匹配，则返回匹配，
            // 否则，判断是否能够回溯，如果可以，则回溯；否则，则返回不匹配
            if (sIndex == s.length() - 1) {
                if (pIndex >= p.length() - 3 && pIndex < p.length()) {
                    List<Character> c = new ArrayList<>();
                    for (int i = pIndex; i < p.length(); i++) {
                        if (WILD_CARD_ASTERISK != p.charAt(i)) {
                            c.add(p.charAt(i));
                        }
                    }
                    if (c.size() == 0) {
                        return true;
                    } else if (c.size() == 1 && isCharMatch(c.get(0), s.charAt(sIndex))) {
                        return true;
                    }
                }
                if (canLookBack(asteriskPosition, asteriskPosition2S)) {
                    // 回溯一下
                    pIndex = lookBackP(s, p, asteriskPosition, asteriskPosition2S);
                    sIndex = lookBackS(s, asteriskPosition, asteriskPosition2S);
                    printInfo(asteriskPosition, asteriskPosition2S, pIndex, sIndex, "2");
                    continue;
                } else {
                    printInfo(asteriskPosition, asteriskPosition2S, pIndex, sIndex, "3");
                    return false;
                }

            }

            if (WILD_CARD_ASTERISK == p.charAt(pIndex)) {
                if (pIndex == p.length() - 1) {
                    return true;
                } else {
                    if (!asteriskPosition.contains(pIndex)) {
                        asteriskPosition.add(pIndex);
                    }
                    int n = nextPosition(s, sIndex, p.charAt(pIndex + 1));
                    if (n != -1) {
                        asteriskPosition2S.put(pIndex, n);
                        pIndex++;
                        sIndex = n;
                    } else {
                        printInfo(asteriskPosition, asteriskPosition2S, pIndex, sIndex, "4");
                        return false;
                    }
                }
            } else if (WILD_CARD_QUESTION == p.charAt(pIndex)) {
                pIndex++;
                sIndex++;
            } else {
                if (s.charAt(sIndex) == p.charAt(pIndex)) {
                    pIndex++;
                    sIndex++;
                } else {
                    if (canLookBack(asteriskPosition, asteriskPosition2S)) {
                        // 回溯一下
                        pIndex = lookBackP(s, p, asteriskPosition, asteriskPosition2S);
                        sIndex = lookBackS(s, asteriskPosition, asteriskPosition2S);
                        printInfo(asteriskPosition, asteriskPosition2S, pIndex, sIndex, "5");
                    } else {
                        printInfo(asteriskPosition, asteriskPosition2S, pIndex, sIndex, "6");
                        return false;
                    }
                }
            }
        }
    }

    private void printInfo(List<Integer> asteriskPosition, Map<Integer, Integer> asteriskPosition2S, int pIndex, int sIndex, String s2) {
//        System.out.print(s2 + "--");
//        System.out.print(asteriskPosition + "--");
//        System.out.print(asteriskPosition2S + "--");
//        System.out.println("pIndex=" + pIndex + "; sIndex=" + sIndex);
    }

    private int lookBackS(String s, List<Integer> asteriskPosition, Map<Integer, Integer> asteriskPosition2S) {
        for (int i = asteriskPosition.size() - 1; i >= 0; i--) {
            if (asteriskPosition2S.containsKey(asteriskPosition.get(i))) {
                return asteriskPosition2S.get(asteriskPosition.get(i));
            }
        }
        return s.length();
    }

    private int lookBackP(String s, String p, List<Integer> asteriskPosition, Map<Integer, Integer> asteriskPosition2S) {
        for (int i = asteriskPosition.size() - 1; i >= 0; i--) {
            if (asteriskPosition2S.containsKey(asteriskPosition.get(i))) {
                int pIndex = asteriskPosition.get(i);
                int sIndex = asteriskPosition2S.get(pIndex);
                int n = nextPosition(s, sIndex + 1, p.charAt(pIndex + 1));
                if (n != -1) {
                    asteriskPosition2S.put(pIndex, n);
                    return pIndex + 1;
                } else {
                    asteriskPosition2S.remove(pIndex);
                    return p.length();
                }
            }
        }
        return p.length();
    }

    private boolean canLookBack(List<Integer> asteriskPosition,
                                Map<Integer, Integer> asteriskPosition2S) {
        if (asteriskPosition.size() > 0) {
            return asteriskPosition2S.containsKey(asteriskPosition.get(0));
        }
        return false;
    }

    private boolean isCharMatch(char p, char s) {
        if (WILD_CARD_QUESTION == p) {
            return true;
        } else if (WILD_CARD_ASTERISK == p) {
            return true;
        }
        return p == s;
    }

    private int nextPosition(String s, int i, char p) {
        if (i > s.length() - 1) {
            return -1;
        }
        if (WILD_CARD_QUESTION == p) {
            return i;
        }
        for (int j = i; j < s.length(); j++) {
            if (s.charAt(j) == p) {
                return j;
            }
        }
        return -1;
    }

    private String combineAsterisk(String p) {
        StringBuilder result = new StringBuilder();
        char pc = '0';
        for (int i = 0; i < p.length(); i++) {
            if (WILD_CARD_ASTERISK != pc) {
                result.append(p.charAt(i));
                pc = p.charAt(i);
            } else {
                if (WILD_CARD_ASTERISK != p.charAt(i)) {
                    result.append(p.charAt(i));
                    pc = p.charAt(i);
                }
            }
        }
        return result.toString();
    }
}