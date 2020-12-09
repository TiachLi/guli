import com.atguigu.oss.controller.OssController;
import org.junit.Test;

public class TestJunit {
    @Test
    public void  test1(){
        OssController ossController =new OssController();
        ossController.deleteOssFile("http://guli-file.oss-cn-beijing.aliyuncs.com/cover/2019/03/13/d0086eb0-f2dc-45f7-bba1-744d95e5be0f.jpg");
    }
}
