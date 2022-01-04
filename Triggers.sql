
DELIMITER //
CREATE TRIGGER insertInJerseyBin
BEFORE DELETE ON jersey 
FOR EACH ROW
 BEGIN
 -- insereaza in tabelul bin informatiile sterse, in cazul in care acesta se vor a fi refolosite
	INSERT INTO jersey_bin(jersey_id, name, brand, color, description) VALUES (old.jersey_id, old.name, old.brand, old.color, old.description);
 END //
 DELIMITER ;
 
DELIMITER //
CREATE TRIGGER sameTeamName
BEFORE INSERT ON basket_club
FOR EACH ROW
BEGIN
    IF EXISTS(SELECT 1 FROM basket_club WHERE club_name = NEW.club_name) THEN 
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Exista deja o echipa cu numele asta';
    END IF;
END //

DELIMITER ;

insert basket_club(club_name, club_description) VALUES ('Los Angeles Clippers', 'TEST');


DELIMITER // 
-- Triggerul insereaza in tabelul reschedule log-ul schimbarii datei in care se desfasoara un meci.
CREATE TRIGGER rescheduleLogWork  
AFTER UPDATE ON games 
FOR EACH ROW  
BEGIN  
    INSERT into reschedule VALUES (OLD.games_id,   
    CONCAT('Previous date was:  ', OLD.game_date, '. Current date is :',  NEW.game_date));  
END // 
  
DELIMITER ;  

UPDATE games SET game_date = "2020/07/07" WHERE games_id = 10;
Select * from reschedule;