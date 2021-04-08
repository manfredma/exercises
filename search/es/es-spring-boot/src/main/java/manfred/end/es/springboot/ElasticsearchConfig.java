package manfred.end.es.springboot;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ElasticsearchConfig class
 *
 * @author : zaw
 * @date : 2019/4/22
 */
@Configuration
public class ElasticsearchConfig {

    @Value("${xuecheng.elasticsearch.host-list}")
    private String hostList;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(getHttpHostList(hostList)));
    }

    private HttpHost[] getHttpHostList(String hostList) {
        String[] hosts = hostList.split(",");
        HttpHost[] httpHostArr = new HttpHost[hosts.length];
        for (int i = 0; i < hosts.length; i++) {
            String[] items = hosts[i].split(":");
            httpHostArr[i] = new HttpHost(items[0], Integer.parseInt(items[1]), "http");
        }
        return httpHostArr;
    }

    // rest low level client
    @Bean
    public RestClient restClient() {
        return RestClient.builder(getHttpHostList(hostList)).build();
    }
}
