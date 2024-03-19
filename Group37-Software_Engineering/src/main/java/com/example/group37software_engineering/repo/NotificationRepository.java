package com.example.group37software_engineering.repo;

import com.example.group37software_engineering.model.Notification;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NotificationRepository extends CrudRepository<Notification, Integer> {
}
