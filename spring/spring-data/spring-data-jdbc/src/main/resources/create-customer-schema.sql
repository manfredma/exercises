CREATE TABLE customer (
   id INT GENERATED BY DEFAULT AS IDENTITY,
   first_Name VARCHAR(50) NOT NULL,
   dob DATE,
   PRIMARY KEY (id)
);