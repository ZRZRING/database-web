create table university.course
(
    id        int            not null
        primary key,
    title     varchar(255)   not null,
    dept_name varchar(255)   not null,
    credits   decimal(10, 1) not null,
    constraint course_department_dept_name_fk
        foreign key (dept_name) references university.department (dept_name)
);

create table university.department
(
    dept_name varchar(255) not null
        primary key,
    building  varchar(255) not null,
    budget    bigint       not null
);

create table university.instructor
(
    id        int          not null
        primary key,
    name      varchar(255) not null,
    dept_name varchar(255) not null,
    salary    int          not null,
    constraint instructor_dept_name
        foreign key (dept_name) references university.department (dept_name)
);

create table university.student
(
    id        int            not null
        primary key,
    name      varchar(255)   not null,
    dept_name varchar(255)   not null,
    tot_cred  decimal(10, 1) null,
    constraint student_dept_name
        foreign key (dept_name) references university.department (dept_name)
);

create table university.enrollment
(
    student_id int            not null,
    course_id  int            not null,
    grade      decimal(10, 2) not null,
    primary key (student_id, course_id),
    constraint enrollment_course_fk
        foreign key (course_id) references university.course (id),
    constraint enrollment_student_fk
        foreign key (student_id) references university.student (id)
);

