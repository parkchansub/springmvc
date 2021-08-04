package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 *{"username":"a", "age": "20"}
 */

@Slf4j
@RestController
public class RequestBodyJsonController {
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requsetBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("userName = {}, age = {}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("ok");

    }

    @PostMapping("/request-body-json-v2")
    public String requsetBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("userName = {}, age = {}", helloData.getUsername(), helloData.getAge());

        return "ok";

    }

    @PostMapping("/request-body-json-v3")
    public String requsetBodyJsonV3(@RequestBody HelloData helloData)  {

        log.info("userName = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";

    }

    @PostMapping("/request-body-json-v4")
    public String requsetBodyJsonV4(HttpEntity<HelloData> helloData)  {
        log.info("userName = {}, age = {}", helloData.getBody().getUsername(), helloData.getBody().getAge());
        return "ok";

    }

    @PostMapping("/request-body-json-v5")
    public HelloData requsetBodyJsonV4(@RequestBody HelloData helloData)  {
        log.info("userName = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return helloData;
    }
}
