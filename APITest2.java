import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class APITest2 extends AbstractAPITest
{
    public APITest2(Map<String, String> headers, String uri) {
        super(headers, uri);
    }

    @Override
    protected void MakeTest(String jsonString, ObjectMapper mapper) throws IOException {
        JokeResponse jokeResponse = mapper.readValue(jsonString, JokeResponse.class);

        List<Category> categories = jokeResponse.contents.categories;
        String category_name =categories.get(2).name;// Get the 3rd category

        String joke_uri = "https://jokes.p.rapidapi.com/jod?category="+category_name;
        CloseableHttpResponse joke_response= MakeCall(joke_uri,headers);
        int code = joke_response.getStatusLine().getStatusCode();
        String joke_json = EntityUtils.toString(joke_response.getEntity());

        Assert.assertEquals(200, code);//should be 200

        ObjectMapper joke_mapper = new ObjectMapper();

        ResponseJson responsejson = joke_mapper.readValue(joke_json, ResponseJson.class);
        String id = responsejson.contents.jokes.get(0).joke.id;
        String description = responsejson.contents.jokes.get(0).description;
        String category = responsejson.contents.jokes.get(0).category;
        String title = responsejson.contents.jokes.get(0).joke.title;
        String text = responsejson.contents.jokes.get(0).joke.text;

        WriteFile(id, description, category, title, text);
    }

    private void WriteFile(String id, String description, String category, String title, String text) throws IOException {
        FileWriter writer = new FileWriter(id + ".txt");
        writer.write("Description: " + description + "\n");
        writer.write("Category: " + category + "\n");
        writer.write("Title: " + title + "\n");
        writer.write("Text: " + text + "\n");
        writer.close();
    }
}
