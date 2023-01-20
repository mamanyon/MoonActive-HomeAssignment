import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;

import java.util.Map;

public class APITest1 extends AbstractAPITest
{
    public APITest1(Map<String, String> headers, String uri) {
        super(headers, uri);
    }

    @Override
    protected void MakeTest(String jsonString, ObjectMapper mapper) throws JsonProcessingException {
        Map<String, Object> jsonMap = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>(){});

        // Get the error message and code from the JSON map
        String errorMessage = (String) jsonMap.get("message");
        int errorCode = (int) jsonMap.get("code");

        // Check if the error message and code match the expected values
        Assert.assertEquals(404, errorCode);

    }
}
