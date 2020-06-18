--liquibase formatted sql

--changeset marwa-pfe:2020-1 runOnChange:true runAlways:false runInTransaction:true dbms:mysql failOnError:true
drop table if exists departement;
drop table if exists membredereunion;
drop table if exists personne;
drop table if exists reunion;

create table departement (id int not null auto_increment, nom varchar(254), primary key (id));
create table membredereunion (id int not null auto_increment, id_membre int not null, id_reunion int not null, primary key (id));
create table reunion (id int not null auto_increment, sujet varchar(254),qrcode varchar(254), date_deb datetime, date_fin datetime, primary key (id));
create table personne (id int not null auto_increment, id_dep int, matricule varchar(255) unique, prenom varchar(254), nom varchar(254), fonction varchar(254), type varchar(100), email varchar(100) unique, password varchar(254), cin varchar(254), primary key (id));

alter table personne add constraint FK_association1 foreign key (id_dep) references departement (id) on delete cascade on update cascade;
alter table membredereunion add constraint FK_Generalisation_3 foreign key (id_membre) references personne (id) on delete cascade on update cascade;
alter table membredereunion add constraint FK_Generalisation_4 foreign key (id_reunion) references reunion (id) on delete cascade on update cascade;

insert into departement (nom) values ('IT'), ('TestDepart'), ('test2'),('test3');
insert into reunion (sujet,qrcode,date_deb,date_fin) values ('firstreunion','firstQrcode','2020-04-12 14:00:00','2020-04-12 15:00:00');

--changeset marwa-pfe:2020-2 runOnChange:true runAlways:false runInTransaction:true dbms:mysql failOnError:true
alter table membredereunion add column is_present smallint default 0;