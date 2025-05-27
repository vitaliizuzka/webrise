package ru.webrise.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.webrise.dto.SubscriptionDto;
import ru.webrise.dto.UserDto;
import ru.webrise.entities.User;
import ru.webrise.services.UserService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable("id") Long id) {
        return userService
                .findById(id)
                .orElseThrow(() -> {
                    log.info("user not found");
                    return new ResponseStatusException(HttpStatus.NOT_FOUND);
                });
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable("id") Long id, UserDto userDto) {
        return userService.updateById(id, userDto).orElseThrow(() -> {
            log.info("user not found");
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        if (!userService.deleteById(id)){
            log.info("user not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/subscriptions")
    public SubscriptionDto addNewSubscription(@PathVariable("id") Long id, SubscriptionDto subscriptionDto) {
        return userService.addNewSubscriptionByUserId(id, subscriptionDto);
    }

    @GetMapping("/{id}/subscriptions")
    public List<SubscriptionDto> findSubscriptionsByUserId(@PathVariable("id") Long id) {
        return userService.findAllSubscriptionByUserId(id);
    }

    @DeleteMapping("/{id}/subscriptions/{sub_id}")
    public boolean deleteSubscriptionByIdForUserById(@PathVariable("id") Long id,
                                                     @PathVariable("sub_id") Integer subscriptionId) {
        if (!userService.deleteSubscriptionByIdForUserById(id, subscriptionId)){
            log.info("user not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return true;
    }
}
