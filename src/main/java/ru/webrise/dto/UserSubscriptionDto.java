package ru.webrise.dto;

public record UserSubscriptionDto (UserDto userDto,
                                   SubscriptionDto subscriptionDto) {
}
