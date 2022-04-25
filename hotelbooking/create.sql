create table clients (id bigint not null, email varchar(50), is_organizer bit, name varchar(50), reservation_id bigint, primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table reservations (id bigint not null, end datetime, start datetime, room_id bigint, primary key (id)) engine=InnoDB;
create table room (id bigint not null, capacity integer, number integer, primary key (id)) engine=InnoDB;
alter table clients add constraint FK8v5om6yle8n3tl7ieg82yvs foreign key (reservation_id) references reservations (id);
alter table reservations add constraint FK5abbl7abbb5wne1e18gv8opwr foreign key (room_id) references room (id);
-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           8.0.27 - MySQL Community Server - GPL
-- SE du serveur:                Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Listage des données de la table hotelbooking.room : ~0 rows (environ)
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT IGNORE INTO `room` (`id`, `capacity`, `number`) VALUES
	(1, 4, 1);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
