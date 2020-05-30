package com.gnevanov.BooksAndFilmsBoot.dao;

import com.gnevanov.BooksAndFilmsBoot.models.Book;
import com.gnevanov.BooksAndFilmsBoot.utilities.BookMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@PropertySource(value = "classpath:application.properties")
public class BookDAOImpl implements BookDAO {

    private DataSource dataSource;
    private SessionFactory sessionFactory;
    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setDataSource(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Book book) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "INSERT INTO schema_baf.books(id,name,author,description) VALUES (?,?,?,?)";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(sql, book.getId(),
                    book.getName(),
                    book.getAuthor(),
                    book.getDescription());
        } else {
            Session session = sessionFactory.getCurrentSession();
            session.persist(book);
        }
    }

    @Override
    public void update(Book book) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "UPDATE schema_baf.books SET name = ?, author = ?, description = ? WHERE id = ?";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(sql,
                    book.getName(),
                    book.getAuthor(),
                    book.getDescription(),
                    book.getId());
        } else {
            Session session = sessionFactory.getCurrentSession();
            session.update(book);
        }
    }

    @Override
    public void delete(Book book) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "DELETE FROM schema_baf.books WHERE id = ?";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(sql, book.getId());
        } else {
            Session session = sessionFactory.getCurrentSession();
            session.delete(book);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> getAllBooks() {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "SELECT * FROM schema_baf.books";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            return jdbcTemplate.query(sql, new BookMapper());
        } else {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("from Book").list();
        }
    }

    @Override
    public Book getBookById(int id) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "SELECT * FROM schema_baf.books WHERE id = ?";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            return (Book) jdbcTemplate.queryForObject(sql, new Object[]{id}, new BookMapper());
        } else {
            Session session = sessionFactory.getCurrentSession();
            return session.get(Book.class, id);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> getBooksByName(String name) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "SELECT * FROM schema_baf.books WHERE name LIKE ?";
            return getListOfBooksByParams(sql, new Object[]{name});
        } else {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("FROM Book WHERE name LIKE :name");
            query.setParameter("name", name);
            return (List<Book>) query.list();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> getBooksByAuthor(String author) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "SELECT * FROM schema_baf.books WHERE author LIKE ?";
            return getListOfBooksByParams(sql, new Object[]{author});
        } else {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("FROM Book WHERE author LIKE :author");
            query.setParameter("author", author);
            return (List<Book>) query.list();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> getBooksByNameAndAuthor(String name, String author) {
        if (Boolean.parseBoolean(environment.getProperty("db.useJdbcTemplate"))) {
            String sql = "SELECT * FROM schema_baf.books WHERE name LIKE ? AND author LIKE ?";
            return getListOfBooksByParams(sql, new Object[]{name, author});
        } else {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("FROM Book WHERE name LIKE :name AND author LIKE :author");
            query.setParameter("name", name);
            query.setParameter("author", author);
            return (List<Book>) query.list();
        }
    }

    public List<Book> getListOfBooksByParams(String sql, Object[] paramsArray) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Book> bookList = new ArrayList<Book>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, paramsArray);
        for (Map<String, Object> row: rows) {
            Book book = new Book();
            book.setId((Integer) row.get("id"));
            book.setName((String)row.get("name"));
            book.setAuthor((String)row.get("author"));
            book.setDescription((String)row.get("description"));
            bookList.add(book);
        }

        return bookList;
    }
}
