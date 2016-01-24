CREATE ROLE citymap_user LOGIN
  PASSWORD 'citymap_user'
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;

CREATE ROLE citymap_select;
CREATE ROLE tickets_select;

--GRANT SELECT AT SCHEMA citymap TO CITYMAP-SELECT
GRANT USAGE ON SCHEMA citymap TO citymap_select;
GRANT SELECT ON ALL TABLES IN SCHEMA citymap TO citymap_select;

--GRANT SELECT AT SCHEMA tickets TO TICKETS-SELECT
GRANT USAGE ON SCHEMA tickets TO tickets_select;
GRANT SELECT ON ALL TABLES IN SCHEMA tickets TO tickets_select;

--the JPA user should be allowed to SELECT from all tables in the schemes CITYMAP and TICKET

--GRANT citymap_select TO citymap_user;
GRANT tickets_select TO citymap_user;

--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--
--!!ALL OTHER ACCESS HAS TO BE GRANTED EXPLICITLY TO THE USER!!--
--!!WHEN REQUIRED. INSERT, DELETE AND UPDATE IS DENIED IF NOT!!--                                             !!--
--!!NEEDED. - neuherza, 18.01.2016                           !!--
--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--
ALTER SEQUENCE citymap.line_id_seq OWNER TO citymap_user;
ALTER SEQUENCE citymap.ride_id_seq   OWNER TO citymap_user;
ALTER SEQUENCE citymap.ridetype_id_seq OWNER TO citymap_user;
ALTER SEQUENCE citymap.station_id_seq   OWNER TO citymap_user;
ALTER SEQUENCE citymap.vehicle_id_seq  OWNER TO citymap_user;
--TEMP HACK, NO BEAUTIFUL SOLUTION
GRANT INSERT ON ALL TABLES IN SCHEMA citymap TO citymap_user;
GRANT EXECUTE ON FUNCTION citymap.setup() TO citymap_user;
GRANT EXECUTE ON FUNCTION citymap.teardown() TO citymap_user;
GRANT USAGE, UPDATE, SELECT ON ALL SEQUENCES IN SCHEMA citymap TO citymap_user;
GRANT TRUNCATE ON ALL TABLES IN SCHEMA citymap TO citymap_user;