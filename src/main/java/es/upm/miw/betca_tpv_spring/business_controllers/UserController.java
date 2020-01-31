package es.upm.miw.betca_tpv_spring.business_controllers;

import es.upm.miw.betca_tpv_spring.business_services.JwtService;
import es.upm.miw.betca_tpv_spring.documents.Role;
import es.upm.miw.betca_tpv_spring.documents.User;
import es.upm.miw.betca_tpv_spring.dtos.TokenOutputDto;
import es.upm.miw.betca_tpv_spring.dtos.UserDto;
import es.upm.miw.betca_tpv_spring.dtos.UserMinimumDto;
import es.upm.miw.betca_tpv_spring.exceptions.ForbiddenException;
import es.upm.miw.betca_tpv_spring.exceptions.NotFoundException;
import es.upm.miw.betca_tpv_spring.repositories.UserReactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private UserReactRepository userReactRepository;
    private JwtService jwtService;

    @Autowired
    public UserController(UserReactRepository userReactRepository, JwtService jwtService) {
        this.userReactRepository = userReactRepository;
        this.jwtService = jwtService;
    }

    public Mono<TokenOutputDto> login(String mobile) {
        return this.userReactRepository.findByMobile(mobile).map(
                user -> {
                    String[] roles = Arrays.stream(user.getRoles()).map(Role::name).toArray(String[]::new);
                    return new TokenOutputDto(jwtService.createToken(user.getMobile(), user.getUsername(), roles));
                }
        );
    }

    private Mono<User> readAndValidate(String mobile, String claimMobile, List<String> claimRoles) {
        return this.userReactRepository.findByMobile(mobile)
                .switchIfEmpty(Mono.error(new NotFoundException("User mobile:" + mobile)))
                .handle((user, sink) -> {
                    if (!this.isAuthorized(claimMobile, claimRoles, mobile, Arrays.stream(user.getRoles())
                            .map(Role::roleName).collect(Collectors.toList()))) {
                        sink.error(new ForbiddenException("User mobile (" + mobile + ")"));
                    } else {
                        sink.next(user);
                    }
                });
    }

    private boolean isAuthorized(String claimMobile, List<String> claimRoles, String userMobile, List<String> userRoles) {
        if (claimRoles.contains(Role.ADMIN.roleName()) || claimMobile.equals(userMobile)) {
            return true;
        }
        if (claimRoles.contains(Role.MANAGER.roleName())
                && !userRoles.contains(Role.ADMIN.roleName()) && !userRoles.contains(Role.MANAGER.roleName())) {
            return true;
        }
        return claimRoles.contains(Role.OPERATOR.roleName())
                && userRoles.stream().allMatch(role -> role.equals(Role.CUSTOMER.roleName()));
    }

    public Mono<UserDto> readUser(String mobile, String claimMobile, List<String> claimRoles) {
        return this.readAndValidate(mobile, claimMobile, claimRoles)
                .map(UserDto::new);
    }

    public Flux<UserMinimumDto> readAll() {
        return this.userReactRepository.findAllUsers();
    }


}
