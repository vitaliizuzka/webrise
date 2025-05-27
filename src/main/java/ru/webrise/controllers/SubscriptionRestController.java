package ru.webrise.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.webrise.dto.SubscriptionDto;
import ru.webrise.services.SubscriptionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscriptions")
public class SubscriptionRestController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/top")
    public List<SubscriptionDto> findTop3Subscriptions(){
        return subscriptionService.findTop3Subscriptions();
    }

}
