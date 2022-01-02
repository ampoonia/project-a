package dev.ampoonia.projecta.ingress;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
public class PingRoute {

    @Value("${info.app.version:none}") String appVersion;

    @GetMapping("/")
    public String root () {
        return ping();
    }

    @GetMapping("/ping")
    public String ping () {
        final String result = "SpringBootDemo: " + appVersion + " Ping @ " + LocalDateTime.now();
        log.info("SpringBootDemo: Ping, result = {}", result);

        return result;
    }

    @GetMapping({"/greet", "/greet/{name}"})
    public String greet (@PathVariable(value = "name", required = false) String name) {
        String result;
        if (name != null && !name.isEmpty()) {
            result = String.format("SpringBootDemo: Greetings, %s!", name);
        } else result = "SpringBootDemo: Greetings, Stranger!";
        log.info("SpringBootDemo: Greet, result = {}", result);

        return result;
    }
}
