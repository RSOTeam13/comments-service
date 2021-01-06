package si.fri.rso.albify.commentservice.services.beans;


import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.glassfish.jersey.server.ContainerRequest;
import si.fri.rso.albify.commentservice.lib.Comment;
import si.fri.rso.albify.commentservice.models.converters.CommentConverter;
import si.fri.rso.albify.commentservice.models.entities.CommentEntity;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@RequestScoped
public class CommentBean {

//    @Inject
//    ContainerRequest request;

    private Logger log = Logger.getLogger(CommentBean.class.getName());

    private CodecRegistry pojoCodecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    private MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(System.getenv("DB_URL")))
                .codecRegistry(pojoCodecRegistry)
                .build();


    private MongoClient mongoClient = MongoClients.create(settings);
    private MongoDatabase database = mongoClient.getDatabase("albify");
    private MongoCollection<CommentEntity> commentsCollection = database.getCollection("comments", CommentEntity.class);

    @PreDestroy
    private void onDestroy() {
        try {
            mongoClient.close();
        } catch (Exception e) {
            log.severe("Error when closing comment bean database connection.");
            e.printStackTrace();
        }
    }

    /**
     * Returns image by its ID.
     * @param commentId Image ID.
     * @return Image entity.
     */
    public CommentEntity getComment(String commentId) {
        try {
            CommentEntity entity = commentsCollection.find(eq("_id", new ObjectId(commentId))).first();
            if (entity != null && entity.getId() != null) {
                return entity;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns list of images.
     * @param imageId Id of image you're getting comments for.
     * @return List of images.
     */
    public List<Comment> getComments(
            String imageId,
            Integer limit,
            Integer offset) {

        try {
            return commentsCollection
                    .find(eq("imageId", new ObjectId(imageId)))
                    .limit(limit)
                    .skip(offset)
                    .into(new ArrayList<>())
                    .stream()
                    .map(CommentConverter::toDto)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Counts comments of an image in DB.
     * @return Comment count.
     */
    public long getCommentsCount(String imageId) {
        BasicDBObject query = new BasicDBObject();

        try {
            return commentsCollection.countDocuments(eq("imageId", new ObjectId(imageId)));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Creates new comment.
     * @param comment to create.
     * @return Newly created comment.
     */
    public CommentEntity createComment(Comment comment) {
        try {
            CommentEntity entity = CommentConverter.toEntity(comment);
//            entity.setOwnerId(new ObjectId(request.getProperty("userId").toString()));
            entity.setImageId(new ObjectId(comment.getImageId()));
            entity.setOwnerId(new ObjectId(comment.getOwnerId()));
            entity.setText(comment.getText());
            entity.setCreatedAt(new Date());

            InsertOneResult result = commentsCollection.insertOne(entity);
            if (result != null) {
                return entity;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Removes comment.
     * @param commentId Comment ID.
     * @return Deleted comment.
     */
    public CommentEntity removeComment(String commentId) {
        try {
            CommentEntity entity = commentsCollection.findOneAndDelete(eq("_id", new ObjectId(commentId)));
            if (entity != null && entity.getId() != null) {
                return entity;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
