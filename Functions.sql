DELIMITER //
# Returneaza capacitatea stadioanelor echipelor din NBA
CREATE DEFINER=`root`@`localhost` FUNCTION `CapacityFunction`() RETURNS int
    READS SQL DATA
    DETERMINISTIC
BEGIN
DECLARE aux integer;
DECLARE bFinished integer default 0;
DECLARE  iCapacity INT;
DECLARE capacity_cursor  CURSOR FOR
	 Select capacity from locations;
DECLARE continue handler for NOT FOUND set bFinished = 1;

SET aux = 0;
OPEN capacity_cursor;
    capacity_loop: WHILE(bFinished = 0) DO #cat timp am informatii
					FETCH capacity_cursor INTO iCapacity;# transfer capacitatea intr-o variabila locala
                    IF bFinished = 1 THEN # daca nu mai sunt informatii disponibile, opresc loop-ul
						LEAVE capacity_loop;
					END IF;
                    SET aux = aux + iCapacity;# adaug capacitatea la suma
				END WHILE;
	close capacity_cursor;
RETURN aux;# returnez suma

END//
DELIMITER ;

DELIMITER //
# Returneaza numarul de meciuri care s-au jucat la o anumita data
CREATE DEFINER=`root`@`localhost` FUNCTION `GameDate`(input_date DATE) RETURNS int
    READS SQL DATA
    DETERMINISTIC
BEGIN
DECLARE aux integer;
DECLARE bFinished integer default 0;
DECLARE gameDATE VARCHAR(100);
DECLARE game_date_cursor  CURSOR FOR
	Select game_date from games where game_date = input_date; # preiau meciurile care s-au jucat la data specificata
DECLARE continue handler for NOT FOUND set bFinished = 1;

SET aux = 0;
OPEN game_date_cursor;
    game_date_loop: WHILE(bFinished = 0) DO
					FETCH game_date_cursor INTO gameDATE;# preiau informatia intr-o variabila
                    IF bFinished = 1 THEN# Daca nu mai sunt informatii, opresc loop-ul
						LEAVE game_date_loop;
					END IF;
                    SET aux = aux +1;# Cresc numarul de meciuri jucate cu o unitate
				END WHILE;
	close game_date_cursor;
RETURN aux;# Returnez numarul de meciuri jucate in data specificata

END//
DELIMITER ;

DELIMITER //
# Returnez numarul de persoane care sunt de un anumit gen, specificat ca data de intrare
CREATE DEFINER=`root`@`localhost` FUNCTION `GenderFunction`(input_gender VARCHAR(10)) RETURNS int
    READS SQL DATA
    DETERMINISTIC
BEGIN
DECLARE aux integer;
DECLARE bFinished integer default 0;
DECLARE numePERS VARCHAR(100);
DECLARE gender_cursor  CURSOR FOR
	Select name from club_personnel where gender = input_gender; #returneaza numele persoanei care este de genul specificat
DECLARE continue handler for NOT FOUND set bFinished = 1;

SET aux = 0;
OPEN gender_cursor;
    gender_loop: WHILE(bFinished = 0) DO
					FETCH gender_cursor INTO numePERS;# preiau intr-o variabila numele ei
				    IF bFinished = 1 THEN # Daca nu mai sunt informatii, opresc loop-ul
						LEAVE gender_loop;
                        END IF;
                    SET aux = aux + 1; # cresc numarul de persoane
				END WHILE;
	close gender_cursor;
RETURN aux;# returnez numarul de persoane care sunt de genul specificat

END//
DELIMITER ;