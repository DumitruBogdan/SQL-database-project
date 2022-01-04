DELIMITER // 
# afiseaza numele echipei al carei echipament este de culoarea specificata ca parametru
CREATE DEFINER=`root`@`localhost` PROCEDURE `Jersey`(input_color VARCHAR(30))
BEGIN
DECLARE clubNAME VARCHAR(30);

DECLARE bFinished integer default 0;
DECLARE jersey_color_cursor CURSOR FOR
SELECT club_name FROM basket_club WHERE club_id = ( SELECT club_id from jersey WHERE color = input_color) ;# preiau numele clubului al carui echipament are culoarea specificata in parametrul de intrare

DECLARE continue handler for NOT FOUND set bFinished = 1;

	OPEN jersey_color_cursor;# deschid cursorul
    jersey_color_loop: WHILE(bFinished = 0) DO # cat timp mai am informatii
					FETCH jersey_color_cursor INTO clubNAME;# se transfera informatia din cursor intr-o variabila
                    select clubNAME;# afisez numele clublui
				END WHILE;
	close jersey_color_cursor;# inchid cursorul
END//

DELIMITER ;

DELIMITER //
# afiseaza pentru ce club lucreaza persoana identificata prin nume
CREATE DEFINER=`root`@`localhost` PROCEDURE `Person`(input_name VARCHAR(100))
BEGIN
DECLARE clubNAME VARCHAR(30);

DECLARE bFinished integer default 0;
DECLARE person_identifier_cursor CURSOR FOR
SELECT club_name FROM basket_club WHERE club_id = ( SELECT club_id from club_personnel WHERE name = input_name); # citesc numele persoanei, care este parametru de intrare

DECLARE continue handler for NOT FOUND set bFinished = 1;

	OPEN person_identifier_cursor;
    person_identifier_loop: WHILE(bFinished = 0) DO # cat timp mai am informatii
					FETCH person_identifier_cursor INTO clubNAME; #preiau numele clubului
                    select clubNAME;# afisez numele clublui
				END WHILE;
	close person_identifier_cursor;# inchid cursorul
END //
DELIMITER ;

DELIMITER //
# afiseaza ultimul meci jucat de o echipa identificata prin id
CREATE DEFINER=`root`@`localhost` PROCEDURE `Result`(input_clubID INT)
BEGIN
DECLARE locationID INT;
DECLARE gameDATA DATE;

DECLARE aux INT;

DECLARE bFinished integer default 0;
DECLARE games_cursor CURSOR FOR
SELECT location_id, game_date FROM games WHERE club_id = input_clubID OR opponent_club_id = input_clubID; # preiau in cursor locatia si ziua meciului in care echipa cu id-ul specificat ca parametru de intrare a jucat

DECLARE continue handler for NOT FOUND set bFinished = 1;

SET aux = 0;
	OPEN games_cursor;
    games_loop: WHILE(bFinished = 0 AND aux = 0) DO # cat timp am informatii si nu am afisat vreuna
					FETCH games_cursor INTO  locationID, gameDATA;# transfer informatiile in variabile
                    select locationID, gameDATA; #si afisez id-ul locatiei si ziua in care s-a jucat meciul
                    SET aux = aux + 1; #cresc contorul
				END WHILE;
	close games_cursor;
END
DELIMITER ;