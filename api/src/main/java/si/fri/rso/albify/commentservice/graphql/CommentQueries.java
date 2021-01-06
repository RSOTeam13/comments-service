package si.fri.rso.albify.commentservice.graphql;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import si.fri.rso.albify.commentservice.lib.Comment;
import si.fri.rso.albify.commentservice.models.entities.CommentEntity;
import si.fri.rso.albify.commentservice.services.beans.CommentBean;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
@GraphQLClass
public class CommentQueries {

    @Inject
    private CommentBean commentBean;

    @GraphQLQuery
    public List<Comment> allComments(
                                                    @GraphQLArgument(name = "id") String imageId,
                                                    @GraphQLArgument(name = "limit") Integer limit,
                                                    @GraphQLArgument(name = "offset") Integer offset) {
        System.out.println("TEST");
        List<Comment> comments = commentBean.getComments(imageId, limit, offset);

        return comments;
    }

    @GraphQLQuery
    public CommentEntity getComment(@GraphQLArgument(name = "id") String id) {
        return commentBean.getComment(id);
    }
}
