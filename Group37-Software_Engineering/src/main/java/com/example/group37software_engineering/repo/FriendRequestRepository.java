package com.example.group37software_engineering.repo;

import com.example.group37software_engineering.model.FriendRequest;
import com.example.group37software_engineering.model.MyUser;
import org.springframework.data.repository.CrudRepository;

public interface FriendRequestRepository extends CrudRepository<FriendRequest, Integer> {

    public FriendRequest findBySenderUsername(String username);

    public FriendRequest findByReceiverUsername(String username);

}
