/**
 * Java 泛型举例
 *
 * @param <T>
 */
class DataHolder<T> {
    T item;

    public void setData(T t) {
        this.item = t;
    }

    public T getData() {
        return this.item;
    }

    /**
     * 泛型方法
     *
     * @param e
     */
    public <E> void PrinterInfo(E e) {
        System.out.println(e);
    }
}