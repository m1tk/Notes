
create table account (
    id int not null auto_increment primary key,
    is_admin boolean not null,
    email varchar(40) not null,
    pass text not null,
    unique(email)
);

create table note (
    id bigint not null auto_increment primary key,
    date datetime not null,
    title text not null,
    body text not null,
    account_id int not null,
    constraint `fk_note_account`
        foreign key (account_id) references account (id)
        on delete cascade
        on update restrict
);

-- Admin account with 123 password
insert into account (is_admin, email, pass) values (1, "admin@admin.com", "$argon2id$v=19$m=15360,t=2,p=1$niujkTZvDhebTdr20LfZWtUYAnUQfmS4gRpiktIC9ic$4ti0o5q6wvu2x2QEYsLnjKyjpcl3l1l9OO9ecAppUdorwobHumJMeuO7+OAZpIN6YAW2yQ/Aw36wt+ZTcFj5nw");

