package uz.example.db_service.users;


import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    Long insert(@Param("user") User user);

    List<User> getUsers();

    User getUser(@Param("username") String username);

    void deleteById(@Param("id") Long id);

    void update(@Param("user") User user);

}
