package uz.example.service;


import org.apache.ibatis.session.SqlSession;
import uz.example.conf.ConfigFactory;
import uz.example.dao.User;
import uz.example.repository.UserRepository;

import java.util.Collections;
import java.util.List;

public class UserService {

    public List<User> getUsersList() {
        try (SqlSession sqlSession = ConfigFactory.getSqlSessionFactory().openSession()) {
            UserRepository mapper = sqlSession.getMapper(UserRepository.class);
            return mapper.getUsers();
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        return Collections.emptyList();
    }

    public User getUser(String username) {
        try (SqlSession sqlSession = ConfigFactory.getSqlSessionFactory().openSession()) {
            UserRepository mapper = sqlSession.getMapper(UserRepository.class);
            return mapper.getUser(username);
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        return null;
    }

    public void deleteById(Long id) {
        try (SqlSession sqlSession = ConfigFactory.getSqlSessionFactory().openSession()) {
            UserRepository mapper = sqlSession.getMapper(UserRepository.class);
            mapper.deleteById(id);
            sqlSession.commit();
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    public boolean create(User user) {
        try (SqlSession sqlSession = ConfigFactory.getSqlSessionFactory().openSession()) {
            UserRepository mapper = sqlSession.getMapper(UserRepository.class);
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
            UserRepository mapper = sqlSession.getMapper(UserRepository.class);
            mapper.update(user);
            sqlSession.commit();
            System.out.println("Updated !");
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

}
