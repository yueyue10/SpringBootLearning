package forezp.dao.impl;

import forezp.dao.BookRepository;
import forezp.entity.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Created by fangzhipeng on 2017/4/19.
 */
@Component
public class SimpleBookRepository implements BookRepository {

    @Override
    @Cacheable("book")
    public Book getByIsbn(String isbn) {
        String tag = simulateSlowService();
        return new Book(isbn, tag + "创建的book");
    }

    // Don't do this at home
    private String simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
            int flag = (int) (Math.random() * 1000);
            return "flag=" + flag;
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}