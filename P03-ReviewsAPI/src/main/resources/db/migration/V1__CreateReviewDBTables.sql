create table review(
review_id    int not null auto_increment primary key,
review_title varchar(300) not null,
review_text  varchar(10000) not null,
review_created_time timestamp not null,
review_rating tinyint(1) not null,
product_id int not null
);

create table product(

product_id    int not null auto_increment primary key,
product_name varchar(255) not null,
product_description  varchar(10000) not null

);

create table comment(

comment_id    int not null auto_increment primary key,
comment_text varchar(10000) not null,
comment_created_time timestamp not null,
review_id int not null
);