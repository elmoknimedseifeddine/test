create table clients (id bigint not null, email varchar(50), is_organizer bit, name varchar(50), reservation_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table reservations (id bigint not null, end datetime, start datetime, room_id bigint, primary key (id)) engine=InnoDB;
create table room (id bigint not null, capacity integer, number integer, primary key (id)) engine=InnoDB;
alter table clients add constraint FK8v5om6yle8n3tl7ieg82yvs foreign key (reservation_id) references reservations (id);
alter table reservations add constraint FK5abbl7abbb5wne1e18gv8opwr foreign key (room_id) references room (id);
create table clients (id bigint not null, email varchar(50), is_organizer bit, name varchar(50), reservation_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table reservations (id bigint not null, end datetime, start datetime, room_id bigint, primary key (id)) engine=InnoDB;
create table room (id bigint not null, capacity integer, number integer, primary key (id)) engine=InnoDB;
alter table clients add constraint FK8v5om6yle8n3tl7ieg82yvs foreign key (reservation_id) references reservations (id);
alter table reservations add constraint FK5abbl7abbb5wne1e18gv8opwr foreign key (room_id) references room (id);
create table clients (id bigint not null, email varchar(50), is_organizer bit, name varchar(50), reservation_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table reservations (id bigint not null, end datetime, start datetime, room_id bigint, primary key (id)) engine=InnoDB;
create table room (id bigint not null, capacity integer, number integer, primary key (id)) engine=InnoDB;
alter table clients add constraint FK8v5om6yle8n3tl7ieg82yvs foreign key (reservation_id) references reservations (id);
alter table reservations add constraint FK5abbl7abbb5wne1e18gv8opwr foreign key (room_id) references room (id);
create table clients (id bigint not null, email varchar(50), is_organizer bit, name varchar(50), reservation_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table reservations (id bigint not null, end datetime, start datetime, room_id bigint, primary key (id)) engine=InnoDB;
create table room (id bigint not null, capacity integer, number integer, primary key (id)) engine=InnoDB;
alter table clients add constraint FK8v5om6yle8n3tl7ieg82yvs foreign key (reservation_id) references reservations (id);
alter table reservations add constraint FK5abbl7abbb5wne1e18gv8opwr foreign key (room_id) references room (id);
create table clients (id bigint not null, email varchar(50), is_organizer bit, name varchar(50), reservation_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table reservations (id bigint not null, end datetime, start datetime, room_id bigint, primary key (id)) engine=InnoDB;
create table room (id bigint not null, capacity integer, number integer, primary key (id)) engine=InnoDB;
alter table clients add constraint FK8v5om6yle8n3tl7ieg82yvs foreign key (reservation_id) references reservations (id);
alter table reservations add constraint FK5abbl7abbb5wne1e18gv8opwr foreign key (room_id) references room (id);
create table clients (id bigint not null, email varchar(50), is_organizer bit, name varchar(50), reservation_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table reservations (id bigint not null, end datetime, start datetime, room_id bigint, primary key (id)) engine=InnoDB;
create table room (id bigint not null, capacity integer, number integer, primary key (id)) engine=InnoDB;
alter table clients add constraint FK8v5om6yle8n3tl7ieg82yvs foreign key (reservation_id) references reservations (id);
alter table reservations add constraint FK5abbl7abbb5wne1e18gv8opwr foreign key (room_id) references room (id);
create table clients (id bigint not null, email varchar(50), is_organizer bit, name varchar(50), reservation_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table reservations (id bigint not null, end datetime, start datetime, room_id bigint, primary key (id)) engine=InnoDB;
create table room (id bigint not null, capacity integer, number integer, primary key (id)) engine=InnoDB;
alter table clients add constraint FK8v5om6yle8n3tl7ieg82yvs foreign key (reservation_id) references reservations (id);
alter table reservations add constraint FK5abbl7abbb5wne1e18gv8opwr foreign key (room_id) references room (id);
create table clients (id bigint not null, email varchar(50), is_organizer bit, name varchar(50), reservation_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table reservations (id bigint not null, end datetime, start datetime, room_id bigint, primary key (id)) engine=InnoDB;
create table room (id bigint not null, capacity integer, number integer, primary key (id)) engine=InnoDB;
alter table clients add constraint FK8v5om6yle8n3tl7ieg82yvs foreign key (reservation_id) references reservations (id);
alter table reservations add constraint FK5abbl7abbb5wne1e18gv8opwr foreign key (room_id) references room (id);
create table clients (id bigint not null, email varchar(50) not null, is_organizer bit, name varchar(50) not null, reservation_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table reservations (id bigint not null, end datetime, start datetime, room_id bigint, primary key (id)) engine=InnoDB;
create table room (id bigint not null, capacity integer, number integer, primary key (id)) engine=InnoDB;
alter table clients add constraint FK8v5om6yle8n3tl7ieg82yvs foreign key (reservation_id) references reservations (id);
alter table reservations add constraint FK5abbl7abbb5wne1e18gv8opwr foreign key (room_id) references room (id);
create table clients (id bigint not null, email varchar(50) not null, is_organizer bit, name varchar(50) not null, reservation_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table reservations (id bigint not null, end datetime, start datetime, room_id bigint, primary key (id)) engine=InnoDB;
create table room (id bigint not null, capacity integer, number integer, primary key (id)) engine=InnoDB;
alter table clients add constraint FK8v5om6yle8n3tl7ieg82yvs foreign key (reservation_id) references reservations (id);
alter table reservations add constraint FK5abbl7abbb5wne1e18gv8opwr foreign key (room_id) references room (id);
create table clients (id bigint not null, email varchar(50) not null, is_organizer bit, name varchar(50) not null, reservation_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table reservations (id bigint not null, end datetime, start datetime, room_id bigint, primary key (id)) engine=InnoDB;
create table room (id bigint not null, capacity integer, number integer, primary key (id)) engine=InnoDB;
alter table clients add constraint FK8v5om6yle8n3tl7ieg82yvs foreign key (reservation_id) references reservations (id);
alter table reservations add constraint FK5abbl7abbb5wne1e18gv8opwr foreign key (room_id) references room (id);
create table clients (id bigint not null, email varchar(50) not null, is_organizer bit, name varchar(50) not null, reservation_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table reservations (id bigint not null, end datetime, start datetime, room_id bigint, primary key (id)) engine=InnoDB;
create table room (id bigint not null, capacity integer, number integer, primary key (id)) engine=InnoDB;
alter table clients add constraint FK8v5om6yle8n3tl7ieg82yvs foreign key (reservation_id) references reservations (id);
alter table reservations add constraint FK5abbl7abbb5wne1e18gv8opwr foreign key (room_id) references room (id);
create table clients (id bigint not null, email varchar(50) not null, is_organizer bit, name varchar(50) not null, reservation_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table reservations (id bigint not null, end datetime, start datetime, room_id bigint, primary key (id)) engine=InnoDB;
create table room (id bigint not null, capacity integer, number integer, primary key (id)) engine=InnoDB;
alter table clients add constraint FK8v5om6yle8n3tl7ieg82yvs foreign key (reservation_id) references reservations (id);
alter table reservations add constraint FK5abbl7abbb5wne1e18gv8opwr foreign key (room_id) references room (id);
create table clients (id bigint not null, email varchar(50) not null, is_organizer bit, name varchar(50) not null, reservation_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table reservations (id bigint not null, end datetime, start datetime, room_id bigint, primary key (id)) engine=InnoDB;
create table room (id bigint not null, capacity integer, number integer, primary key (id)) engine=InnoDB;
alter table clients add constraint FK8v5om6yle8n3tl7ieg82yvs foreign key (reservation_id) references reservations (id);
alter table reservations add constraint FK5abbl7abbb5wne1e18gv8opwr foreign key (room_id) references room (id);
create table clients (id bigint not null, email varchar(50) not null, is_organizer bit, name varchar(50) not null, reservation_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table reservations (id bigint not null, end datetime, start datetime, room_id bigint, primary key (id)) engine=InnoDB;
create table room (id bigint not null, capacity integer, number integer, primary key (id)) engine=InnoDB;
alter table clients add constraint FK8v5om6yle8n3tl7ieg82yvs foreign key (reservation_id) references reservations (id);
alter table reservations add constraint FK5abbl7abbb5wne1e18gv8opwr foreign key (room_id) references room (id);
create table clients (id bigint not null, email varchar(50) not null, is_organizer bit, name varchar(50) not null, reservation_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table reservations (id bigint not null, end datetime, start datetime, room_id bigint, primary key (id)) engine=InnoDB;
create table room (id bigint not null, capacity integer, number integer, primary key (id)) engine=InnoDB;
alter table clients add constraint FK8v5om6yle8n3tl7ieg82yvs foreign key (reservation_id) references reservations (id);
alter table reservations add constraint FK5abbl7abbb5wne1e18gv8opwr foreign key (room_id) references room (id);
create table clients (id bigint not null, email varchar(50) not null, is_organizer bit, name varchar(50) not null, reservation_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table reservations (id bigint not null, end datetime, start datetime, room_id bigint, primary key (id)) engine=InnoDB;
create table room (id bigint not null, capacity integer, number integer, primary key (id)) engine=InnoDB;
alter table clients add constraint FK8v5om6yle8n3tl7ieg82yvs foreign key (reservation_id) references reservations (id);
alter table reservations add constraint FK5abbl7abbb5wne1e18gv8opwr foreign key (room_id) references room (id);
create table clients (id bigint not null, email varchar(50) not null, is_organizer bit, name varchar(50) not null, reservation_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table reservations (id bigint not null, end datetime, start datetime, room_id bigint, primary key (id)) engine=InnoDB;
create table room (id bigint not null, capacity integer, number integer, primary key (id)) engine=InnoDB;
alter table clients add constraint FK8v5om6yle8n3tl7ieg82yvs foreign key (reservation_id) references reservations (id);
alter table reservations add constraint FK5abbl7abbb5wne1e18gv8opwr foreign key (room_id) references room (id);
create table clients (id bigint not null, email varchar(50) not null, is_organizer bit, name varchar(50) not null, reservation_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table reservations (id bigint not null, end datetime, start datetime, room_id bigint, primary key (id)) engine=InnoDB;
create table room (id bigint not null, capacity integer, number integer, primary key (id)) engine=InnoDB;
alter table clients add constraint FK8v5om6yle8n3tl7ieg82yvs foreign key (reservation_id) references reservations (id);
alter table reservations add constraint FK5abbl7abbb5wne1e18gv8opwr foreign key (room_id) references room (id);
