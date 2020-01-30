package pl.bialekkostrzewa.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ResourceApiTest {

    private String urlBase = "https://localhost:8443/restaurant/api/resources";

    @Test
    void postTestCorrect(){

        HttpUriRequest request = new HttpGet(urlBase);

        try {
            HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
            httpResponse.getEntity().getContent();
            System.out.println("aa");
        }
        catch (IOException e){
            e.printStackTrace();
        }


    }
}
