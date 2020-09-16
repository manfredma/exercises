package exe71.simplify.path;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public String simplifyPath(String path) {
        if (path == null || "".equals(path)) {
            return path;
        }

        List<String> dirs = new ArrayList<>();
        String[] dirAll = path.split("/");
        for (String s : dirAll) {
            if (s.length() == 0 || ".".equals(s)) {
                continue;
            }
            if ("..".equals(s)) {
                if (dirs.size() > 0) {
                    dirs.remove(dirs.size() - 1);
                }
            } else {
                dirs.add(s);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < dirs.size(); i++) {
            result.append("/").append(dirs.get(i));
        }
        if (dirs.size() == 0) {
            return "/";
        }
        return result.toString();
    }
}