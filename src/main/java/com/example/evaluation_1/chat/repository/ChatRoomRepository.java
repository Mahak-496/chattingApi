package com.example.evaluation_1.chat.repository;

import com.example.evaluation_1.chat.entity.ChatRoom;
import com.example.evaluation_1.signupAndLogin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {

    Optional<ChatRoom> findByUsers(Set<User> users);

    Set<ChatRoom> findByUsersContaining(User user);
}
