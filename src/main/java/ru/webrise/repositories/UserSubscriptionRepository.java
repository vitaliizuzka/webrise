package ru.webrise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.webrise.entities.Subscription;
import ru.webrise.entities.UserSubscription;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Long> {

    List<UserSubscription> findByUserId(Long id);

    @Modifying
    @Transactional
    @Query("delete from UserSubscription where user.id = :userId and subscription.id = :subscriptionId")
    void deleteByUserIdAndSubscriptionId(Long userId, Integer subscriptionId);

    Optional<UserSubscription> findByUserIdAndSubscriptionId(Long userId, Integer subscriptionId);



}
