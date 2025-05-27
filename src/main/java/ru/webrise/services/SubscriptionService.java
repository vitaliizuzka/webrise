package ru.webrise.services;

import org.springframework.stereotype.Service;
import ru.webrise.dto.SubscriptionDto;

import java.util.List;


public interface SubscriptionService {
    List<SubscriptionDto> findTop3Subscriptions();
}
