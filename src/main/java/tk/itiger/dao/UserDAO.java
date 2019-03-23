package tk.itiger.dao;

import org.springframework.stereotype.Component;
import tk.itiger.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class UserDAO {

    private static Connection connection;

    static {
        String url = null;
        String username = null;
        String password = null;

        //load to properties;
        try(InputStream in = UserDAO.class
                .getClassLoader().getResourceAsStream("persistence.properties")){
            Properties properties = new Properties();
            properties.load(in);
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //acquire do connection
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        PreparedStatement st = connection.prepareStatement("SELECT * FROM users");
        ResultSet result = st.executeQuery();
        while (result.next()) {
            User user = new User();
            user.setName(result.getString(1));
            user.setSurName(result.getString(2));
            user.setEmail(result.getString(3));
            if (user != null) users.add(user);
        }

        return users;
    }

    public User getOne(String email) {
        try {
            PreparedStatement st = connection
                    .prepareStatement("select * from users where email = ?");
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                User user = new User();
                user.setName(rs.getString("name"));
                user.setSurName(rs.getString("surname"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        } catch(SQLException ignored){

        }
        return null;
    }

    public void addUser(User user){
        try {
            PreparedStatement st = connection.prepareStatement("insert into users values (?, ?, ?)");
            st.setString(1, user.getName());
            st.setString(2, user.getSurName());
            st.setString(3, user.getEmail());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
