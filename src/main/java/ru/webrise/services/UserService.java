package ru.webrise.services;

import ru.webrise.dto.SubscriptionDto;
import ru.webrise.dto.UserDto;
import ru.webrise.entities.Subscription;
import ru.webrise.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto create(UserDto user);
    Optional<UserDto> findById(Long id);
    List<UserDto> findAll();

    Optional<UserDto> updateById(Long id, UserDto userDto);
    boolean deleteById(Long id);

    SubscriptionDto addNewSubscriptionByUserId(Long id, SubscriptionDto subscriptionDto);

    List<SubscriptionDto> findAllSubscriptionByUserId(Long id);

    boolean deleteSubscriptionByIdForUserById(Long UserId, Integer subscriptionId);


}
