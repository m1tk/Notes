
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
