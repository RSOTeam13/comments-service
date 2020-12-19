package si.fri.rso.albify.commentservice.models.converters;

import si.fri.rso.albify.commentservice.lib.Comment;
import si.fri.rso.albify.commentservice.models.entities.CommentEntity;

public class CommentConverter {

    public static Comment toDto(CommentEntity entity) {

        Comment dto = new Comment();
        dto.setId(entity.getId().toString());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setOwnerId(entity.getOwnerId().toString());
        dto.setImageId(entity.getImageId().toString());
        dto.setText(entity.getText());

        return dto;

    }

    public static CommentEntity toEntity(Comment dto) {

        CommentEntity entity = new CommentEntity();
        return entity;

    }

}
