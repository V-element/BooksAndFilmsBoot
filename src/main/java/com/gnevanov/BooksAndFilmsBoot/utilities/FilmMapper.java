package com.gnevanov.BooksAndFilmsBoot.utilities;

import com.gnevanov.BooksAndFilmsBoot.models.Film;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Film film = new Film();
        film.setId(resultSet.getInt("ID"));
        film.setName(resultSet.getString("NAME"));
        film.setDescription(resultSet.getString("DESCRIPTION"));
        film.setProducer(resultSet.getString("PRODUCER"));
        film.setYear(resultSet.getShort("YEAR"));
        return film;
    }
}
