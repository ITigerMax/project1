package tk.itiger.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Component;
import tk.itiger.model.User;

import java.util.List;


@Component
public class JdbcTemplateUserDao implements UserDAO{


    @Autowired
    JdbcTemplate jdbcTemplate;



    public List<User> getAll() {
        return jdbcTemplate.query(
                "select * from users",
                new BeanPropertyRowMapper<>(User.class));
    }

    public User getOne(String email) {
            return jdbcTemplate.query(
                    "select * from users where email = ?",
                    new Object[]{email},
                    new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    public void addUser(User user){
        jdbcTemplate.update("insert into users values(?,?,?)",
                user.getName(), user.getSurName(), user.getEmail());
    }



}
