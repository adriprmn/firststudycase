package com.mitrais.cdc.dao;

import com.mitrais.cdc.model.User;

import java.util.Optional;

public interface UserDao extends Dao<User, Long> {

    /**
     * Find {@link User} by its username
     * @param userName username
     * @return user
     */
    Optional<User> findByUserName(String userName);
}
