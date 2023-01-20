import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Assert;
import org.junit.Test;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.ws.Response;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class Main {
    public static void main(String[] args) throws IOException {
        Map<String,String> headers = GetHeaders();
        List<AbstractAPITest> tests = new ArrayList<>();
        tests.add(new APITest1(headers, "https://jokes.p.rapidapi.com/jod/test"));
        tests.add(new APITest2(headers, "https://jokes.p.rapidapi.com/jod/categories"));



        for (AbstractAPITest test: tests) {
            try{
                test.Test();
            }
            catch (AssertionError e){
                System.out.println(e.getMessage());
            }
        }

    }

    public static Map<String,String> GetHeaders(){
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("x-rapidapi-host", "jokes.p.rapidapi.com");
        headers.put("x-rapidapi-key", "56d7a4653emsh4c19b463b18e6b7p144eb7jsn030e478c59b2");
        return headers;
    }
}



