package cn.felord.r2dbc.config;
import lombok.Data;
/**
 * @author felord.cn
 */
@Data
public class ClientUser {
    private String userId;
    private String username;
    private String phoneNumber;
    private Integer gender;
}