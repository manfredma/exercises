package manfred.end.rdbms.mybatis;

public class Book {

    private Long id;
    private String author;
    private String title;
    private int published;
    private String remark;
    
    public Book() {};

    public Book(String author, String title, int published, 
            String remark) {
        
        this.author = author;
        this.title = title;
        this.published = published;
        this.remark = remark;
    }    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", author=" + author + ", "
                + "title=" + title + ", published=" + published 
                + ", remark=" + remark + '}';
    }    
}