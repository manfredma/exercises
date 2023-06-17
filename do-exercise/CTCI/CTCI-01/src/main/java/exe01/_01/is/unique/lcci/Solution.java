package exe01._01.is.unique.lcci;

class Solution {
    public boolean isUnique(String astr) {
        int exists = 0;
        char[] chars = astr.toCharArray();
        for (char aChar : chars) {
            int shift = 1 << (aChar - 'a');
            int newExist = exists | shift;
            if (exists == newExist) {
                return false;
            }
            exists = newExist;
        }
        return true;
    }
}