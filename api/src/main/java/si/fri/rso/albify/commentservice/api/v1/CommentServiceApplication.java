package si.fri.rso.albify.commentservice.api.v1;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.graphql.GraphQLApplication;
import com.kumuluz.ee.graphql.annotations.GraphQLApplicationClass;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// @ApplicationPath("/v1")
@GraphQLApplicationClass
@CrossOrigin(name = "comments-resource")
public class CommentServiceApplication extends GraphQLApplication {

}
