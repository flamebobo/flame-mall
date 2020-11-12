import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/11/12 16:02
 */
@EnableDiscoveryClient
@SpringBootApplication
@NacosPropertySource(dataId = "gateway" ,autoRefreshed = true)
public class MallGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallGatewayApplication.class, args);
    }

}
