package manfred.end;

import java.util.Arrays;

public class CreatorTable2Puml {
    public static void main(String[] args) {
        String s = "CREATE TABLE `sc_media_video_info` (\n" +
                "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                "  `video_url` varchar(300) NOT NULL DEFAULT '' COMMENT '视频URL',\n" +
                "  `channel_id` int(11) NOT NULL DEFAULT '0' COMMENT 'channelId',\n" +
                "  `istatus` smallint(4) NOT NULL DEFAULT '1' COMMENT '逻辑删除:0删除,1有效',\n" +
                "  `create_time` datetime DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',\n" +
                "  `create_by` varchar(45) NOT NULL DEFAULT '' COMMENT '创建者',\n" +
                "  `last_modify_time` datetime DEFAULT '1970-01-01 00:00:00' COMMENT '修改时间',\n" +
                "  `last_modify_by` varchar(45) NOT NULL DEFAULT '' COMMENT '修改者',\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  KEY `idx_video_url` (`video_url`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=16117 DEFAULT CHARSET=utf8mb4 COMMENT='视频表'";

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
