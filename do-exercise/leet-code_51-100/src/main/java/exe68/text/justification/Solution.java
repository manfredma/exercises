package exe68.text.justification;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int columnLength = 0;
        int beginIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < words.length; i++) {
            if (columnLength + words[i].length() > maxWidth) {
                StringBuilder row = buildRow(words, maxWidth, beginIndex, endIndex);
                result.add(row.toString());
                beginIndex = i;
                endIndex = i;
                columnLength = 0;
                i--;
            } else if (columnLength + words[i].length() == maxWidth) {
                endIndex = i;
                StringBuilder row = buildRow(words, maxWidth, beginIndex, endIndex);
                result.add(row.toString());
                beginIndex = i + 1;
                endIndex = i + 1;
                columnLength = 0;
            } else {
                columnLength = columnLength + words[i].length() + 1;
                endIndex = i;
            }
        }

        // 处理最后一行
        if (columnLength > 0) {
            int x = 0;
            StringBuilder row = new StringBuilder();
            for (int i = beginIndex; i < words.length; i++) {
                row.append(words[i]).append(' ');
                x += words[i].length() + 1;
            }
            for (int i = x; i < maxWidth; i++) {
                row.append(' ');
            }
            result.add(row.toString());
        }

        return result;
    }

    private StringBuilder buildRow(String[] words, int maxWidth, int beginIndex, int endIndex) {
        StringBuilder row = new StringBuilder();
        if (endIndex == beginIndex) {
            row.append(words[beginIndex]);
            for (int j = words[beginIndex].length(); j < maxWidth; j++) {
                row.append(' ');
            }
        } else {
            int slotNum = endIndex - beginIndex;
            int spaceNum = maxWidth;
            for (int j = beginIndex; j <= endIndex; j++) {
                spaceNum -= words[j].length();
            }
            int x = spaceNum / slotNum;
            int r = spaceNum % slotNum;
            for (int j = 0; j < slotNum; j++) {
                row.append(words[beginIndex + j]);
                for (int k = 0; k < (j < r ? x + 1 : x); k++) {
                    row.append(' ');
                }
            }
            row.append(words[endIndex]);
        }
        return row;
    }
}