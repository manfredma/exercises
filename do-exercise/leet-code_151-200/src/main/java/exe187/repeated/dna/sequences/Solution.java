package exe187.repeated.dna.sequences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        /*

        keep a hash set that contains all prev subs. doing a .add() which returns
        false, means that we add this to the list bc it's a dupe

        return the list
        */
        if (s.length() <= 10) {
            return new ArrayList<>();
        }

        Set<String> seen = new HashSet<>();
        Set<String> added = new HashSet<>();
        for (int i = 0; i <= s.length() - 10; ++i) {
            String currSub = s.substring(i, i + 10);
            if (!seen.add(currSub)) {
                added.add(currSub);
            }
        }

        return new ArrayList<>(added);
    }
}