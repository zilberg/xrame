package org.xrame.api;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test gitHub")
public class GitHubTest {
    @Test
    public void givenUserDoesNotExists() throws ClientProtocolException, IOException{
        String name = RandomStringUtils.randomAlphabetic(8);
        HttpUriRequest request = new HttpGet("https://api.github.com/users/" + name);
        System.out.println(request.getMethod());

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse.getStatusLine());
    }

    @Test
    public void RequestWithNoAcceptHeader()
    throws ClientProtocolException, IOException{
        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet("https://api.github.com/users/zilberg");

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();

        assertEquals(jsonMimeType, mimeType);
    }

    @Test
    public void testPayload()
    throws ClientProtocolException, IOException{
        HttpUriRequest request = new HttpGet("https://api.github.com/users/zilberg");

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        GitHubUser resource = RetrievalUtil.retrieveResourceFromResponse(response, GitHubUser.class);
        assertEquals("zilberg", resource.getLogin());
    }

}
