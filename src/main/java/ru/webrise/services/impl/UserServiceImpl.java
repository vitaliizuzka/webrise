package ru.webrise.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.webrise.dto.SubscriptionDto;
import ru.webrise.dto.UserDto;
import ru.webrise.entities.Subscription;
import ru.webrise.entities.User;
import ru.webrise.entities.UserSubscription;
import ru.webrise.repositories.SubscriptionRepository;
import ru.webrise.repositories.UserRepository;
import ru.webrise.repositories.UserSubscriptionRepository;
import ru.webrise.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;

    @Override
    public UserDto create(UserDto userDto) {
        log.info("try to save user: {}", userDto);
        User savedUser = userRepository.save(new User(userDto.email(), userDto.name()));
        log.info("user: {} saved successfully", savedUser);
        return new UserDto( savedUser.getEmail(), savedUser.getName());
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        log.info("try to find user by id: {}", id);
        User foundUser = userRepository.findById(id).get();
        log.info("user: {} found successfully", foundUser);
        return Optional.of(new UserDto( foundUser.getEmail(), foundUser.getName()));
    }

    @Override
    public List<UserDto> findAll() {
        log.info("try to find all users");
        return userRepository
                .findAll()
                .stream()
                .map(user -> new UserDto(user.getEmail(), user.getName()))
                .toList();
    }

    @Override
    public Optional<UserDto> updateById(Long id, UserDto userDto) {
        log.info("try to update user by id: {}", id);
        User readUser = userRepository.findById(id).get();
        readUser.setName(userDto.name());
        readUser.setEmail(userDto.email());
        User savedUser = userRepository.saveAndFlush(readUser);
        log.info("old user: {}, updated user: {}", readUser, savedUser);
        return Optional.of(new UserDto( savedUser.getEmail(), savedUser.getName()));
    }

    @Override
    public boolean deleteById(Long id) {
        log.info("try to delete user by id: {}", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            userRepository.flush();
            log.info("user deleted successfully");
            return true;
        }
        log.info("user not found");
        return false;
    }

    @Override
    public SubscriptionDto addNewSubscriptionByUserId(Long id, SubscriptionDto subscriptionDto) {
        log.info("try to add subscription {} for user by id: {}",subscriptionDto, id);
        User foundUser = userRepository.findById(id).get();
        Subscription subscription = new Subscription(subscriptionDto.name());
        Subscription saved = subscriptionRepository.save(subscription);
        UserSubscription userSubscription = new UserSubscription();
        userSubscription.setUser(foundUser);
        userSubscription.setSubscription(saved);
        userSubscriptionRepository.save(userSubscription);
        log.info("subscription {} for user {} added successfully",saved, foundUser);
        return new SubscriptionDto( saved.getName());
    }

    @Override
    public List<SubscriptionDto> findAllSubscriptionByUserId(Long id) {
        return userSubscriptionRepository
                .findByUserId(id)
                .stream()
                .map(userSubscription -> userSubscription.getSubscription())
                .map(subscription -> new SubscriptionDto( subscription.getName()))
                .toList();
    }

    @Override
    public boolean deleteSubscriptionByIdForUserById(Long userId, Integer subscriptionId) {
        log.info("try to delete subscription by id {} for user by id: {}", subscriptionId, userId);
        Optional<UserSubscription> userSubscription = userSubscriptionRepository.findByUserIdAndSubscriptionId(userId, subscriptionId);
        if (userSubscription.isPresent()) {
            userSubscriptionRepository.deleteByUserIdAndSubscriptionId(userId, subscriptionId);
            userSubscriptionRepository.flush();
            log.info("subscription for user deleted successfully");
            return true;
        }
        return false;
    }


}
