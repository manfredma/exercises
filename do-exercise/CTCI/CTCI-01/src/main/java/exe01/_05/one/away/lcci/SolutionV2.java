package exe01._05.one.away.lcci;

class SolutionV2 {
    public boolean oneEditAway(String first, String second) {
        if (first.length() - second.length() > 1 || second.length() - first.length() > 1) {
            return false;
        }

        if (first.length() == second.length()) {
            return oneUpdateEdit(first, second);

        } else {
            String longer;
            String shorter;
            // 差距为1
            if (first.length() > second.length()) {
                longer = first;
                shorter = second;
            } else {
                longer = second;
                shorter = first;
            }
            return oneInsertEdit(longer, shorter);
        }
    }

    private boolean oneInsertEdit(String longer, String shorter) {
        int diff = 0;
        for (int i = 0; i < longer.length(); i++) {
            int shortIndex = i - diff;
            if (shortIndex < shorter.length() && longer.charAt(i) == shorter.charAt(shortIndex)) {
                continue;
            }
            diff++;
            if (diff > 1) {
                return false;
            }
        }
        return true;
    }

    private boolean oneUpdateEdit(String first, String second) {
        if (first.equals(second)) {
            return true;
        }
        int diff = 0;
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) == second.charAt(i)) {
                continue;
            }
            diff++;
            if (diff > 1) {
                return false;
            }
        }
        return true;
    }
}