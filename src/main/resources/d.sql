drop table userinfo;
create Table userInfo(
    user_id number,
    email varchar2(150) unique,
    pwd varchar2(150) not null,
    firstname varchar2(150) not null,
    lastName VARCHAR2(150) not null,
    PRIMARY KEY (user_id)
);
drop table question;
create table question(
    quest_ID number,
    contents varchar2(1500) not null,
    count_of_Answer number,
    create_date date,
    title varchar2(150),
    user_id number,
    primary key(quest_id),
    CONSTRAINT question_fk FOREIGN KEY(user_id) REFERENCES userinfo(user_id)
);
drop table answer;
create table answer(
    id number,
    contents varchar2(150),
    create_date date,
    quest_id number,
    user_id number,
    CONSTRAINT answer_pk PRIMARY KEY(id),
    CONSTRAINT answer_q_fk FOREIGN KEY(quest_id) REFERENCES question(quest_id),
    CONSTRAINT answer_u_fk FOREIGN key(user_id) REFERENCES userinfo(user_id)
);