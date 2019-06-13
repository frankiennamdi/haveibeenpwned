package com.franklin.sample.haveibeenpwned.core;

import com.google.common.collect.ImmutableList;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

/**
 * Provide support for processing http request
 */
@Component
public class HttpSupport {

  public String getResponse(URI uri) {
    HttpGet httpGet = new HttpGet(uri);
    HttpClient httpClient = HttpClientBuilder
            .create()
            .setDefaultHeaders(ImmutableList.of(new BasicHeader("User-Agent", "Java App")))
            .build();
    try {
      return httpClient.execute(httpGet, new RequestResponseHandler());
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      httpGet.releaseConnection();
    }
  }
}
