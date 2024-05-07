package exe223.rectangle.area;

/**
 * Given the coordinates of two rectilinear rectangles in a 2D plane, return the total area
 * covered by the two rectangles.
 * <p>
 * The first rectangle is defined by its bottom-left corner (A, B) and its top-right corner (C, D).
 * <p>
 * The second rectangle is defined by its bottom-left corner (E, F) and its top-right corner (G, H).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Rectangle Area
 * Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2
 * Output: 45
 * Example 2:
 * <p>
 * Input: A = -2, B = -2, C = 2, D = 2, E = -2, F = -2, G = 2, H = 2
 * Output: 16
 */
class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area = (C - A) * (D - B) + (G - E) * (H - F);
        // 判断是否有交集：通过判断矩形1的4个点是否有落在矩形2的范围内（或者反之也可以）
        // 矩形1的4个点的坐标分别为（A, B), (C, D), (A, D), (C, B)
        // 矩形2的4个点的坐标分别为（E, F), (G, H), (E, H), (G, F)
        int left = Math.max(A, E);
        int right = Math.min(C, G);
        int bottom = Math.max(B, F);
        int top = Math.min(D, H);
        if (right > left && top > bottom) {
            return area - (right - left) * (top - bottom);
        }
        return area;
    }
}