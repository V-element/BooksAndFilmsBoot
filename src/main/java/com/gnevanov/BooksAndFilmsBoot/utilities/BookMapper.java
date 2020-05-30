package com.gnevanov.BooksAndFilmsBoot.utilities;

import com.gnevanov.BooksAndFilmsBoot.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("ID"));
        book.setName(resultSet.getString("NAME"));
        book.setAuthor(resultSet.getString("AUTHOR"));
        book.setDescription(resultSet.getString("DESCRIPTION"));
        return book;
    }
}
