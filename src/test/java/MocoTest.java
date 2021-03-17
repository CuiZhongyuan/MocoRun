import com.api.util.JsonUtils;
import com.api.util.RestTemplateUtils;
import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Moco;
import com.github.dreamhead.moco.MocoJsonRunner;
import com.github.dreamhead.moco.Runner;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

;


public class MocoTest {

    @BeforeTest
    public void inintStartMoco(){

        String path = this.getClass().getClassLoader().getResource("config.json").getPath().substring(1);
        HttpServer mocoServer = MocoJsonRunner.jsonHttpServer(18080, Moco.file(path));
        Runner.runner(mocoServer).start();
        System.out.println("开启mock服务");
    }
    @Test
    public void testCase(){
        String apiPath = "http://localhost:18080/hr/regist/insertRegist";
        String response = JsonUtils.jsonFormatter( RestTemplateUtils.get(apiPath,String.class).getBody());
        System.out.println(response);
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(response);
        String author0 = JsonPath.read(document, "$.data[1].key4");
        System.out.println("取出当前结果："+author0);
    }

}
