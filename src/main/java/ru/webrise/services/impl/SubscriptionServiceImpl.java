package ru.webrise.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.webrise.dto.SubscriptionDto;
import ru.webrise.repositories.SubscriptionRepository;
import ru.webrise.services.SubscriptionService;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public List<SubscriptionDto> findTop3Subscriptions() {
        log.info("finding top subscriptions...");
        return subscriptionRepository
                .findTop3Subscriptions()
                .stream()
                .map(entry -> new SubscriptionDto((String) entry[1]))
                .toList();
    }

}
