package uz.example.db_service.users;


import org.apache.ibatis.session.SqlSession;
import uz.example.db_service.ConfigFactory;

import java.util.Collections;
import java.util.List;

public class UserService {

    public List<User> getUsersList() {
        try (SqlSession sqlSession = ConfigFactory.getSqlSessionFactory().openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.getUsers();
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        return Collections.emptyList();
    }

    public User getUser(String username) {
        try (SqlSession sqlSession = ConfigFactory.getSqlSessionFactory().openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.getUser(username);
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        return null;
    }

    public void deleteById(Long id) {
        try (SqlSession sqlSession = ConfigFactory.getSqlSessionFactory().openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.deleteById(id);
            sqlSession.commit();
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    public boolean create(User user) {
        try (SqlSession sqlSession = ConfigFactory.getSqlSessionFactory().openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            Long id = mapper.insert(user);
            sqlSession.commit();
            System.out.println("id = " + id);
            return true;
        } catch (Exception e) {
            System.out.println("e = " + e);
            return false;
        }
    }

    public void update(User user) {
        try (SqlSession sqlSession = ConfigFactory.getSqlSessionFactory().openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.update(user);
            sqlSession.commit();
            System.out.println("Updated !");
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

}
