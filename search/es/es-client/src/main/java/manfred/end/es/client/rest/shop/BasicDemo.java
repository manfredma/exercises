package manfred.end.es.client.rest.shop;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * 在ES的官方客户端RestClient中，数据的CRUD都遵循一套标准写法流程：
 * <p>
 * 1.连接集群，获取RestHighLevelClient，作为CRUD的操作对象
 * 2.{Search|Index|Update|Delete}Request request = new {Search|Index|Update|Delete}Request();
 * 3.初始化request对象；
 * 4.X{Search|Index|Update|Delete}Response response = porosClient.{search|index|update|delete}
 * (request);
 * 5.解析response
 */
public class BasicDemo {

    public static void main(String[] args) throws Exception {

        // 连接集群
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // 构造 Request 对象
        SearchRequest request = new SearchRequest("shop");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        request.source(builder);
        builder.query(QueryBuilders.termQuery("shopid", 1001));
        builder.size(10);
        builder.from(0);

        // 构造响应
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);

        // 解析响应
        System.out.println(searchResponse);

        client.close();
        System.out.println();


    }
}
