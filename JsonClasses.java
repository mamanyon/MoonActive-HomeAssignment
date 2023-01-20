import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class JokeResponse {
    public Success success;
    public Contents contents;

}
class Success {
    public int total;
}
class Contents {
    public List<Category> categories;
    public String copyright;
}
class Category {
    public String language;
    public String name;
    public String background;
    public String description;
}

class ResponseJson{
    public HashMap<String, Integer> success;
    public Content contents;
}

class Content{
    public ArrayList<JokeContents> jokes;
    public String copyright;
}
class JokeContents {
    public String language;
    public String date;
    public String description;
    public JokeJson joke;
    public String category;
    public String background;
}
class JokeJson{
    public String racial;
    public String title;
    public String date;
    public Integer length;
    public String lang;
    public String clean;
    public String id;
    public String text;
}

