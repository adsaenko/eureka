package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;

@SpringBootApplication
@EnableEurekaClient
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return (args) -> {

            System.out.print("Test test");

        };
    }
}

@RestController
@RequestMapping("/{userId}/bookmarks")
class BookmarkRestController {

    @RequestMapping(method = RequestMethod.GET)
    String getBookmarks(@PathVariable String userId) {
        return "GET Call getBookmarks";
    }

    @RequestMapping(value = "/{bookmarkId}", method = RequestMethod.GET)
    String getBookmark(@PathVariable String userId,
                         @PathVariable Long bookmarkId) {
        return "GET Call getBookmark";
    }

    @RequestMapping(method = RequestMethod.POST)
    String createBookmark(@PathVariable String userId,
                            @RequestBody Bookmark bookmark) {

        return "POST Call getBookmarks";
    }

}


class Bookmark {

    private String userId;

    private String href;

    private String description;

    Bookmark() {
    }

    public Bookmark(String userId, String href,
                    String description, String label) {
        this.userId = userId;
        this.href = href;
        this.description = description;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getUserId() {
        return userId;
    }

    public String getHref() {
        return href;
    }

    public String getDescription() {
        return description;
    }

    private String label;
}