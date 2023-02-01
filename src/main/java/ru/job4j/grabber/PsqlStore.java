package ru.job4j.grabber;

import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store {
    private Connection connection;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("driver-name"));
            connection = DriverManager.getConnection(
                    cfg.getProperty("url"),
                    cfg.getProperty("username"),
                    cfg.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private Post getPost(ResultSet rs) throws SQLException {
        return new Post(rs.getInt("id"), rs.getString("name"),
                rs.getString("text"), rs.getString("link"),
                rs.getTimestamp("created").toLocalDateTime());
    }

    @Override
    public void save(Post post) {
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into post (name, text, link, created) " +
                        "values (?, ?, ?, ?) on conflict (link) do nothing;", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getDescription());
            ps.setString(3, post.getLink());
            ps.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            ps.execute();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    post.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select * from post;")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(getPost(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Post findById(int id) {
        Post post = null;
        try (PreparedStatement ps = connection.prepareStatement("select * from post where id = ?;")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    post = getPost(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Properties config = new Properties();
        try (PsqlStore ps = new PsqlStore(config)) {
            HabrCareerParse hcp = new HabrCareerParse(new HabrCareerDateTimeParser());
            List<Post> listPosts = hcp.list("https://career.habr.com/vacancies/java_developer?page=");
            ps.save(listPosts.get(12));
            ps.save(listPosts.get(15));
            ps.save(listPosts.get(2));
            System.out.println(ps.findById(2));
            listPosts = ps.getAll();
            for (Post post : listPosts) {
                System.out.println(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
