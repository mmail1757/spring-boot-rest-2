package app.test;

import app.test.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TestApplicationTests {

	private static final HttpClient httpClient = HttpClient.newBuilder()
			.version(HttpClient.Version.HTTP_2)
			.connectTimeout(Duration.ofSeconds(10))
			.build();

	@Autowired
	JdbcTemplate template;

	User user = new User("Tom", "Pass", BigDecimal.ZERO);

	@Test
	void contextLoads() {
	}

	@Test
	void sendRequest() throws IOException, InterruptedException {
		template.update("delete from users_tbl");

		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <request>\n" +
				"\n" +
				"<request-type>%s</request-type> <extra name=\"login\">%s</extra> <extra name=\"password\">%s</extra>\n" +
				"\n" +
				"</request> \n" +
				"\n";

		HttpRequest request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(String.format(xml, "CREATE-AGT", user.getLogin(), user.getPassword())))
				.uri(URI.create("http://localhost:8080/user"))
				.setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
				.header("Content-Type", "application/xml")
				.build();

		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.statusCode());

		request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(String.format(xml, "GET-BALANCE", user.getLogin(), user.getPassword())))
				.uri(URI.create("http://localhost:8080/user"))
				.setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
				.header("Content-Type", "application/xml")
				.build();

		response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		request = HttpRequest.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(String.format(xml, "NO-OPP", user.getLogin(), user.getPassword())))
				.uri(URI.create("http://localhost:8080/user"))
				.setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
				.header("Content-Type", "application/xml")
				.build();

		response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		assertTrue(response.body().contains("<result-code>2</result-code>"));

	}

}
