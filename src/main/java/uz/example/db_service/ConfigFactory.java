package uz.example.db_service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

public class ConfigFactory {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("config.xml");
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File Not Found Exception");
        } catch (IOException iOException) {
            System.out.println("Cannot Open Connection");
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
