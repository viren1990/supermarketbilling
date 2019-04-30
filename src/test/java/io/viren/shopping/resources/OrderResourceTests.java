package io.viren.shopping.resources;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import io.viren.shopping.SuperMarketBillingAppConfiguration;
import io.viren.shopping.domains.OrderDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {SuperMarketBillingAppConfiguration.class})
public class OrderResourceTests {

  @Autowired private TestRestTemplate restTemplate;

  @LocalServerPort private int port;

  @Test
  public void test_promotion_success() throws IOException {

    final String stringJson =
        Files.readString(Paths.get("src/test/resources/test.json"), Charset.forName("utf-8"));
    Assert.assertNotNull(stringJson);

    final var orderDto = new Gson().fromJson(stringJson, OrderDto.class);
    Assert.assertNotNull(orderDto);

    final var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    
    final var entity = new HttpEntity<OrderDto>(orderDto, headers);
    final var response =
        restTemplate.exchange(
            createURLWithPort("/orders"), HttpMethod.POST, entity, OrderDto.class);
    
    final var resultDto = response.getBody();
    Assert.assertNotNull(resultDto);
    Assert.assertTrue(!resultDto.getOrderEntries().isEmpty());
    Assert.assertTrue(resultDto.getCurrency().equals("INR"));
    Assert.assertTrue(Double.compare(resultDto.getSubTotal(), 1295) == 0);
    Assert.assertTrue(Double.compare(resultDto.getTotalPrice(), 1295) == 0);
    Assert.assertTrue(Double.compare(resultDto.getTotalDiscount(), 355) == 0);
    Assert.assertTrue(Double.compare(resultDto.getSubTotalWithoutDiscounts(), 1650) == 0);
    Assert.assertNotNull(resultDto.getUser());
    Assert.assertTrue(resultDto.getUser().getUid().equals("anishkumar@in.com"));
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }
}
