package com.example.evaluation_1.chat.repository;

import com.example.evaluation_1.chat.entity.ChatRoom;
import com.example.evaluation_1.signupAndLogin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

//   Extends JpaRepository to provide basic CRUD operations.
@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    // Finds all chat rooms
    List<ChatRoom> findByUser1OrUser2(User user1, User user2);

    // Finds a specific chat room involving the two specified users.
    ChatRoom findByUser1AndUser2(User user1, User user2);

}
