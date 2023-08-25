package manfred.end;

import java.util.Arrays;

public class CreatorTable2Puml {
    public static void main(String[] args) {
        String s = "CREATE TABLE `sc_sku_bu_property_value_draft` (\n" +
                "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                "  `task_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '类目管理任务Id',\n" +
                "  `sku_code` int(11) NOT NULL DEFAULT '0' COMMENT 'SKU编码',\n" +
                "  `sku_name` varchar(255) NOT NULL DEFAULT '' COMMENT 'SKU名称',\n" +
                "  `spu_code` varchar(30) NOT NULL DEFAULT '' COMMENT 'SPU编码',\n" +
                "  `bu_id` int(11) NOT NULL DEFAULT '0' COMMENT '事业部编码',\n" +
                "  `bu_name` varchar(255) NOT NULL DEFAULT '' COMMENT '事业部名称',\n" +
                "  `property_info` text COMMENT '商品SKU事业部属性信息json',\n" +
                "  `property_integrity` smallint(4) NOT NULL DEFAULT '0' COMMENT '属性值完整性:0不完整,1完整',\n" +
                "  `channel_id` int(11) NOT NULL DEFAULT '0' COMMENT '渠道',\n" +
                "  `istatus` smallint(4) NOT NULL DEFAULT '1' COMMENT '逻辑删除, 1正常, 0已删除',\n" +
                "  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',\n" +
                "  `create_by` varchar(60) NOT NULL DEFAULT '' COMMENT '创建人',\n" +
                "  `last_modify_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '最后修改时间',\n" +
                "  `last_modify_by` varchar(60) NOT NULL DEFAULT '' COMMENT '最后修改人',\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  KEY `idx_sc_sku_bu_property_value_draft_task_id_sku_code_bu_id` (`task_id`,`sku_code`,`bu_id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=33724 DEFAULT CHARSET=utf8mb4 COMMENT='SKU事业部属性值信息草稿表'";

        String[] lines = s.split("\\r?\\n");
        String tableName = lines[0].substring(lines[0].indexOf("`") + 1, lines[0].lastIndexOf("`"));
        String simpleTableName = Arrays.stream(tableName.split("_")).map(name -> name.substring(0
                , 1)).reduce("", (x, y) ->
                x + "_" + y);
        StringBuilder result = new StringBuilder();
        String indent = "    ";
        result.append("entity \"").
                append(s.split("COMMENT='")[1].replace("'", ""))
                .append(" \\n ")
                .append(tableName)
                .append("\" as ")
                .append(simpleTableName)
                .append(" {\n");
        for (int i = 0; i < lines.length; i++) {
            String trim = lines[i].trim();
            if (trim.startsWith("`") && trim.contains("'")) {
                String[] strings = trim.split("'");
                String[] strings2 = trim.split(" ");
                result.append(indent)
                        .append(strings[strings.length - 2])
                        .append(" : ")
                        .append(strings2[1])
                        .append("\n");
            }
        }
        result.append("}");
        System.out.println(result);

    }
}
