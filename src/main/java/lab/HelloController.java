package lab;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/** API déployée avec deux replicas et une configuration externe. */
@RestController
public class HelloController {
  private final String message;

  public HelloController(
      @Value("${APP_MESSAGE:Hello from Spring Boot on Kubernetes}") String message) {
    this.message = message;
  }

  @GetMapping("/api/hello")
  public Map<String, String> hello() {
    return Map.of("message", message, "status", "OK");
  }
}
