package com.gnevanov.BooksAndFilmsBoot.tasks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.gnevanov.BooksAndFilmsBoot.models.Book;
import com.gnevanov.BooksAndFilmsBoot.models.Film;
import com.gnevanov.BooksAndFilmsBoot.services.BookService;
import com.gnevanov.BooksAndFilmsBoot.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component("scheduledTasks")
public class ScheduledTasks {

    private BookService bookService;
    private FilmService filmService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }

    @Scheduled(fixedRate = 6000, initialDelay = 1000)
    public void exportDataToJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Book> bookList = bookService.getAllBooks();
        List<Film> filmList = filmService.getAllFilms();
        try {
            Files.writeString(Paths.get("C:/temp/books.json"), objectMapper.writeValueAsString(bookList));
            Files.writeString(Paths.get("C:/temp/films.json"), objectMapper.writeValueAsString(filmList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 10000, initialDelay = 2000)
    public void exportDataToXML() {
        List<Book> bookList = bookService.getAllBooks();
        writeListToXML("books", bookList);
        List<Film> filmList = filmService.getAllFilms();
        writeListToXML("films", filmList);
    }

    private void writeListToXML(String fileName, List<?> list) {

        XmlMapper xmlMapper = new XmlMapper();
        File xmlOutput = new File("C:/temp/" + fileName + ".xml");
        try (FileWriter fileWriter = new FileWriter(xmlOutput)) {
            for (Object object: list) {
                fileWriter.write(xmlMapper.writeValueAsString(object));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
