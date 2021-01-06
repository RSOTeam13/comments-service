package si.fri.rso.albify.commentservice.graphql;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import si.fri.rso.albify.commentservice.lib.Comment;
import si.fri.rso.albify.commentservice.models.entities.CommentEntity;
import si.fri.rso.albify.commentservice.services.beans.CommentBean;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
@GraphQLClass
public class CommentMutations {

    @Inject
    private CommentBean commentBean;

    @GraphQLMutation
    public Comment addComment(@GraphQLArgument(name = "comment") Comment comment) {
        commentBean.createComment(comment);
        return comment;
    }

    @GraphQLMutation
    public DeleteResponse deleteComment(@GraphQLArgument(name = "id") String id) {
        CommentEntity entity = commentBean.removeComment(id);
        return new DeleteResponse(entity != null);
    }

}