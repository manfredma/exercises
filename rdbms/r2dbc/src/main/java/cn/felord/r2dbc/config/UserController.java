package cn.felord.r2dbc.config;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;

/**
 * The type User controller.
 *
 * @author felord.cn
 * @since 17 :07
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private DatabaseClient databaseClient;

    /**
     * 查询
     *
     * @return 返回Flux序列 包含所有的ClientUser
     */
    @GetMapping("/get")
    public Flux clientUserFlux() {
        return databaseClient.sql("select * from client_user")
                .fetch()
                .all();
    }
}