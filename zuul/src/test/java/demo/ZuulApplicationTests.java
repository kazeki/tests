package demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.noop.NoopDiscoveryClient;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class ZuulApplicationTests {

	@Autowired
	DiscoveryClient discoveryClient;

	@LocalServerPort
	int port;

	@Test
	public void contextLoads() {
		assertEquals("Hello World", new TestRestTemplate()
				.getForObject("http://localhost:" + this.port + "/static", String.class));
	}

	@Test
	public void discoveryClientIsNoop() {
		assertTrue("discoveryClient is wrong type",
				this.discoveryClient instanceof NoopDiscoveryClient);
	}
}
