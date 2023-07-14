package az.ingress.pagination.service;

import az.ingress.pagination.entity.UserEntity;
import az.ingress.pagination.mapper.UserMapper;
import az.ingress.pagination.model.criteria.PageCriteria;
import az.ingress.pagination.model.criteria.UserCriteria;
import az.ingress.pagination.model.request.UserRequest;
import az.ingress.pagination.model.response.PageableUserResponse;
import az.ingress.pagination.repository.UserRepository;
import az.ingress.pagination.service.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static az.ingress.pagination.mapper.UserMapper.buildToEntity;
import static az.ingress.pagination.model.constants.CriteriaConstants.COUNT_DEFAULT_VALUE;
import static az.ingress.pagination.model.constants.CriteriaConstants.PAGE_DEFAULT_VALUE;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @SneakyThrows
    public void saveUser(UserRequest userRequest){
        Thread.sleep(2000);
        UserEntity user = buildToEntity(userRequest);
        userRepository.save(user);
    }

    public PageableUserResponse getUser(PageCriteria pageCriteria, UserCriteria userCriteria){
        var pageNumber = pageCriteria.getPage() == null ? PAGE_DEFAULT_VALUE : pageCriteria.getPage();
        var count = pageCriteria.getCount() == null ? COUNT_DEFAULT_VALUE : pageCriteria.getCount();
        
        var userPage = userRepository.findAll(new UserSpecification(userCriteria), PageRequest.of(pageNumber, count));

        return  mapToPageableResponse(userPage);
    }

    private PageableUserResponse mapToPageableResponse(Page<UserEntity> userPage){
        return PageableUserResponse.builder()
                .users(userPage.getContent().stream().map(UserMapper::buildToResponse).collect(Collectors.toList()))
                .hasNextPage(userPage.hasNext())
                .lastPageNumber(userPage.getTotalPages())
                .totalElements(userPage.getTotalElements())
                .build();
    }
}
