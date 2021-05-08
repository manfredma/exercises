package spring;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author manfred
 * @since 2019-11-11 下午3:58
 */
public class TestJavaConfigBean {

    @Value("${timeout:100}")
    private int timeout;

    @Value("${batch:200}")
    private int batch;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }
}
