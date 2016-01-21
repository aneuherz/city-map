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