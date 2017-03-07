use board;

create table member(
	memberid varchar(50) not null primary key,
	name varchar(50) not null,
	password varchar(10) not null,
	regdate datetime not null
) CHARSET=utf8;

select * from member;
select * from member where memberid = 'bominem';
update member set password='aaa' where memberid = 'bominem';

create table article(
	article_no int(11) not null auto_increment,
	writer_id varchar(50) not null,
	writer_name varchar(50) not null,
	title varchar(255) not null,
	regdate datetime not null,
	moddate datetime not null,
	read_cnt integer default null,
	primary key(article_no)
) charset = utf8;

create table article_content(
	article_no int(11) not null,
	content text,
	primary key(article_no)
) charset = utf8;

use board;

select * from article;
select * from article_content where article_no = 1;
select * from article_content;

update article set title='안녕하세요하하하' where article_no = 2;
update article_content set content='테스트 1 입니다.' where article_no = 1;
update article_content set content='테스트 2 입니다.' where article_no = 2;
update article_content set content='테스트 3 입니다.' where article_no = 3;



-- 하하하