package com.example.group37software_engineering.repo;

import com.example.group37software_engineering.model.FriendRequest;
import com.example.group37software_engineering.model.MyUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendRequestRepository extends CrudRepository<FriendRequest, Integer> {

    public List<FriendRequest> findBySenderUsername(String username);

    public List<FriendRequest> findByReceiverUsername(String username);

    public FriendRequest findBySenderUsernameAndReceiverUsername(String senderUsername, String ReceiverUsername);

}
