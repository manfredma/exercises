package exe149.max.points.on.a.line;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int maxPoints(int[][] points) {
        if (points.length == 0) {
            return 0;
        }

        Point first = new Point(points[0]);
        List<Point> pointLists = new ArrayList<>();
        List<Line> lines = new ArrayList<>();
        pointLists.add(first);
        for (int i = 1; i < points.length; i++) {
            Point cur = new Point(points[i]);
            if (pointLists.contains(cur)) {
                pointLists.get(pointLists.indexOf(cur)).num++;
                continue;
            }
            Set<Point> allReady = new HashSet<>();
            for (int j = 0; j < lines.size(); j++) {
                if (lines.get(j).isInLine(cur)) {
                    lines.get(j).allPoints.add(cur);
                    allReady.addAll(lines.get(j).allPoints);
                }
            }
            Set<Point> pre = new HashSet<>(pointLists);
            pre.removeAll(allReady);

            for (Point point : pre) {
                lines.add(new Line(point, cur));
            }
            pointLists.add(cur);
        }

        if (lines.size() == 0) {
            return pointLists.get(0).num;
        }

        int result = 0;
        for (Line line : lines) {
            int curR = 0;
            for (Point allPoint : line.allPoints) {
                curR += allPoint.num;
            }
            if (curR > result) {
                result = curR;
            }
        }
        return result;
    }

    private static class Line {
        Point p1;

        Point p2;

        List<Point> allPoints = new ArrayList<>();

        boolean isInLine(Point p) {
            if (p.equals(p1) || p.equals(p2)) {
                return true;
            }

            boolean re = (long)(p1.x - p2.x) * (long)(p.y - p2.y) == (long)(p1.y - p2.y) * (long)(p.x - p2.x);
            // System.out.println(p + "is in line " + this + " - " + re + "" + (p1.x - p2.x) + "" + (p.y - p2.y) + "" + (p1.y - p2.y) + "" + (p.x - p2.x));
            return re;
        }

        public Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
            this.allPoints.add(p1);
            this.allPoints.add(p2);
        }

        @Override
        public String toString() {
            return "Line{" + p1 + ", " + p2 + "}";
        }
    }

    private static class Point {
        int x;
        int y;

        int num = 1;

        Point(int[] coordinate) {
            this.x = coordinate[0];
            this.y = coordinate[1];
        }

        @Override
        public int hashCode() {
            return x + y;
        }

        @Override
        public boolean equals(Object o) {
            Point p = (Point) o;
            return this.x == p.x && this.y == p.y;
        }

        @Override
        public String toString() {
            return "[" + x + ", " + y + "]";
        }
    }
}