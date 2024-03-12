import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(String id) {
        return commentRepository.findById(id);
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(String id, Comment updatedComment) {
        updatedComment.setId(id);
        return commentRepository.save(updatedComment);
    }

    public void deleteComment(String id) {
        commentRepository.deleteById(id);
    }

    public void likeComment(String id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setLikes(comment.getLikes() + 1);
            commentRepository.save(comment);
        }
    }

    public void dislikeComment(String id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setDislikes(comment.getDislikes() + 1);
            commentRepository.save(comment);
        }
    }
}

