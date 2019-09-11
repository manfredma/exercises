package exe1;

public interface Calculator {
    /**
     * calculate sum of an integer array
     *
     * @param numbers
     * @return
     */
    long sum(int[] numbers);

    /**
     * shutdown pool or reclaim any related resources
     */
    void shutdown();
}