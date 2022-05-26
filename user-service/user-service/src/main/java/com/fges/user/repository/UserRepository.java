package com.fges.user.repository;

import com.fges.user.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {

    Optional<User> findByName(String name);

    User findByUserId(Long userId);

    //@Query( "select * from USER where user_Id in :userIds" )  @Param("userIds")
    List<User> findByUserIdIn(List<Long> userIds);
}
