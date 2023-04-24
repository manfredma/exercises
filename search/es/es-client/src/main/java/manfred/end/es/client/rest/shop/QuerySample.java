package manfred.end.es.client.rest.shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.ClearScrollResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.geo.ShapeRelation;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.geometry.Circle;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.GeoBoundingBoxQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.GeoPolygonQueryBuilder;
import org.elasticsearch.index.query.GeoShapeQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.PrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.RegexpQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

public class QuerySample {

    public static void main(String[] args) throws Exception {
        try (RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")))) {
            System.out.println(
                    "_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_" +
                            "+_+_+_+_+_+_+_+_+_+_+_+_+_+_+");
            System.out.println("termsQuery");
            termsQuery(client);
            System.out.println(
                    "_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_" +
                            "+_+_+_+_+_+_+_+_+_+_+_+_+_+_+");
            System.out.println("geoQuery");
            geoQuery(client);

            System.out.println(
                    "_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_" +
                            "+_+_+_+_+_+_+_+_+_+_+_+_+_+_+");
            System.out.println("geoPolygon");
            geoPolygon(client);
            System.out.println(
                    "_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_" +
                            "+_+_+_+_+_+_+_+_+_+_+_+_+_+_+");
            System.out.println("geoBounding");
            geoBounding(client);
            System.out.println(
                    "_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_" +
                            "+_+_+_+_+_+_+_+_+_+_+_+_+_+_+");
            System.out.println("geoShape");
            // geoShape(client);

            System.out.println(
                    "_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_" +
                            "+_+_+_+_+_+_+_+_+_+_+_+_+_+_+");
            System.out.println("prefixQuery");
            prefixQuery(client);

            System.out.println(
                    "_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_" +
                            "+_+_+_+_+_+_+_+_+_+_+_+_+_+_+");
            System.out.println("fuzzyQuery");
            fuzzyQuery(client);

            System.out.println(
                    "_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_" +
                            "+_+_+_+_+_+_+_+_+_+_+_+_+_+_+");
            System.out.println("wildCardQuery");
            wildCardQuery(client);

            System.out.println(
                    "_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_" +
                            "+_+_+_+_+_+_+_+_+_+_+_+_+_+_+");
            System.out.println("regexpQuery");
            regexpQuery(client);

            System.out.println(
                    "_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_" +
                            "+_+_+_+_+_+_+_+_+_+_+_+_+_+_+");
            System.out.println("rangeQuery");
            rangeQuery(client);

            System.out.println(
                    "_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_" +
                            "+_+_+_+_+_+_+_+_+_+_+_+_+_+_+");
            System.out.println("scrollQuery");
            scrollQuery(client);

            System.out.println(
                    "_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_" +
                            "+_+_+_+_+_+_+_+_+_+_+_+_+_+_+");
            System.out.println("metricAggQuery");
            metricAggQuery(client);


        }

    }



