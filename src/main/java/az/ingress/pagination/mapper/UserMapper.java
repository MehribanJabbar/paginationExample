package az.ingress.pagination.mapper;

import az.ingress.pagination.entity.UserEntity;
import az.ingress.pagination.model.request.UserRequest;
import az.ingress.pagination.model.response.UserResponse;

public class UserMapper {

    public static UserResponse buildToResponse(UserEntity user){
        return UserResponse.builder()
                .id(user.getId())
                .age(user.getAge())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static UserEntity buildToEntity(UserRequest request){
        return UserEntity.builder()
                .name(request.getName())
                .age(request.getAge())
                .email(request.getEmail())
                .build();
    }
}
