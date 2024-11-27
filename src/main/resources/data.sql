create table department
(
    dept_name varchar(255) not null
        primary key,
    building  varchar(255) not null,
    budget    bigint       not null
);

create table instructor
(
    id        int          not null
        primary key,
    name      varchar(255) not null,
    dept_name varchar(255) not null,
    salary    int          not null,
    constraint instructor_dept_name
        foreign key (dept_name) references department (dept_name)
);

create table student
(
    id        int          not null
        primary key,
    name      varchar(255) not null,
    dept_name varchar(255) not null,
    tot_cred  int          not null,
    constraint student_dept_name
        foreign key (dept_name) references department (dept_name)
);

