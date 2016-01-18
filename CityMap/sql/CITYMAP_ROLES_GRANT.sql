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
GRANT citymap_select TO citymap_user;
GRANT tickets_select TO citymap_user;

--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--
--!!ALL OTHER ACCESS HAS TO BE GRANTED EXPLICITLY TO THE USER!!--
--!!WHEN REQUIRED. INSERT, DELETE AND UPDATE IS DENIED IF NOT!!--                                             !!--
--!!NEEDED. - neuherza, 18.01.2016                           !!--
--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--
