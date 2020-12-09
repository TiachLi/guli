import com.atguigu.commonutils.R;
import com.atguigu.vodtest.TestVod;
import org.junit.jupiter.api.Test;

public class TestDemo {
    @Test
    public void Test1(){
        try {
            TestVod.getPlayAuth();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
