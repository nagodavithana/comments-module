import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable String id) {
        Optional<Comment> comment = commentService.getCommentById(id);
        return comment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment addedComment = commentService.addComment(comment);
        return new ResponseEntity<>(addedComment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable String id, @RequestBody Comment updatedComment) {
        Comment comment = commentService.updateComment(id, updatedComment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/like")
    public ResponseEntity<Void> likeComment(@PathVariable String id) {
        commentService.likeComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/dislike")
    public ResponseEntity<Void> dislikeComment(@PathVariable String id) {
        commentService.dislikeComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
