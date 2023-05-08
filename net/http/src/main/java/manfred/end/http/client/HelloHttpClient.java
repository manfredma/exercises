package manfred.end.http.client;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HelloHttpClient {
    public static void main(String[] args) {
        RequestConfig config = RequestConfig.custom()
//				.setConnectTimeout(5000)
//				.setConnectionRequestTimeout(5000)
//				.setSocketTimeout(20000)
                .build();

        try (final CloseableHttpClient httpclient =
                     HttpClientBuilder.create().setDefaultRequestConfig(config).build()) {
            final HttpGet httpget = new HttpGet("http://www.yidas.com");

//			System.out.println("Executing request " + httpget.getMethod() + " " + httpget.getURI
//			());

            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity);

            System.out.println(responseBody);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
