package spring.custom.aspectj;

public class LoginServiceImpl implements LoginService {

    @UseAop
    public String login(String userName){
        System.out.println("正在登录");
        return "success";
    }
}