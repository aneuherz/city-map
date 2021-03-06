CREATE OR REPLACE FUNCTION citymap.fill_ride_on_day(rideid integer, minutes integer)
RETURNS void AS $$
DECLARE
        endofday timestamp := current_date + 1 - interval '1 second';
        ride_time timestamp := current_date + interval '4 hours' + interval '1 minute' * minutes;
BEGIN
LOOP
    IF ride_time > endofday THEN
        EXIT;  -- exit loop
    END IF;
    INSERT INTO citymap.rideonday(
            ride_id, ridestarttime)
    VALUES (rideid, ride_time);
    RAISE NOTICE 'Time %', ride_time;
    ride_time := ride_time + interval '1 minute' * minutes;
END LOOP;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION citymap.setup()
  RETURNS void AS
$BODY$
BEGIN
  INSERT INTO citymap.vehicle(description)
  VALUES ('bus');
  INSERT INTO citymap.vehicle(description)
  VALUES ('tram');


  INSERT INTO citymap.ridetype(type)
  VALUES ('Monday to Friday');
  INSERT INTO citymap.ridetype(type)
  VALUES ('Saturday');
  INSERT INTO citymap.ridetype(type)
  VALUES ('Sunday and legal holidays');

  INSERT INTO citymap.line(description, vehicle_id)
  VALUES ('Tram 1',2);

  INSERT INTO citymap.line(description, vehicle_id)
  VALUES ('Tram 2',2);


  INSERT INTO citymap.line(description, vehicle_id)
  VALUES ('Tram 3',2);

  INSERT INTO citymap.line(description, vehicle_id)
  VALUES ('Bus 4',1);

  INSERT INTO citymap.line(description, vehicle_id)
  VALUES ('Bus 5',1);

  INSERT INTO citymap.station(description)
  VALUES ('Jakominiplatz'),('Hauptplatz'),('Esperantoplatz/Arbeiterkammer'),('Roseggerhaus'),(
    'Südtirolerplatz/Kunsthaus'),('Dietrichsteinplatz'),('Neue Technik'),('Keplerbrücke'),('Finanzamt'),('Steyrergasse'),
    ('Kaiser-Franz-Josef-Platz/Oper'),('Maiffredygasse'),('Lichtenfelsgasse/Kunstuni'),('Zinzendorfgasse'),('Bad zur Sonne'),('Andreas-Hofer-Platz'),('Wielandgasse'),('Griesplatz'), ('Volksgartenstraße');

  INSERT INTO citymap.vehicle_station(
    station_id, vehicle_id)
  VALUES  (1, 1),(1, 2),(2, 2),(3, 2),
    (4, 1),(4, 2),(5, 2),(6, 2),
    (7, 2),(8, 2),(9, 2),(10, 2),
    (11, 1),(11, 2),(12, 1),(12, 2),
    (13, 2),(14, 1),(15, 1),(16, 1),
    (17, 1),(18, 1),(19, 1);

  INSERT INTO citymap.ride(description, line_id, ridetype_id)
  VALUES ('Esperantoplatz-Neue Technik', 3, 1),
    ('Neue Technik-Esperantoplatz', 3, 1),
    ('Volksgartenstraße-Jakominiplatz', 4, 1),
    ('Jakominiplatz-Volksgartenstraße', 4, 1),
    ('Griesplatz-Zinzendorfgasse', 5, 1),
    ('Zinzendorfgasse-Griesplatz', 5, 1);

  PERFORM citymap.fill_ride_on_day(1,11);
  PERFORM citymap.fill_ride_on_day(2,13);
  PERFORM citymap.fill_ride_on_day(3,27);
  PERFORM citymap.fill_ride_on_day(4,28);
  PERFORM citymap.fill_ride_on_day(5,21);
  PERFORM citymap.fill_ride_on_day(6,19);

  INSERT INTO citymap.stop(
    ride_id, halt_no, station_id, waittime, timetonextstop)
  VALUES (1, 1, 3, 0, 2),(1, 2, 4, 1, 2),(1, 3, 5, 1, 2),
    (1, 4, 2, 1, 2),(1, 5, 1, 1, 1),(1, 6, 6, 1, 3),
    (1, 7, 7, 0, null);
  INSERT INTO citymap.stop(
    ride_id, halt_no, station_id, waittime, timetonextstop)
  VALUES (2, 7, 3, 0, null),(2, 6, 4, 1, 2),(2, 5, 5, 1, 2),
    (2, 4, 2, 1, 2),(2, 3, 1, 1, 2),(2, 2, 6, 1, 1),
    (2, 1, 7, 0, 3);
  INSERT INTO citymap.stop(
    ride_id, halt_no, station_id, waittime, timetonextstop)
  VALUES (3, 1, 19, 0, 1),(3, 2, 4, 1, 2),(3, 3, 15, 1, 3),
    (3, 4, 16, 1, 3),(3, 5, 17, 1, 2),(3, 6, 1, 0, null);

  INSERT INTO citymap.stop(
    ride_id, halt_no, station_id, waittime, timetonextstop)
  VALUES (4, 6, 19, 0, null),(4, 5, 4, 1, 1),(4, 4, 15, 1, 2),
    (4, 3, 16, 1, 3),(4, 2, 17, 1, 3),(4, 1, 1, 0, 2);
  INSERT INTO citymap.stop(
    ride_id, halt_no, station_id, waittime, timetonextstop)
  VALUES (5, 1, 18, 0, 3),(5, 2, 17, 1, 2),(5, 3, 1, 1, 1),
    (5, 4, 11, 1, 1),(5, 5, 12, 1, 2),(5, 6, 14, 0, null);
  INSERT INTO citymap.stop(
    ride_id, halt_no, station_id, waittime, timetonextstop)
  VALUES (6, 6, 18, 0, null),(6, 5, 17, 1, 3),(6, 4, 1, 1, 2),
    (6, 3, 11, 1, 1),(6, 2, 12, 1, 1),(6, 1, 14, 0, 2);
