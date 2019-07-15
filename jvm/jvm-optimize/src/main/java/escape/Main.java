package escape;

/**
 * @author Manfred since 2019/7/11
 */
public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 200000; i++) {
            getAge();
        }
    }

    public static int getAge() {
        Student person = new Student(" 小明 ", 18);
        return person.getAge();
    }

    static class Student {
        private String name;
        private int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
