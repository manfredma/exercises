package jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author manfred on 2022/5/23.
 */
public class SimpleJacksonV2 {

    static ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
    public static void main(String[] args) throws Exception {
        main2();
    }

    private static void main1() throws JsonProcessingException {
        String mqjson = "[\n" +
                "  \"sc_ext_spu\",\n" +
                "  \"sc_category\",\n" +
                "  \"info_ext_spu_property_value\",\n" +
                "  \"sc_ext_csu_grid_search\",\n" +
                "  \"info_bu_sku_property_value\",\n" +
                "  \"sc_ext_bu_sku_prop\",\n" +
                "  \"sc_spu_extend\",\n" +
                "  \"sc_sku_sales_info_tag\",\n" +
                "  \"sc_sku_info_tag\",\n" +
                "  \"sc_spu_info\",\n" +
                "  \"info_property_value_base\",\n" +
                "  \"sc_spu_sales_media_info\",\n" +
                "  \"sc_spu_media_info\",\n" +
                "  \"brand_show_info\",\n" +
                "  \"sc_ext_sku_sales\",\n" +
                "  \"sc_process_extend_info\",\n" +
                "  \"sc_csu_info\",\n" +
                "  \"sc_sku_sales_category_cache\",\n" +
                "  \"sc_ext_csu_grid_onshelf_info\",\n" +
                "  \"sc_sku_biz_unit\",\n" +
                "  \"info_ext_bu_spu_property_value\",\n" +
                "  \"sc_sales_category_template_grid\",\n" +
                "  \"sc_sku_sales_info\",\n" +
                "  \"sc_spu_grid_onshelf_info\",\n" +
                "  \"sc_sku_sales_media_info\",\n" +
                "  \"sc_sku_settlement_config\",\n" +
                "  \"info_spu_property_value\",\n" +
                "  \"sc_ext_sku\",\n" +
                "  \"sc_csu_grid_onshelf_info\",\n" +
                "  \"info_property_meta\",\n" +
                "  \"info_sku_property_value\",\n" +
                "  \"sc_sku_process_config\",\n" +
                "  \"sc_same_sku_relation\",\n" +
                "  \"sc_sku_inner_unit\",\n" +
                "  \"sc_ext_csu\",\n" +
                "  \"sc_ext_category_property\",\n" +
                "  \"info_category_property_value\",\n" +
                "  \"sc_sales_spu\",\n" +
                "  \"info_ext_sku_property_value\",\n" +
                "  \"sc_sales_category\",\n" +
                "  \"sc_ext_sku_biz_unit\",\n" +
                "  \"sc_sku_info\",\n" +
                "  \"cspu_sku_mapping\",\n" +
                "  \"sc_tag_value\",\n" +
                "  \"sc_tag_meta\",\n" +
                "  \"info_category_property_meta\",\n" +
                "  \"sc_ext_bu_csu_tag\",\n" +
                "  \"sc_ext_bu_sku_media\",\n" +
                "  \"sc_media_image_info\",\n" +
                "  \"info_bu_spu_property_value\",\n" +
                "  \"sc_sku_media_info\",\n" +
                "  \"sc_sku_sales_live_shot_media\",\n" +
                "  \"info_ext_bu_sku_property_value\",\n" +
                "  \"sc_sku_wrappage_mapping\",\n" +
                "  \"sc_sku_barcode\",\n" +
                "  \"sc_csu_sales_info\",\n" +
                "  \"sc_same_brand\",\n" +
                "  \"sc_ext_csu_sales\",\n" +
                "  \"sc_goods_set_csu\",\n" +
                "  \"sc_media_video_info\"\n" +
                "]";

        String thriftjson = "[\n" +
                "  \"sc_csu_grid_onshelf_info\",\n" +
                "  \"info_property_meta\",\n" +
                "  \"sc_ext_spu\",\n" +
                "  \"sc_category\",\n" +
                "  \"sc_ext_csu_grid_search\",\n" +
                "  \"info_ext_spu_property_value\",\n" +
                "  \"info_sku_property_value\",\n" +
                "  \"info_bu_sku_property_value\",\n" +
                "  \"sc_ext_bu_sku_prop\",\n" +
                "  \"sc_sku_process_config\",\n" +
                "  \"sc_sku_inner_unit\",\n" +
                "  \"sc_sku_sales_info_tag\",\n" +
                "  \"sc_sku_info_tag\",\n" +
                "  \"sc_spu_info\",\n" +
                "  \"info_category_property_value\",\n" +
                "  \"sc_ext_category_property\",\n" +
                "  \"info_property_value_base\",\n" +
                "  \"info_ext_sku_property_value\",\n" +
                "  \"sc_sales_spu\",\n" +
                "  \"sc_spu_media_info\",\n" +
                "  \"sc_sku_info\",\n" +
                "  \"cspu_sku_mapping\",\n" +
                "  \"sc_ext_bu_csu_tag\",\n" +
                "  \"info_category_property_meta\",\n" +
                "  \"sc_ext_sku_sales\",\n" +
                "  \"sc_process_extend_info\",\n" +
                "  \"sc_ext_bu_sku_media\",\n" +
                "  \"sc_media_image_info\",\n" +
                "  \"info_bu_spu_property_value\",\n" +
                "  \"sc_sku_media_info\",\n" +
                "  \"sc_csu_info\",\n" +
                "  \"sc_sku_sales_category_cache\",\n" +
                "  \"sc_sku_sales_live_shot_media\",\n" +
                "  \"sc_ext_csu_grid_onshelf_info\",\n" +
                "  \"info_ext_bu_sku_property_value\",\n" +
                "  \"sc_sku_biz_unit\",\n" +
                "  \"info_ext_bu_spu_property_value\",\n" +
                "  \"sc_sku_sales_info\",\n" +
                "  \"sc_sku_barcode\",\n" +
                "  \"sc_csu_sales_info\",\n" +
                "  \"sc_sku_sales_media_info\",\n" +
                "  \"sc_spu_grid_onshelf_info\",\n" +
                "  \"sc_ext_csu_sales\",\n" +
                "  \"sc_media_video_info\",\n" +
                "  \"sc_goods_set_csu\",\n" +
                "  \"sc_sku_settlement_config\",\n" +
                "  \"info_spu_property_value\",\n" +
                "  \"sc_ext_sku\"\n" +
                "]";

        List<String> mq = objectMapper.readValue(mqjson, new TypeReference<List<String>>() {
        });
        List<String> thrift = objectMapper.readValue(thriftjson, new TypeReference<List<String>>() {
        });

        System.out.println("====== MQ 特有的 =====");
        mq.stream().filter(a -> !thrift.contains(a))
                .forEach(System.out::println);
        System.out.println("====== thrift 特有的 =====");
        thrift.stream().filter(a -> !mq.contains(a))
                .forEach(System.out::println);
    }