END;
$BODY$
LANGUAGE plpgsql VOLATILE
COST 100;
ALTER FUNCTION citymap.setup()
OWNER TO postgres;
GRANT EXECUTE ON FUNCTION citymap.setup() TO public;
GRANT EXECUTE ON FUNCTION citymap.setup() TO postgres;
GRANT EXECUTE ON FUNCTION citymap.setup() TO citymap_user;

CREATE OR REPLACE FUNCTION citymap.teardown()
  RETURNS void AS
$BODY$
BEGIN
  TRUNCATE TABLE citymap.delay  CASCADE;
  TRUNCATE TABLE citymap.line  CASCADE;
  TRUNCATE TABLE citymap.ride CASCADE;
  TRUNCATE TABLE citymap.rideonday  CASCADE;
  TRUNCATE TABLE citymap.ridetype  CASCADE;
  TRUNCATE TABLE citymap.station  CASCADE;
  TRUNCATE TABLE citymap.stop  CASCADE;
  TRUNCATE TABLE citymap.vehicle CASCADE;
  TRUNCATE TABLE citymap.stop  CASCADE;
  TRUNCATE TABLE citymap.vehicle_station  CASCADE;

  ALTER SEQUENCE citymap.line_id_seq RESTART;
  ALTER SEQUENCE citymap.ride_id_seq RESTART;
  ALTER SEQUENCE citymap.ridetype_id_seq RESTART;
  ALTER SEQUENCE citymap.station_id_seq RESTART;
  ALTER SEQUENCE citymap.vehicle_id_seq RESTART;

END;
$BODY$
LANGUAGE plpgsql VOLATILE
COST 100;
ALTER FUNCTION citymap.teardown()
OWNER TO postgres;
GRANT EXECUTE ON FUNCTION citymap.teardown() TO public;
GRANT EXECUTE ON FUNCTION citymap.teardown() TO postgres;
GRANT EXECUTE ON FUNCTION citymap.teardown() TO citymap_user;