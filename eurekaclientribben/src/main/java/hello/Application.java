package hello;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .web(false)
                .run(args);
    }

}

@Component
class FeignExample implements CommandLineRunner {

    @Autowired
    private BookmarkClient bookmarkClient;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println(this.bookmarkClient.getBookmarks("jlong"));
    }
}

@FeignClient("bookmark-service")
interface BookmarkClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}/bookmarks")
    String getBookmarks(@PathVariable("userId") String userId);
}