package manfred.end.es.springboot;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * TestES class
 *
 * @author : zaw
 * @date : 2019/4/22
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestESRestClient {

    @Autowired
    RestHighLevelClient restHighLevelClient;    //ES连接对象

    @Autowired
    RestClient restClient;

    /**
     * 创建索引库
     *
     * @throws IOException
     */
    @Test
    public void testCreateIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("xc_course");
        /*
         * {
         * 	"settings":{
         * 		"index":{
         * 			"number_of_shards":1,
         * 			"number_of_replicas":0
         *       }
         *    }
         * }
         */
        request.settings(Settings.builder().put("number_of_shards", 1).put("number_of_replicas", 0));
        IndicesClient indicesClient = restHighLevelClient.indices();    //通过ES连接对象获取索引库管理对象
        CreateIndexResponse response = indicesClient.create(request);
        System.out.println(response.isAcknowledged());  //操作是否成功
    }

    /**
     * 删除索引库
     *
     * @throws IOException
     */
    @Test
    public void testDeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("xc_course");
        IndicesClient indicesClient = restHighLevelClient.indices();
        DeleteIndexResponse response = indicesClient.delete(request);
        System.out.println(response.isAcknowledged());
    }

    /**
     * 创建索引库时指定映射
     *
     * @throws IOException
     */
    @Test
    public void testCreateIndexWithMapping() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("xc_course");
        request.settings(Settings.builder().put("number_of_shards", 1).put("number_of_replicas", 0));
        request.mapping("doc", "{\n" +
                "    \"properties\": {\n" +
                "        \"name\": {\n" +
                "            \"type\": \"text\",\n" +
                "            \"analyzer\": \"ik_max_word\",\n" +
                "            \"search_analyzer\": \"ik_smart\"\n" +
                "        },\n" +
                "        \"price\": {\n" +
                "            \"type\": \"scaled_float\",\n" +
                "            \"scaling_factor\": 100\n" +
                "        },\n" +
                "        \"timestamp\": {\n" +
                "            \"type\": \"date\",\n" +
                "            \"format\": \"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd\"\n" +
                "        }\n" +
                "    }\n" +
                "}", XContentType.JSON);
        IndicesClient indicesClient = restHighLevelClient.indices();
        CreateIndexResponse response = indicesClient.create(request);
        System.out.println(response.isAcknowledged());
    }

    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 添加文档
     *
     * @throws IOException
     */
    @Test
    public void testAddDocument() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", "Java核心技术");
        jsonMap.put("price", 66.6);
        jsonMap.put("timestamp", FORMAT.format(new Date(System.currentTimeMillis())));
        IndexRequest request = new IndexRequest("xc_course", "doc");
        request.source(jsonMap);
        IndexResponse response = restHighLevelClient.index(request);
        System.out.println(response);
    }

    /**
     * 根据id查询文档
     *
     * @throws IOException
     */
    @Test
    public void testFindById() throws IOException {
        GetRequest request = new GetRequest("xc_course", "doc", "1");
        GetResponse response = restHighLevelClient.get(request);
        System.out.println(response);
    }

    /**
     * 根据id更新文档
     *
     * @throws IOException
     */
    @Test
    public void testUpdateDoc() throws IOException {
        UpdateRequest request = new UpdateRequest("xc_course", "doc", "BtcGnHgBqMLYNOHtVsoa");
        Map<String, Object> docMap = new HashMap<>();
        docMap.put("name", "Spring核心技术");
        docMap.put("price", 99.8);
        docMap.put("timestamp", FORMAT.format(new Date(System.currentTimeMillis())));
        request.doc(docMap);
        UpdateResponse response = restHighLevelClient.update(request);
        System.out.println(response);
        testFindById();
    }

    /**
     * 根据id删除文档
     *
     * @throws IOException
     */
    @Test
    public void testDeleteDoc() throws IOException {
        DeleteRequest request = new DeleteRequest("xc_course", "doc", "BtcGnHgBqMLYNOHtVsoa");
        DeleteResponse response = restHighLevelClient.delete(request);
        System.out.println(response);
    }

    /**
     * 查询所有文档——matchAllQuery
     *
     * @throws IOException
     */
    @Test
    public void testMatchAll() throws IOException {
        // POST     /xc_course/doc
        SearchRequest request = new SearchRequest("xc_course");    //DSL搜索请求对象
        request.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();    //DSL请求体构造对象
        /*
         * {
         * 	"from":2,"size":1,
         * 	"query":{
         * 		"match_all":{
         *
         *                }
         *  	},
         * 	"_source":["name","studymodel"]
         * }
         */
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //参数1：要返回哪些字段   参数2：不要返回哪些字段    两者通常指定其一
        searchSourceBuilder.fetchSource(new String[]{"name", "studymodel"}, null);
        //将请求体设置到请求对象中
        request.source(searchSourceBuilder);
        //发起DSL请求
        SearchResponse response = restHighLevelClient.search(request);
        System.out.println(response);


        SearchHits hits = response.getHits();                //hits
        if (hits != null) {
            SearchHit[] results = hits.getHits();            //hits.hits
            for (SearchHit result : results) {
                System.out.println(result.getSourceAsMap()); //hits.hits._source
            }
        }

    }

    /**
     * 分页查询
     *
     * @throws IOException
     */
    @Test
    public void testPaginating() throws IOException {
        SearchRequest request = new SearchRequest("xc_course");
        request.types("doc");

        int page = 1, size = 1;
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from((page - 1) * size);
        searchSourceBuilder.size(size);
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.fetchSource(new String[]{"name", "studymodel"}, null);

        request.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(request);
        System.out.println(response);
    }

    /**
     * 根据id精确匹配——termsQuery
     */
    @Test
    public void testQueryByIds() {
        SearchRequest request = new SearchRequest("xc_course");
        request.types("doc");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        List<String> ids = Arrays.asList("1", "3");
        sourceBuilder.query(QueryBuilders.termsQuery("_id", ids));

        printResult(request, sourceBuilder);
    }

    private void printResult(SearchRequest request, SearchSourceBuilder sourceBuilder) {
        request.source(sourceBuilder);
        SearchResponse response = null;
        try {
            System.out.println(request);
            response = restHighLevelClient.search(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchHits hits = response.getHits();
        if (hits != null) {
            SearchHit[] results = hits.getHits();
            for (SearchHit result : results) {
                System.out.println(result.getSourceAsMap());
            }
        }
    }

    /**
     * 全文检索—— matchQuery
     */
    @Test
    public void testMatchQuery() {

        SearchRequest request = new SearchRequest("xc_course");
        request.types("doc");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("name", "bootstrap基础"));

        printResult(request, sourceBuilder);
    }


    /**
     * operator
     */
    @Test
    public void testMatchQuery2() {
        SearchRequest request = new SearchRequest("xc_course");
        request.types("doc");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("name", "java基础").operator(Operator.AND));

        printResult(request, sourceBuilder);
    }

    /**
     * minimum_should_match
     */
    @Test
    public void testMatchQuery3() {
        SearchRequest request = new SearchRequest("xc_course");
        request.types("doc");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("name", "spring开发指南").minimumShouldMatch("70%")); //3*0.7 -> 2

        printResult(request, sourceBuilder);
    }

    /**
     * 多域检索——multiMatchQuery
     */
    @Test
    public void testMultiMatchQuery() {
        SearchRequest request = new SearchRequest("xc_course");
        request.types("doc");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.
                multiMatchQuery("spring css", "name", "description").
                minimumShouldMatch("50%"));

        printResult(request, sourceBuilder);
    }

    @Test
    public void testUpdateDoc2() throws IOException {
        UpdateRequest request = new UpdateRequest("xc_course", "doc", "1");
        Map<String, Object> docMap = new HashMap<>();
        docMap.put("description", "Bootstrap是由Twitter推出的一个css前台页面开发框架，是一个非常流行的css开发框架，此框架集成了多种css页面效果。此开发框架包含了大量的CSS、JS程序代码，可以帮助css开发者（尤其是不擅长css页面开发的程序人员）轻松的实现一个不受浏览器限制的精美界面效果。");
        request.doc(docMap);
        UpdateResponse response = restHighLevelClient.update(request);
        System.out.println(response);
        testFindById();
    }

    /**
     * boost权重
     */
    @Test
    public void testMultiMatchQuery2() {
        SearchRequest request = new SearchRequest("xc_course");
        request.types("doc");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("spring css", "name", "description").minimumShouldMatch("50%");
        multiMatchQueryBuilder.field("name", 10);
        sourceBuilder.query(multiMatchQueryBuilder);

        printResult(request, sourceBuilder);
    }

    @Test
    public void testBoolQuery() {
        SearchRequest request = new SearchRequest("xc_course");
        request.types("doc");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();    //query
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();        //query.bool

        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "spring");
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("开发框架", "name", "description");

        boolQueryBuilder.must(termQueryBuilder);        //query.bool.must
        boolQueryBuilder.must(multiMatchQueryBuilder);
        sourceBuilder.query(boolQueryBuilder);

        printResult(request, sourceBuilder);
    }

    /**
     * {
     * "query": {
     * "bool":{
     * "must":[
     * {
     * "term":{
     * "name":"开发"
     * }
     * }
     * ],
     * "must_not":[
     * {
     * "term":{
     * "name":"java"
     * }
     * }
     * ],
     * "should":[
     * {
     * "term":{
     * "name":"bootstrap"
     * }
     * },
     * {
     * "term":{
     * "name":"spring"
     * }
     * }
     * ]
     * }
     * },
     * "_source":["name"]
     * }
     */
    @Test
    public void testBoolQuery2() {
        SearchRequest request = new SearchRequest("xc_course");
        request.types("doc");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        boolQueryBuilder.must(QueryBuilders.termQuery("name", "开发"));
        boolQueryBuilder.mustNot(QueryBuilders.termQuery("name", "java"));
        boolQueryBuilder.should(QueryBuilders.termQuery("name", "spring"));
        boolQueryBuilder.should(QueryBuilders.termQuery("name", "bootstrap"));

        sourceBuilder.query(boolQueryBuilder);

        printResult(request, sourceBuilder);
    }

    /**
     * 过滤器——filter
     * 过滤是针对搜索的结果进行过滤，==过滤器主要判断的是文档是否匹配，不去计算和判断文档的匹配度得分==，
     * 所以==过滤器性能比查询要高，且方便缓存==，推荐尽量使用过滤器去实现查询或者过滤器和查询共同使用。
     * <p>
     * 过滤器仅能在布尔查询中使用。
     */
    @Test
    public void testBoolQuery3() {
        SearchRequest request = new SearchRequest("xc_course");
        request.types("doc");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        boolQueryBuilder.must(QueryBuilders.multiMatchQuery("spring框架", "name", "description"));
        boolQueryBuilder.filter(QueryBuilders.termQuery("studymodel", "201001"));
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(10).lte(100));

        sourceBuilder.query(boolQueryBuilder);

        printResult(request, sourceBuilder);
    }


    /**
     * 排序
     * 查询课程价格在10~100之间的，并按照价格升序排列，当价格相同时再按照时间戳降序排列
     */
    @Test
    public void testSort() {
        SearchRequest request = new SearchRequest("xc_course");
        request.types("doc");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(10).lte(100));

        sourceBuilder.sort("price", SortOrder.ASC);
        sourceBuilder.sort("timestamp", SortOrder.DESC);

        sourceBuilder.query(boolQueryBuilder);
        printResult(request, sourceBuilder);
    }

}