package az.ingress.pagination.controller;

import az.ingress.pagination.model.criteria.PageCriteria;
import az.ingress.pagination.model.criteria.UserCriteria;
import az.ingress.pagination.model.request.UserRequest;
import az.ingress.pagination.model.response.PageableUserResponse;
import az.ingress.pagination.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(NO_CONTENT)
    public void saveUser(@RequestBody UserRequest userRequest){
        userService.saveUser(userRequest);
    }

    public PageableUserResponse getUsers(PageCriteria pageCriteria, UserCriteria userCriteria,
                                         @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")LocalDateTime createdAt){
        return userService.getUser(pageCriteria, userCriteria);
    }


}
