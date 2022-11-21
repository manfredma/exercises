package manfred.end.orthogonal.design;

/**
 * @author manfred on 2022/11/18.
 */
public class Sorter {

    public static void sortStudentsByHeight(Highable[] highableArray) {
        for (int y = 0; y < highableArray.length - 1; y++) {
            for (int x = 1; x < highableArray.length - y; x++) {
                if (highableArray[x].getHeight() < highableArray[x - 1].getHeight()) {
                    swap(highableArray, x, x - 1);
                }
            }
        }
    }

    private static void swap(Highable[] highableArray, int index1, int index2) {
        Highable temp = highableArray[index1];
        highableArray[index1] = highableArray[index2];
        highableArray[index2] = temp;
    }
}
