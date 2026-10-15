package lab;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(properties = "APP_MESSAGE=Message ConfigMap de test")
@AutoConfigureMockMvc
class HelloApiTest {
  @Autowired MockMvc mvc;

  @Test
  void readsExternalConfiguration() throws Exception {
    mvc.perform(get("/api/hello"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").value("Message ConfigMap de test"))
        .andExpect(jsonPath("$.status").value("OK"));
  }

  @Test
  void readinessIsUp() throws Exception {
    mvc.perform(get("/actuator/health/readiness"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("UP"));
  }
}
