create table category (
    catid char(20) not null,
    locale varchar(10) not null,
    name varchar(80) null,
    description varchar(255) null,
    imageuri varchar(80) null,
    constraint pk_category primary key (catid, locale)
)
;

create table lodging (
    lodgingid char(20) not null,
    locale varchar(10) not null,
    location varchar(30) not null,
    name varchar(80) not null,
    description varchar(255) not null,    
    price decimal(10,2) null,
    imageuri varchar(80) not null,   
    constraint pk_lodging primary key (lodgingid , locale )
)
;

create table package (
    packageid char(20) not null,
    catid char(20) not null,
    locale varchar(10) not null,
    location varchar(30) not null,
    price decimal(10,2) not null,
    name varchar(80) null,
    description varchar(255) null,
    imageuri varchar(80) not null,
    lodgingid char(20) not null,
    constraint pk_package primary key (packageid, locale) ,
    constraint fk_package_1 foreign key (catid, locale)
        references category (catid, locale),
    constraint fk_package_2 foreign key (lodgingid, locale)
        references lodging (lodgingid, locale)
)
;

create table activity (
    activityid char(20) not null,
    locale varchar(10) not null,
    location varchar(30) not null,
    name varchar(80) not null,
    description varchar(255) not null,    
    price decimal(10,2) null,
    imageuri varchar(80) not null,   
    constraint pk_activity primary key (activityid , locale )
)
;

create table activitylist (
    packageid char(20) not null,
    activityid char(20) not null,
    locale varchar(10) not null,
    constraint pk_activitylist primary key (packageid , activityid ,locale),
    constraint fk_activitylist_1 foreign key (packageid , locale)
        references package (packageid, locale),
    constraint fk_activitylist_2 foreign key (activityid , locale)
        references activity(activityid , locale)
)
;

create table transportation (
    transportationid char(20) not null,
    locale varchar(10) not null,
    origin varchar(30) not null,
    destination varchar(30) not null,
    carrier varchar(80) not null,
    name varchar(80) not null,
    departuretime varchar(80) not null, 
    arrivaltime varchar(80) not null,
    description varchar(255) not null,  
    class varchar(20) not null,
    price decimal(10,2) null,
    imageuri varchar(80) not null,   
    constraint pk_transportation primary key (transportationid , locale )
)
;

create table signon (
    username varchar(25) not null,
    password varchar(25)  not null,
    constraint pk_signon primary key (username)
)
;

create table account (
    userid varchar(80) not null,
    email varchar(80) not null,
    firstname varchar(80) not null,
    lastname varchar(80) not null,
    addr1 varchar(80) not null,
    addr2 varchar(40) null,
    city varchar(80) not  null,
    state varchar(80) not null,
    zip varchar(20) not null,
    country varchar(20) not null,
    phone varchar(80) not null,
    constraint pk_account primary key (userid)
)
;
