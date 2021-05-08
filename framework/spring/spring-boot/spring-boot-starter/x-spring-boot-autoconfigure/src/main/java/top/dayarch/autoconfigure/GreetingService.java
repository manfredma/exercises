package top.dayarch.autoconfigure;


import java.util.ArrayList;
import java.util.List;

public class GreetingService {

    private List<String> members = new ArrayList<>();

    public GreetingService(List<String> members) {
        this.members = members;
    }

    public void sayHello() {
        members.forEach(s -> System.out.println("hello " + s));
    }
}