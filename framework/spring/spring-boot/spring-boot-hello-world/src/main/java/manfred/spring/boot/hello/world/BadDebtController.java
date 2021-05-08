package manfred.spring.boot.hello.world;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * @author manfred
 */
@RestController
@RequestMapping("/baddebt")
public class BadDebtController {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://10.96.81.212:3306/baddebt?useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8&useSSL=false&allowPublicKeyRetrieval=true";
    static final String USER = "root";
    static final String PASS = "123456";

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("初始化失败");
        }
    }

    @RequestMapping(
            path = "/create",
            method = RequestMethod.POST
    )
    public String create(@ModelAttribute CreateDto createDto) {
        System.out.println("收到请求 " + createDto);
        try (Connection connection = createConn();
             Statement statement = connection.createStatement()) {
            if (createDto.uid == null || "".equals(createDto.uid)) {
                return "uid cannot ben null";
            }

            if ("1".equals(createDto.getOrderType()) || "2".equals(createDto.getOrderType())) {
                if ((createDto.memberUid == null || "".equals(createDto.memberUid))) {
                    return "1".equals(createDto.getOrderType()) ? "亲密付订单，memberUid 不能为空" : "团队付订单，memberUid 不能为空";
                }
            }

            if (createDto.appId == null || "".equals(createDto.appId)) {
                return "appId cannot ben null";
            }

            if (createDto.orderId == null || "".equals(createDto.orderId)) {
                return "orderId cannot ben null";
            }


            Map<String, Object> param = new HashMap<>();
            param.put("uid", "'" + createDto.getUid() + "'");
            param.put("order_type", "'" + createDto.getOrderType() + "'");
            param.put("order_id", "'" + createDto.getOrderId() + "'");
            param.put("app_id", "'" + createDto.getAppId() + "'");
            String totalFee = createDto.getTotalFee() == null || "".equals(createDto.getTotalFee()) ? String.valueOf(20) : createDto.getTotalFee();
            param.put("total_fee", "'" + totalFee + "'");
            if ("1".equals(createDto.getOrderType()) || "2".equals(createDto.getOrderType())) {
                param.put("member_uid", "'" + createDto.getMemberUid() + "'");
                param.put("leader_uid", "'" + createDto.getUid() + "'");
            }
            param.put("district", "'010'");

            String paramName = param.keySet().toString().substring(1, param.keySet().toString().length() - 1);
            String paramValue = param.values().toString().substring(1, param.values().toString().length() - 1);
            String sql = "insert ignore into baddebt_328(" + paramName + ") values(" + paramValue + ")";
            System.out.println("sql = " + sql);
            int ur = statement.executeUpdate(sql);
            if (ur == 0) {
                return "请检查数据是否重复!!!!!!!!!";
            }

            if ("1".equals(createDto.getOrderType()) || "2".equals(createDto.getOrderType())) {
                param.put("uid", "'" + createDto.getMemberUid() + "'");
                paramName = param.keySet().toString().substring(1, param.keySet().toString().length() - 1);
                paramValue = param.values().toString().substring(1, param.values().toString().length() - 1);
                sql = "insert ignore into baddebt_328(" + paramName + ") values( + " + paramValue + ")";
                System.out.println("插入队员的亲情付订单 sql = " + sql);
                statement.executeUpdate(sql);
            }


        } catch (Exception e) {
            return "出现了异常, " + e;
        }
        return "恭喜你，成功了！";
    }


    @RequestMapping(path = "/updatePayStatus", method = {RequestMethod.GET, RequestMethod.POST})
    public String update(@ModelAttribute UpdateDto updateDto) {
        try (Connection connection = createConn();
             Statement statement = connection.createStatement()) {
            if (updateDto.orderId == null || "".equals(updateDto.orderId)) {
                return "orderId cannot ben null";
            }

            if (updateDto.appId == null || "".equals(updateDto.appId)) {
                return "appId cannot ben null";
            }

            if (updateDto.payStatus == null || "".equals(updateDto.payStatus)) {
                return "orderId cannot ben null";
            }


            String sql = "update baddebt_328 set pay_status = '" + updateDto.getPayStatus() + "' where order_id = '" + updateDto.getOrderId() + "' and app_id = '" + updateDto.getAppId() + "'";
            System.out.println("sql = " + sql);
            int ur = statement.executeUpdate(sql);
            if (ur == 0) {
                return "请检查参数，没有匹配到待更新的记录";
            }
        } catch (Exception e) {
            return "出现了异常, " + e;
        }
        return "恭喜你，成功了！";
    }

    @RequestMapping(path = "/createTable", method = {RequestMethod.GET, RequestMethod.POST})
    public void createTable() {
        for (int i = 0; i < 1000; i++) {
            if (i == 328) {
                continue;
            }
            System.out.println("当前执行到 " + i + " 表");
            String sql1 = "DROP TABLE IF EXISTS `baddebt_" + i + "`;";

            String sql = "CREATE TABLE `baddebt_" + i + "` (\n" +
                    "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'è‡ªå¢žid',\n" +
                    "  `order_id` varchar(32) NOT NULL DEFAULT '' COMMENT 'ä¸šåŠ¡è®¢å\u008D•id',\n" +
                    "  `district` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'åŸŽå¸‚åŒºå\u008F·',\n" +
                    "  `app_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'äº§å“\u0081çº¿id',\n" +
                    "  `uid` varchar(32) NOT NULL DEFAULT '' COMMENT 'ä»˜æ¬¾æ–¹å®¢æˆ·id',\n" +
                    "  `total_fee` int(10) NOT NULL DEFAULT '0' COMMENT 'è®¢å\u008D•æ”¯ä»˜æ€»é‡‘é¢\u009D(åˆ†)',\n" +
                    "  `pay_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'æ”¯ä»˜çŠ¶æ€\u0081',\n" +
                    "  `currency` varchar(30) NOT NULL DEFAULT 'cny' COMMENT 'å¸\u0081ç§\u008D',\n" +
                    "  `create_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT 'å\u009D\u008Fè´¦ç”Ÿæˆ\u0090æ—¶é—´',\n" +
                    "  `update_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT 'å\u009D\u008Fè´¦æ›´æ–°æ—¶é—´',\n" +
                    "  `pay_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT 'å\u009D\u008Fè´¦æ”¯ä»˜æ—¶é—´',\n" +
                    "  `debt_create_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT 'ä¸šåŠ¡è´¦å\u008D•ç”Ÿæˆ\u0090æ—¶é—´',\n" +
                    "  `freeze_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '冻结状态',\n" +
                    "  `member_uid` varchar(32) NOT NULL DEFAULT '' COMMENT '团队成员的用户 uid',\n" +
                    "  `leader_uid` varchar(32) NOT NULL DEFAULT '' COMMENT '团队队长的用户 uid',\n" +
                    "  `yn` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否有效',\n" +
                    "  `remark` varchar(512) NOT NULL DEFAULT '' COMMENT '备注',\n" +
                    "  `order_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '订单类型：0普通订单，1亲友代付订单，2团队付订单，默认0',\n" +
                    "  `freeze_status_update_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '更新时间',\n" +
                    "                    PRIMARY KEY (`id`),\n" +
                    "                    UNIQUE KEY `uniq_order_appid` (`order_id`,`app_id`, `uid`),\n" +
                    "            KEY `idx_uid_appid` (`uid`,`app_id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='坏账总表';";
            try (Connection connection = createConn();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql1);
                int ur = statement.executeUpdate(sql);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Connection createConn() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            throw new RuntimeException("创建数据库连接失败", e);
        }
    }


    public static class UpdateDto {

        private String orderId;

        private String payStatus;

        private String appId;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(String payStatus) {
            this.payStatus = payStatus;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        @Override
        public String toString() {
            return "UpdateDto{" +
                    "orderId='" + orderId + '\'' +
                    ", payStatus='" + payStatus + '\'' +
                    ", appId='" + appId + '\'' +
                    '}';
        }
    }


    public static class CreateDto {

        private String uid;

        private String orderType;

        private String memberUid;

        private String orderId;

        private String payStatus;

        private String appId;

        private String totalFee;

        public String getTotalFee() {
            return totalFee;
        }

        public void setTotalFee(String totalFee) {
            this.totalFee = totalFee;
        }


        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getMemberUid() {
            return memberUid;
        }

        public void setMemberUid(String memberUid) {
            this.memberUid = memberUid;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(String payStatus) {
            this.payStatus = payStatus;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        @Override
        public String toString() {
            return "CreateDto{" +
                    "uid='" + uid + '\'' +
                    ", orderType='" + orderType + '\'' +
                    ", memberUid='" + memberUid + '\'' +
                    ", orderId='" + orderId + '\'' +
                    ", payStatus='" + payStatus + '\'' +
                    ", appId='" + appId + '\'' +
                    ", totalFee='" + totalFee + '\'' +
                    '}';
        }
    }


}