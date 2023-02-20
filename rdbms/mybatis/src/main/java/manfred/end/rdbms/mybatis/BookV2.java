package manfred.end.rdbms.mybatis;

public class BookV2 {

    private Long id;
    private Book book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BookV2{" +
                "id=" + id +
                ", book=" + book +
                '}';
    }
}