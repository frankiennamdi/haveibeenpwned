package com.franklin.sample.haveibeenpwned.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Handle the response from the haveibeenpwned service
 */
public class RequestResponseHandler implements ResponseHandler<String> {

  public String handleResponse(final HttpResponse response) throws IOException {

    int statusCode = response.getStatusLine().getStatusCode();
    HttpEntity entity = response.getEntity();

    if (entity == null) {
      throw new ClientProtocolException("Response contains no content");
    }
    String responseBody = EntityUtils.toString(entity);
    if (statusCode != HttpStatus.SC_OK) {
      NotOkResponse notOkResponse = new NotOkResponse(statusCode, responseBody);
      return prettyPrint(notOkResponse.toString());
    }
    return prettyPrint(responseBody);
  }

  private String prettyPrint(String jsonString) {
    Gson gson = new GsonBuilder().setPrettyPrinting().setLenient().create();
    JsonParser jp = new JsonParser();
    JsonElement je = jp.parse(jsonString);
    return gson.toJson(je);
  }
}