import java.util.HashMap;

/**
 * @author DengYuan2
 * @create 2020-11-10 19:24
 */
public class Test {
    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("1","11");
        map.put("2","22");
        System.out.println(map.get("3"));
    }
}
