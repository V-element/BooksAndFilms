use schema_baf;

INSERT INTO schema_baf.books
(name,author,description)
VALUES
('Book#1','Author##2','Some description example for book#1'),
('Book#2','Author##5','Some description example for book#2'),
('Book#3','Author##1','Some description example for book#3');

INSERT INTO schema_baf.films
(name,producer,description,year)
VALUES
('Film#1','Producer##2','Some description example for film#1',2015),
('Film#2','Producer##5','Some description example for film#2',2011),
('Film#3','Producer##1','Some description example for film#3',2020);