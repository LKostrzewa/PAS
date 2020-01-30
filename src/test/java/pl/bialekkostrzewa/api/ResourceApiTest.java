package pl.bialekkostrzewa.api;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

class ResourceApiTest {

    private String urlBase = "https://localhost:8443/restaurant/api/resources/";
    public static int size;

    HttpClient creteConnection() throws Exception{
        String keyStorePassword = "changeit";
        KeyStore keyStore = null;
        keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(new FileInputStream(new File("/Users/pawelbialek/.keystore")),
                keyStorePassword.toCharArray());

        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                new SSLContextBuilder()
                        .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                        .loadKeyMaterial(keyStore, keyStorePassword.toCharArray())
                        .build(),
                NoopHostnameVerifier.INSTANCE);

        HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(
                socketFactory).build();
        return httpClient;
    }


    @Test
    void simpleTest() throws Exception {

        HttpClient httpClient = creteConnection();
        HttpUriRequest request = new HttpGet(urlBase);
        HttpResponse response = httpClient.execute(request);

        String responseJSON = EntityUtils.toString(response.getEntity());
        JSONArray test = new JSONArray(responseJSON);
        size = test.length();
        Assertions.assertEquals(response.getStatusLine().getStatusCode(), 200);
        Assertions.assertNotEquals(size, 0);
    }

    @Test
    void postTest() throws Exception {
        HttpClient httpClient = creteConnection();

        JSONObject body = new JSONObject()
                .put("number",10)
                .put("numOfPeople",90)
                .put("id","restTest")
                .put("price", 12.5);
        StringEntity tmp = new StringEntity(body.toString());
        HttpPost post = new HttpPost(urlBase + "/add-table");
        post.setEntity(tmp);
        post.addHeader("Content-Type", "application/json");
        HttpResponse response = httpClient.execute(post);

        Assertions.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @Test
    void deleteTest() throws Exception {
        HttpClient httpClient = creteConnection();
        String id = "restTest";
        HttpDelete delete = new HttpDelete(urlBase + "/delete-resource/" + id);
        httpClient.execute(delete);

        HttpUriRequest request = new HttpGet(urlBase);
        HttpResponse response = httpClient.execute(request);
        String responseJSON = EntityUtils.toString(response.getEntity());
        JSONArray test = new JSONArray(responseJSON);

        Assertions.assertEquals(2, test.length());
    }
}
