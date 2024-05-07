package exe44.wildcard.matching;


import java.util.*;

class Solution {
    private static final char WILD_CARD_ASTERISK = '*';

    private static final char WILD_CARD_QUESTION = '?';

    private static final List<Character> ALL_CHAR = new ArrayList<>();

    static {
        for (int i = 0; i < 26; i++) {
            ALL_CHAR.add((char) ('a' + i));
        }
    }

    public boolean isMatch(String s, String p) {
        if (isEmpty(s) && isEmpty(p)) {
            return true;
        }
        if (isEmpty(p) && !isEmpty(s)) {
            return false;
        }

        List<Map<Character, List<Integer>>> autoMachine = createAutoMachine(combineAsterisk(p));
//        printAutoMachine(autoMachine);

        return judgeMatchMachine(autoMachine, s);
    }

    private void printAutoMachine(List<Map<Character, List<Integer>>> autoMachine) {
        String filled = "\t\t";
        System.out.print(filled);
        for (int i = 0; i < ALL_CHAR.size(); i++) {
            System.out.print(ALL_CHAR.get(i) + filled);
        }
        System.out.println();
        for (int i = 0; i < autoMachine.size(); i++) {
            System.out.print(i + filled);
            Map<Character, List<Integer>> characterListMap = autoMachine.get(i);
            for (int j = 0; j < ALL_CHAR.size(); j++) {
                System.out.print(characterListMap.get(ALL_CHAR.get(j)) + filled);
            }
            System.out.println();
        }
    }

    private boolean isEmpty(String s) {
        return null == s || "".equals(s);
    }

    private boolean judgeMatchMachine(List<Map<Character, List<Integer>>> autoMachine, String s) {
        Set<Integer> nowState = new HashSet<>();
        nowState.add(0);
        for (int i = 0; i < s.length(); i++) {
            Character now = s.charAt(i);
            Set<Integer> lastState = new HashSet<>(nowState);
            nowState.clear();
            for (Integer integer : lastState) {
                nowState.addAll(autoMachine.get(integer).get(now));
            }
        }
        return nowState.contains(autoMachine.size() - 1);
    }

    private List<Map<Character, List<Integer>>> createAutoMachine(String p) {
        List<Map<Character, List<Integer>>> result = new ArrayList<>();
        addState(result);
        if (isEmpty(p)) {
            return result;
        }

        int state = 0;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);

            if (c == WILD_CARD_ASTERISK) {
                for (Character character : ALL_CHAR) {
                    result.get(state).get(character).add(state);
                }
            } else if (c == WILD_CARD_QUESTION) {
                for (Character character : ALL_CHAR) {
                    result.get(state).get(character).add(state + 1);
                }
            } else {
                result.get(state).get(c).add(state + 1);
            }
            if (c != WILD_CARD_ASTERISK) {
                // 不是*， 增加下一state
                addState(result);
                state++;
            }
        }
        return result;
    }

    private void addState(List<Map<Character, List<Integer>>> autoMachine) {
        autoMachine.add(createEmptyStateMap());
    }

    private Map<Character, List<Integer>> createEmptyStateMap() {
        Map<Character, List<Integer>> initMap = new HashMap<>(ALL_CHAR.size());
        for (Character character : ALL_CHAR) {
            initMap.put(character, new ArrayList<>());
        }
        return initMap;
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