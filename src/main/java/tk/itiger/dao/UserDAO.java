package tk.itiger.dao;

import tk.itiger.model.User;

import java.util.List;

public interface UserDAO {


    public List<User> getAll();

    public User getOne(String email);

    public void addUser(User user);



}