    private static void scrollQuery(RestHighLevelClient client) throws Exception {

        SearchRequest request = new SearchRequest("shop");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        //构造query
        MatchAllQueryBuilder matchQuery = QueryBuilders.matchAllQuery();
        builder.query(matchQuery);
        builder.size(2);//设置每次scroll获取2个记录
        request.source(builder);
        //设置scrollid缓存的时间
        request.scroll(new Scroll(TimeValue.MINUS_ONE));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //对第一次获取的结果进行处理
        System.out.println("First >> ");
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println("hit:" + hit);
        }
        SearchScrollRequest scrollRequest = null;
        //对余下的数据进行游标遍历处理
        do {
            String scrollId = response.getScrollId();
            scrollRequest = new SearchScrollRequest(scrollId);
            //设置此次的保留时间，如果不设置，scrollid可能被清理后，后端报No search context found for id
            scrollRequest.scroll(new Scroll(TimeValue.MINUS_ONE));
            response = client.scroll(scrollRequest, RequestOptions.DEFAULT);
            System.out.println("scrollId >> " + scrollId);
            for (SearchHit hit : response.getHits().getHits()) {
                System.out.println("hit:" + hit);
            }
        } while (response.getHits().getHits().length != 0);//等到最后获取的结果数为0，即表示所有的结果全部遍历完成
        //scroll完成后，需要立即将缓存清除
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(response.getScrollId());
        ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest,
                RequestOptions.DEFAULT);
        System.out.println("response:" + clearScrollResponse.toString());
    }

    public static void metricAggQuery(RestHighLevelClient client) throws IOException {
        SearchRequest request = new SearchRequest("shop");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        MatchAllQueryBuilder matchQuery = QueryBuilders.matchAllQuery();
        builder.query(matchQuery);
        //计算平均值
        AggregationBuilder avgBuilder = AggregationBuilders.avg("avg").field("shopid");
        //求和
        AggregationBuilder sumBuilder = AggregationBuilders.sum("SUM").field("shopid");
        //最小值
        AggregationBuilder minBuilder = AggregationBuilders.min("Minprice").field("shopid");
        //最大值
        AggregationBuilder maxBuilder = AggregationBuilders.max("Max").field("shopid");
        //域字段的uniq统计数量
        AggregationBuilder cardinality = AggregationBuilders.cardinality("cardinality").field(
                "shopid");
        //对域字段进行全局统计，包括最大值，最小值，数量，和，平均值
        AggregationBuilder stats = AggregationBuilders.stats("stats").field("shopid");
        //对指定字段的值按从小到大累计每个值对应的文档数的占比，返回指定占比比例对应的值
        AggregationBuilder percentiles = AggregationBuilders.percentiles("percentiles").field(
                "shopid");
        //这里通过文档值求域字段百分比
        AggregationBuilder percentileRank = AggregationBuilders.percentileRanks("percentileRank",
                new double[]{1002, 1003}).field("shopid");
        builder.aggregation(avgBuilder).aggregation(sumBuilder).aggregation(minBuilder).aggregation(maxBuilder);
        builder.aggregation(cardinality).aggregation(stats).aggregation(percentileRank).aggregation(percentiles);
        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println("response:" + response.toString());
    }

    private static void rangeQuery(RestHighLevelClient client) throws Exception {
        /*
        #http请求
curl -X Get /shop/_doc
{"from":0,"size":10,"query":{"range":{"avgprice":{"from":20,"to":90,"include_lower":true,
"include_upper":false,"boost":1.0}}}}
         */
        SearchRequest request = new SearchRequest("shop");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        RangeQueryBuilder rangeQueryBuilder =
                QueryBuilders.rangeQuery("avgprice").from(20, true).to(90, false);
        builder.query(rangeQueryBuilder);
        request.source(builder);
        builder.from(0);
        builder.size(10);

        System.out.println("request:" + request);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println("response:" + response);
    }

    private static void regexpQuery(RestHighLevelClient client) throws Exception {
        /*
        #http请求
curl -X Get /shop/_doc
{"from":0,"size":10,"query":{"regexp":{"address":{"value":"金沙江.*","flags_value":65535,
"max_determinized_states":10000,"boost":1.0}}}}
         */
        SearchRequest request = new SearchRequest("shop");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        RegexpQueryBuilder regexpQuery = QueryBuilders.regexpQuery("address", "金沙江.*");
        builder.query(regexpQuery);
        request.source(builder);
        builder.from(0);
        builder.size(10);

        System.out.println("request:" + request);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println("response:" + response);
    }

    private static void wildCardQuery(RestHighLevelClient client) throws Exception {
        /*
        #http请求
curl -X Get /shop/_doc
{"from":0,"size":10,"query":{"wildcard":{"address":{"wildcard":"金沙江*","boost":1.0}}}}
         */
        SearchRequest request = new SearchRequest("shop");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        WildcardQueryBuilder wildcardQuery = QueryBuilders.wildcardQuery("address", "金沙江*");
        builder.query(wildcardQuery);
        request.source(builder);
        builder.from(0);
        builder.size(10);
        System.out.println("request:" + request);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println("response:" + response);
    }

    private static void fuzzyQuery(RestHighLevelClient client) throws Exception {
        /*
        #http请求
curl -X Get /shop/_doc
{"from":0,"size":10,"query":{"prefix":{"address":{"value":"长宁","boost":1.0}}}}
         */
        SearchRequest request = new SearchRequest("shop");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        FuzzyQueryBuilder fuzzyQuery = QueryBuilders
                .fuzzyQuery("address", "长宁区北新泾四村")
                .fuzziness(Fuzziness.TWO)
                .maxExpansions(1)
                .transpositions(false);
        builder.query(fuzzyQuery);
        request.source(builder);
        builder.from(0);
        builder.size(10);
        System.out.println("request:" + request);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println("response:" + response);
    }

    private static void prefixQuery(RestHighLevelClient client) throws Exception {
        /*
        #http请求
curl -X Get /shop/_doc
{"from":0,"size":10,"query":{"prefix":{"address":{"value":"长宁","boost":1.0}}}}
         */
        SearchRequest request = new SearchRequest("shop");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        PrefixQueryBuilder prefixQueryBuilder = QueryBuilders.prefixQuery("address", "长宁");
        builder.query(prefixQueryBuilder);
        request.source(builder);
        builder.from(0);
        builder.size(10);
        System.out.println("request:" + request);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println("response:" + response);
    }

    private static void geoShape(RestHighLevelClient client) throws Exception {
        /*
        #http请求
curl -X Get /shop/_doc
{"from":0,"size":10,"query":{"geo_shape":{"shoppoiarea":{"shape":{"type":"Circle","radius":"10000
.0m","coordinates":[31.217789757037103,121.37543736424315]},"relation":"intersects"},
"ignore_unmapped":false,"boost":1.0}}}}
         */
        SearchRequest request = new SearchRequest("shop");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        List<GeoPoint> points = new ArrayList<>();
        //申亚时代广场
        GeoPoint point = new GeoPoint(31.217789757037103, 121.37543736424315);
        points.add(point);
        GeoShapeQueryBuilder geoShapeQueryBuilder = QueryBuilders.geoShapeQuery("shoppoi",
                new Circle(point.getLat(),
                        point.getLon(), 10000)).relation(ShapeRelation.CONTAINS);
        builder.query(geoShapeQueryBuilder);
        request.source(builder);
        builder.from(0);
        builder.size(10);
        System.out.println("request:" + request);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println("response:" + response);
    }

    private static void geoBounding(RestHighLevelClient client) throws IOException {
        /*
        #http请求
curl -X Get /shop/_doc
{"from":0,"size":10,"query":{"geo_bounding_box":{"shoppoi":{"top_left":[121.37543736424315,31
.217789757037103],"bottom_right":[121.36190169697494,31.194052749518814]},
"validation_method":"STRICT","type":"MEMORY","ignore_unmapped":false,"boost":1.0}}}
         */
        SearchRequest request = new SearchRequest("shop");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        //申亚时代广场
        GeoPoint topLeftPoint = new GeoPoint(31.217789757037103, 121.37543736424315);
        //上海动物园
        GeoPoint bottomRightPoint = new GeoPoint(31.194052749518814, 121.36190169697494);
        GeoBoundingBoxQueryBuilder geoBoundingBoxQuery = QueryBuilders.geoBoundingBoxQuery(
                "shoppoi").setCorners(topLeftPoint, bottomRightPoint);
        builder.query(geoBoundingBoxQuery);
        request.source(builder);
        builder.from(0);
        builder.size(10);
        System.out.println("request:" + request);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println("response:" + response);
    }

    private static void geoPolygon(RestHighLevelClient client) throws IOException {

        /*
        #http请求
curl -X Get /shop/_doc
{"from":0,"size":10,"query":{"geo_polygon":{"shoppoi":{"points":[[121.37543736424315,31
.217789757037103],[121.3519578527128,31.222859916942692],[121.36190169697494,31.194052749518814],
[121.37543736424315,31.217789757037103]]},"validation_method":"STRICT","ignore_unmapped":false,
"boost":1.0}}}
         */

        SearchRequest request = new SearchRequest("shop");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        //points至少3个点，否则报错，另外这三个点最好按照顺时针方向写入
        List<GeoPoint> points = new ArrayList<>();
        //申亚时代广场
        GeoPoint point = new GeoPoint(31.217789757037103, 121.37543736424315);
        points.add(point);
        //凌空soho
        point = new GeoPoint(31.222859916942692, 121.3519578527128);
        points.add(point);
        //上海动物园
        point = new GeoPoint(31.194052749518814, 121.36190169697494);
        points.add(point);
        GeoPolygonQueryBuilder geoPolygonQueryBuilder
                = QueryBuilders.geoPolygonQuery("shoppoi", points);
        builder.query(geoPolygonQueryBuilder);
        request.source(builder);
        builder.from(0);
        builder.size(10);
        System.out.println("request:" + request);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println("response:" + response);
    }

    private static void termsQuery(RestHighLevelClient client) throws IOException {
        /*
        curl -X Get /shop/_doc {"query":{"terms":{"cityid":[1,2,3]}}}
         */
        SearchRequest request = new SearchRequest("shop");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        int[] cityids = new int[]{1, 2, 3};
        TermsQueryBuilder termsQuery = QueryBuilders.termsQuery("cityid", cityids);
        builder.query(termsQuery);
        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println("结果为:" + response);
    }


    private static void geoQuery(RestHighLevelClient client) throws Exception {
        /*
        #http请求
curl -X Get /shop/_doc
{"from":0,"size":10,"query":{"bool":{"must":[{"term":{"shopname":{"value":"肯","boost":1.0}}},
{"geo_distance":{"shoppoi":[121.36507,31.210586547],"distance":100000.0,"distance_type":"arc",
"validation_method":"STRICT","ignore_unmapped":false,"boost":1.0}}],"adjust_pure_negative":true,
"boost":1.0}},"sort":[{"_geo_distance":{"shoppoi":[{"lat":31.210586547,"lon":121.36507}],
"unit":"m","distance_type":"arc","order":"asc","validation_method":"STRICT",
"ignore_unmapped":false}}]}
         */
        SearchRequest request = new SearchRequest("shop");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        //用户当前位置
        GeoPoint point = new GeoPoint(31.210586547, 121.36507);
        //查询肯德基，ES默认分词器为单字分词
        TermQueryBuilder query = QueryBuilders.termQuery("shopname", "肯");
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(query);
        //查询附近3公里的商户
        GeoDistanceQueryBuilder geoDistanceQueryBuilder = QueryBuilders.geoDistanceQuery("shoppoi").
                point(point).distance(3, DistanceUnit.KILOMETERS).geoDistance(GeoDistance.ARC);
        boolQueryBuilder.must(geoDistanceQueryBuilder);
        builder.query(boolQueryBuilder);
        request.source(builder);
        //按照距离排序
        GeoDistanceSortBuilder distanceSort = SortBuilders.geoDistanceSort("shoppoi", point);
        distanceSort.order(SortOrder.ASC);
        builder.sort(distanceSort);
        //取Top10的商户
        builder.from(0);
        builder.size(10);
        System.out.println("request:" + request);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println("response:" + response);
    }
}
