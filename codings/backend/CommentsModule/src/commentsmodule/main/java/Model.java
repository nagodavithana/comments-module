import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class Comment {
    @Id
    private String id;
    private String content;
    private int likes;
    private int dislikes;

    // Constructors, getters, and setters
}
