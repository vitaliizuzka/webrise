package ru.webrise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.webrise.entities.Subscription;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    @Query(value = """
            SELECT s.id, s.name, count(us.user_id) AS user_count
            FROM  subscription s 
            JOIN user_subscription us ON s.id = us.subscription_id
            GROUP BY s.id, s.name
            ORDER BY user_count DESC 
            LIMIT 3
            """
            , nativeQuery = true)
    List<Object[]> findTop3Subscriptions();

}
