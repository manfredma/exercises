package manfred.end.es.client.rest;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

/**
 * 获取文档数据
 */
public class GetDocumentDemo {

    private static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {
        try (RestHighLevelClient client = InitDemo.getClient();) {
            // 1、创建获取文档请求
            GetRequest request = new GetRequest(
                    "mess",   //索引
                    "_doc",     // mapping type
                    "1");     //文档id  

            //选择返回的字段
            String[] includes = new String[]{"message", "*Date"};
            String[] excludes = Strings.EMPTY_ARRAY;
            FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes,
                    excludes);
            request.fetchSourceContext(fetchSourceContext);

            //3、发送请求        
            GetResponse getResponse = null;
            try {
                // 同步请求
                getResponse = client.get(request, RequestOptions.DEFAULT);
            } catch (ElasticsearchException e) {
                if (e.status() == RestStatus.NOT_FOUND) {
                    LOGGER.error("没有找到该id的文档");
                }
                if (e.status() == RestStatus.CONFLICT) {
                    LOGGER.error("获取时版本冲突了，请在此写冲突处理逻辑！");
                }
                LOGGER.error("获取文档异常", e);
            }

            //4、处理响应
            if (getResponse != null) {
                String index = getResponse.getIndex();
                String type = getResponse.getType();
                String id = getResponse.getId();
                if (getResponse.isExists()) { // 文档存在
                    long version = getResponse.getVersion();
                    String sourceAsString = getResponse.getSourceAsString(); //结果取成 String       
                    Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();  // 结果取成Map
                    byte[] sourceAsBytes = getResponse.getSourceAsBytes();    //结果取成字节数组

                    LOGGER.info("index:" + index + "  type:" + type + "  id:" + id);
                    LOGGER.info(sourceAsString);

                } else {
                    LOGGER.error("没有找到该id的文档");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}