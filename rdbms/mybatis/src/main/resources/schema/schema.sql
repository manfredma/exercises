drop table IF EXISTS MyBooks;
CREATE TABLE MyBooks
(
    Id        INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Author    VARCHAR(30),
    Title     VARCHAR(60),
    Published INTEGER,
    Remark    VARCHAR(150),
    created   DATETIME
);

INSERT INTO MyBooks(Author, Title, Published, Remark) VALUES ('Leo Tolstoy', 'War and Peace', 1869, 'Napoleonic wars');
INSERT INTO MyBooks(Author, Title, Published, Remark) VALUES ('Leo Tolstoy', 'Anna Karenina', 1878, 'Greatest book of love');
INSERT INTO MyBooks(Author, Title, Published, Remark) VALUES ('Jeff Prosise', 'Programming Windows with MFC', 1999, 'Classic book about MFC');
INSERT INTO MyBooks(Author, Title, Published, Remark) VALUES ('Tom Marrs', 'JBoss at Work', 2005, 'JBoss practical guide');
INSERT INTO MyBooks(Author, Title, Published, Remark) VALUES ('Debu Panda', 'EJB3 in Action', 2007, 'Introduction to Enterprice Java Beans');