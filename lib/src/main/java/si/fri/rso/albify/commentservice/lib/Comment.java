package si.fri.rso.albify.commentservice.lib;

import io.leangen.graphql.annotations.GraphQLIgnore;
import io.leangen.graphql.annotations.GraphQLNonNull;

import java.util.Date;

public class Comment {

    private String id;
    private Date createdAt;
    private String ownerId;
    private String imageId;
    private String text;

    public Date getCreatedAt() {
        return createdAt;
    }
    @GraphQLIgnore
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }
    @GraphQLIgnore
    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(@GraphQLNonNull String imageId) {
        this.imageId = imageId;
    }

    public @GraphQLNonNull String getText() {
        return text;
    }

    public void setText(@GraphQLNonNull String text) {
        this.text = text;
    }
}
