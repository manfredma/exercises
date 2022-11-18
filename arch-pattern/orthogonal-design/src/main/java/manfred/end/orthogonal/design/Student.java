package manfred.end.orthogonal.design;

/**
 * @author manfred on 2022/11/18.
 */
public class Student {

    private String name;

    private int height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static void sortStudentsByHeight(Student[] students) {
        for (int y = 0; y < students.length - 1; y++) {
            for (int x = 1; x < students.length - y; x++) {
                if (students[x].height > students[x - 1].height) {
                    swap(students, x, x - 1);
                }
            }
        }
    }

    private static void swap(Student[] students, int index1, int index2) {
        Student temp = students[index1];
        students[index1] = students[index2];
        students[index2] = temp;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", height=" + height +
                '}';
    }
}