    public static void main2() throws JsonProcessingException {
        String bujson = "[\n" +
                "    11000011,\n" +
                "    11000129,\n" +
                "    11029847,\n" +
                "    11000027,\n" +
                "    11000128,\n" +
                "    11000019,\n" +
                "    11000142,\n" +
                "    11000162,\n" +
                "    120100,\n" +
                "    11000008,\n" +
                "    11000178,\n" +
                "    11000045,\n" +
                "    11000043,\n" +
                "    11000010,\n" +
                "    310100,\n" +
                "    110100,\n" +
                "    11000177,\n" +
                "    11000125,\n" +
                "    11000124,\n" +
                "    11000088,\n" +
                "    11033402,\n" +
                "    11000040,\n" +
                "    11000009,\n" +
                "    11067524,\n" +
                "    11000012,\n" +
                "    11000141,\n" +
                "    11000037,\n" +
                "    11000170,\n" +
                "    11000017,\n" +
                "    11039831,\n" +
                "    340100,\n" +
                "    11000006,\n" +
                "    11000039,\n" +
                "    11000041,\n" +
                "    11024547,\n" +
                "    11000167,\n" +
                "    11034090,\n" +
                "    11000028,\n" +
                "    11043324,\n" +
                "    11000032,\n" +
                "    11058385,\n" +
                "    11000003,\n" +
                "    11000020,\n" +
                "    11000018,\n" +
                "    11000044,\n" +
                "    11061937,\n" +
                "    11063642,\n" +
                "    11000046,\n" +
                "    11000111,\n" +
                "    11000116,\n" +
                "    11000184,\n" +
                "    11030420,\n" +
                "    11000126,\n" +
                "    11000031,\n" +
                "    11000016,\n" +
                "    11000033,\n" +
                "    11058885,\n" +
                "    11000130,\n" +
                "    11072605,\n" +
                "    11000024,\n" +
                "    11063641,\n" +
                "    11024548,\n" +
                "    11000186,\n" +
                "    11000047,\n" +
                "    11046531,\n" +
                "    11040083,\n" +
                "    11000073,\n" +
                "    11029846,\n" +
                "    11000158,\n" +
                "    11031364,\n" +
                "    11057610,\n" +
                "    11000021,\n" +
                "    11051692,\n" +
                "    11059746,\n" +
                "    11056070,\n" +
                "    11067522,\n" +
                "    11039885,\n" +
                "    11059743,\n" +
                "    11030419,\n" +
                "    11000075,\n" +
                "    11000154,\n" +
                "    11059745,\n" +
                "    11059742,\n" +
                "    11061938,\n" +
                "    11000042,\n" +
                "    11066486,\n" +
                "    11000169,\n" +
                "    11000166,\n" +
                "    11000168,\n" +
                "    11067523,\n" +
                "    11000151,\n" +
                "    376600,\n" +
                "    11053308,\n" +
                "    11059744,\n" +
                "    11072604,\n" +
                "    11000217,\n" +
                "    11000200,\n" +
                "    11000139,\n" +
                "    11000216,\n" +
                "    11000201,\n" +
                "    11000138,\n" +
                "    11000174,\n" +
                "    11000205,\n" +
                "    11000179,\n" +
                "    11000165,\n" +
                "    11000185,\n" +
                "    11000161,\n" +
                "    11000133,\n" +
                "    11000160,\n" +
                "    11000215,\n" +
                "    11002954,\n" +
                "    11000188,\n" +
                "    11000192,\n" +
                "    11000146,\n" +
                "    11000187,\n" +
                "    11000155,\n" +
                "    11076035,\n" +
                "    11000135,\n" +
                "    11000163,\n" +
                "    11076033,\n" +
                "    11000190,\n" +
                "    11000191,\n" +
                "    11000207,\n" +
                "    11000152,\n" +
                "    11000149,\n" +
                "    11000175,\n" +
                "    11076983,\n" +
                "    11000203,\n" +
                "    11000213,\n" +
                "    11000153,\n" +
                "    11000210,\n" +
                "    11000189,\n" +
                "    11002915,\n" +
                "    11076034,\n" +
                "    11000195,\n" +
                "    11000172,\n" +
                "    11000156,\n" +
                "    11000211,\n" +
                "    11000157,\n" +
                "    11000212,\n" +
                "    11000214,\n" +
                "    11000143,\n" +
                "    11000171,\n" +
                "    11000196,\n" +
                "    11000137,\n" +
                "    11000150,\n" +
                "    11000159,\n" +
                "    11000164,\n" +
                "    11000209,\n" +
                "    11000134,\n" +
                "    11000173,\n" +
                "    11000183,\n" +
                "    11000148,\n" +
                "    11000198,\n" +
                "    11000144,\n" +
                "    11000176,\n" +
                "    11000208,\n" +
                "    11000193,\n" +
                "    11000180,\n" +
                "    11000206,\n" +
                "    11000136,\n" +
                "    11000194,\n" +
                "    11000147,\n" +
                "    11000132,\n" +
                "    11002961,\n" +
                "    11000204,\n" +
                "    11000182,\n" +
                "    11000140,\n" +
                "    11000181,\n" +
                "    11000197,\n" +
                "    11000145,\n" +
                "    11000202,\n" +
                "    11000199,\n" +
                "    710400]";

        List<Integer> bu1 = objectMapper.readValue(bujson, new TypeReference<List<Integer>>() {
        });

        String bujson2 = "[376600,11000075,110100,11000027,11000012,11046531,11000046,11000009,11000126,11000162,310100,11000028,11000041,710400,11000033,11000037,11000044,11000011,11000031,11000003,11000017,11000167,11000040,11034090,11000184,11000170,11000039,11000111,11000018,11000073,11000016,11000129,11000006,11000010,11000186,340100,11000008,11000128,11000043,11000142,120100,11000020,11000088,11000019,11000130,11000047,11000124,11000032,11000177,11000021,11000116,11024547,11000125,11000042,11000141,11000169,11059746,11000024,11000045,11029846,11000178,11059744,11024548,11031364,11030420,11029847,11059745,11059743,11051692,11033402,11030419,11057610,11072604,11058885,11000205,11067522,11067523,11000158,11000154,11072605,11067524,11043324,11056070,11058385,11039885,11066486,11059742,11000200,11000166,11000165,11000168,11000174,11000217,11000160,11000216,11076034,11063641,11000151,11000185,11000201,11000139,11000161,11000179,11063642,11039831,11061937,11040083,11061938,11076035,11053308,11000163,11000133,11002954,11076033,11000153,11000175,11000132,11000147,11000157,11000211,11000176,11000214,11000209,11000208,11000199,11000189,11000156,11000203,11000196,11000144,11000145,11000188,11000197,11000135,11000159,11000193,11000192,11000173,11000137,11000215,11000204,11000143,11000202,11002915,11000190,11000140,11000152,11000146,11000150,11000136,11000149,11000181,11000134,11000207,11000138,11000171,11000183,11000212,11000172,11002961,11000155,11000180,11000194,11000182,11000210,11000148,11000164,11000213,11000198,11000195,11000191,11000187,11000206,11076983]";

        List<Integer> bu2 = objectMapper.readValue(bujson2, new TypeReference<List<Integer>>() {
        });
        ArrayList<Integer> integers = new ArrayList<>(bu1);
        integers.removeAll(bu2);
        System.out.println(integers);
        ArrayList<Integer> integers1 = new ArrayList<>(bu2);
        integers1.removeAll(bu1);
        System.out.println(integers1);
    }

}
