package uz.example.repository;


import org.apache.ibatis.annotations.Param;
import uz.example.dao.User;

import java.util.List;

public interface UserRepository {

    Long insert(@Param("user") User user);

    List<User> getUsers();

    User getUser(@Param("username") String username);

    void deleteById(@Param("id") Long id);

    void update(@Param("user") User user);

}
