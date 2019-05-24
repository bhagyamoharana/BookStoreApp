# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table addresses (
  id                            bigint auto_increment not null,
  street_address                varchar(255),
  city                          varchar(255),
  state                         varchar(255),
  country                       varchar(255),
  zip_code                      integer,
  user_email                    varchar(255),
  constraint uq_addresses_user_email unique (user_email),
  constraint pk_addresses primary key (id)
);

create table answers_table (
  email_c                       varchar(255),
  q1                            varchar(255),
  q2                            varchar(255),
  q3                            varchar(255),
  q4                            varchar(255),
  q5                            varchar(255),
  q6                            varchar(255),
  q7                            varchar(255),
  q8                            varchar(255),
  q9                            varchar(255),
  q10                           varchar(255)
);

create table books (
  id                            integer auto_increment not null,
  title                         varchar(255),
  price                         integer,
  cover                         varchar(255),
  pdf                           varchar(255),
  details                       LONGTEXT,
  author_email                  varchar(255) not null,
  constraint pk_books primary key (id)
);

create table books_tags (
  books_id                      integer not null,
  tags_id                       integer not null,
  constraint pk_books_tags primary key (books_id,tags_id)
);

create table booksdata (
  isbn                          varchar(255) not null,
  title                         varchar(255),
  author                        varchar(255),
  tags                          varchar(255),
  yearofpublication             integer,
  publisher                     varchar(255),
  coverimage                    varchar(255),
  url                           varchar(255),
  constraint pk_booksdata primary key (isbn)
);

create table customer_users (
  email                         varchar(255) not null,
  name                          varchar(255),
  password                      varchar(255),
  city                          varchar(255),
  country                       varchar(255),
  gender                        varchar(255),
  age                           varchar(255),
  constraint pk_customer_users primary key (email)
);

create table customer_address (
  id                            bigint auto_increment not null,
  city                          varchar(255),
  state                         varchar(255),
  country                       varchar(255),
  customer_email                varchar(255),
  constraint uq_customer_address_customer_email unique (customer_email),
  constraint pk_customer_address primary key (id)
);

create table userprofile (
  user_id                       integer auto_increment not null,
  age                           integer not null,
  country_code                  integer not null,
  constraint pk_userprofile primary key (user_id)
);

create table livetable2 (
  bookrating                    integer,
  age                           integer,
  country_code                  varchar(255),
  title                         varchar(255),
  author                        varchar(255),
  coverimage                    varchar(255),
  tags                          varchar(255),
  url                           varchar(255)
);

create table questions (
  q_id                          integer auto_increment not null,
  question                      varchar(255),
  constraint pk_questions primary key (q_id)
);

create table ratings (
  user_id                       integer auto_increment not null,
  isbn                          integer not null,
  bookrating                    integer not null,
  constraint pk_ratings primary key (user_id)
);

create table tags (
  id                            integer auto_increment not null,
  name                          varchar(255),
  description                   TEXT,
  constraint pk_tags primary key (id)
);

create table users (
  email                         varchar(255) not null,
  name                          varchar(255),
  password                      varchar(255),
  constraint pk_users primary key (email)
);

alter table addresses add constraint fk_addresses_user_email foreign key (user_email) references users (email) on delete restrict on update restrict;

create index ix_books_author_email on books (author_email);
alter table books add constraint fk_books_author_email foreign key (author_email) references users (email) on delete restrict on update restrict;

create index ix_books_tags_books on books_tags (books_id);
alter table books_tags add constraint fk_books_tags_books foreign key (books_id) references books (id) on delete restrict on update restrict;

create index ix_books_tags_tags on books_tags (tags_id);
alter table books_tags add constraint fk_books_tags_tags foreign key (tags_id) references tags (id) on delete restrict on update restrict;

alter table customer_address add constraint fk_customer_address_customer_email foreign key (customer_email) references customer_users (email) on delete restrict on update restrict;


# --- !Downs

alter table addresses drop foreign key fk_addresses_user_email;

alter table books drop foreign key fk_books_author_email;
drop index ix_books_author_email on books;

alter table books_tags drop foreign key fk_books_tags_books;
drop index ix_books_tags_books on books_tags;

alter table books_tags drop foreign key fk_books_tags_tags;
drop index ix_books_tags_tags on books_tags;

alter table customer_address drop foreign key fk_customer_address_customer_email;

drop table if exists addresses;

drop table if exists answers_table;

drop table if exists books;

drop table if exists books_tags;

drop table if exists booksdata;

drop table if exists customer_users;

drop table if exists customer_address;

drop table if exists userprofile;

drop table if exists livetable2;

drop table if exists questions;

drop table if exists ratings;

drop table if exists tags;

drop table if exists users;

