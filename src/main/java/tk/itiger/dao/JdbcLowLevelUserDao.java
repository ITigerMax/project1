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
public class JdbcLowLevelUserDao implements UserDAO {


    private static Connection connection;


    static {
        String driver = null;
        String url = null;
        String username = null;
        String password = null;
        try (InputStream in = JdbcLowLevelUserDao.class.getClassLoader().getResourceAsStream("persistence.properties")){

            Properties properties = new Properties();
            properties.load(in);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setName(rs.getString(1));
                user.setSurName(rs.getString(2));
                user.setEmail(rs.getString(3));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getOne(String email) {
        try {
            PreparedStatement st = connection
                    .prepareStatement("SELECT * FROM users WHERE email = ?");
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                User user = initUser(rs);
               return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        try{
            PreparedStatement st = connection
                    .prepareStatement("insert into users values (?,?,?)");
            st.setString(1, user.getName());
            st.setString(2, user.getSurName());
            st.setString(3, user.getEmail());
            st.execute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private User initUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setName(rs.getString("name"));
        user.setSurName(rs.getString("surname"));
        user.setEmail(rs.getString("email"));
        return user;
    }
}
