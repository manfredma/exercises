package exe87.scramble.string;

class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) {
//            System.out.println(s1 + ":" + s2 + " = true(1)" );
            return true;
        }
        if (s1.length() == 1) {
//            System.out.println(s1 + ":" + s2 + " = false(2)" );
            return false;
        }

        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i))) {
//                System.out.println(s1 + ":" + s2 + " = true(3)" );
                return true;
            } else if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i))
                    && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) {
//                System.out.println(s1 + ":" + s2 + " = true(4)" );
                return true;
            }
        }
//        System.out.println(s1 + ":" + s2 + " = false(5)" );
        return false;
    }
}