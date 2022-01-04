CREATE TABLE JERSEY(
jersey_id INT AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
brand VARCHAR(30) NOT NULL,
color VARCHAR(30) NOT NULL,
description VARCHAR(100),
PRIMARY KEY(jersey_id)
);

CREATE TABLE BASKET_CLUB(
club_id INT AUTO_INCREMENT,
jersey_id INT,
club_name VARCHAR(30) NOT NULL,
club_description VARCHAR(100),
PRIMARY KEY(club_id),
CONSTRAINT jersey_id FOREIGN KEY (jersey_id)
REFERENCES JERSEY(jersey_id)
);

CREATE TABLE CLUB_PERSONNEL(
person_id INT auto_increment,
club_id INT,
name VARCHAR(100) NOT NULL,
title VARCHAR(20),
gender VARCHAR(10),
address VARCHAR(120) NOT NULL,
email VARCHAR(32) NOT NULL,
phone_number VARCHAR(20) NOT NULL,
PRIMARY KEY(person_id),
CONSTRAINT club_id FOREIGN KEY (club_id)
references BASKET_CLUB(club_id)
);

CREATE TABLE LOCATIONS(
location_id INT AUTO_INCREMENT,
name VARCHAR(100) NOT NULL,
address VARCHAR(120) NOT NULL,
capacity INT NOT NULL,
PRIMARY KEY(location_id)
);

CREATE TABLE GAMES(
games_id INT AUTO_INCREMENT,
club_id INT,
location_id INT,
opponent_club_id INT,
game_date DATE,
game_description VARCHAR(120),
game_result VARCHAR(10) NOT NULL,
PRIMARY KEY(games_id),
CONSTRAINT location_id_FK FOREIGN KEY (location_id)
references LOCATIONS(location_id),
CONSTRAINT club_id_FK FOREIGN KEY (club_id)
references BASKET_CLUB(club_id),
CONSTRAINT opponent_club_id_FK FOREIGN KEY (opponent_club_id)
references BASKET_CLUB(club_id)
);

CREATE TABLE JERSEY_BIN(
jersey_id INT ,
name VARCHAR(50) NOT NULL,
brand VARCHAR(30) NOT NULL,
color VARCHAR(30) NOT NULL,
description VARCHAR(100),
PRIMARY KEY(jersey_id)
);
