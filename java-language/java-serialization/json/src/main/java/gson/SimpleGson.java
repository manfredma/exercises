package gson;

import com.google.gson.Gson;

/**
 * @author manfred on 2022/5/23.
 */
public class SimpleGson {

    public static void main(String[] args) {
        Gson gson = new Gson();
        int i = gson.fromJson("100", int.class);              //100
        double d = gson.fromJson("\"99.99\"", double.class);  //99.99
        boolean b = gson.fromJson("true", boolean.class);     // true
        String str = gson.fromJson("String", String.class);   // String

        User user = new User("怪盗kidou",24);
        String jsonObject = gson.toJson(user); // {"name":"怪盗kidou","age":24}
        System.out.println(jsonObject);
    }
}
