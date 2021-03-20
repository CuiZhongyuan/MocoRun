import com.api.util.TplKit;
import org.testng.annotations.Test;

public class RandomTest {

    @Test
    public void randomCase(){
//        随机函数测试
        String str = "中原Random(Char[5])";
        String body = TplKit.parseStringByReplaceCache(str);
        System.out.println(body);

    }

}
