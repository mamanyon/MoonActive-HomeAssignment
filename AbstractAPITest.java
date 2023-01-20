import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;

import java.io.IOException;
import java.util.Map;

public abstract class AbstractAPITest
{
    protected Map<String,String> headers;
    protected String uri;

    public AbstractAPITest(Map<String, String> headers, String uri) {
        this.headers = headers;
        this.uri = uri;
    }

    public void Test() throws IOException {
        CloseableHttpResponse response = MakeCall(uri,headers);
        int responseCode = response.getStatusLine().getStatusCode();
        String jsonString = EntityUtils.toString(response.getEntity());

        // should be 200
        Assert.assertEquals(200, responseCode);

        ObjectMapper mapper = new ObjectMapper();
        MakeTest(jsonString, mapper);
        response.close();
    }
    protected CloseableHttpResponse MakeCall(String uri , Map<String,String> headers )throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Prepare a request object
        HttpGet httpGet = new HttpGet(uri);

        // Add the headers
        for (String key : headers.keySet()) {
            httpGet.addHeader(key,headers.get(key));
        }

        // Execute the request
        return httpClient.execute(httpGet);
    }

    protected abstract void MakeTest(String jsonString, ObjectMapper mapper) throws IOException;
}
