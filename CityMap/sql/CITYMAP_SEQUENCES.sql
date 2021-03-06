CREATE SEQUENCE CITYMAP.LINE_ID_SEQ
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE CITYMAP.RIDE_ID_SEQ
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE CITYMAP.RIDETYPE_ID_SEQ
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE CITYMAP.STATION_ID_SEQ
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE CITYMAP.VEHICLE_ID_SEQ
START WITH 1
INCREMENT BY 1;


ALTER TABLE CITYMAP.LINE ALTER COLUMN LINE_ID
  SET DEFAULT NEXTVAL('citymap.line_id_seq');

ALTER TABLE CITYMAP.RIDE ALTER COLUMN RIDE_ID
SET DEFAULT NEXTVAL('citymap.ride_id_seq');

ALTER TABLE CITYMAP.RIDETYPE ALTER COLUMN RIDETYPE_ID
SET DEFAULT NEXTVAL('citymap.ridetype_id_seq');

ALTER TABLE CITYMAP.STATION ALTER COLUMN STATION_ID
SET DEFAULT NEXTVAL('citymap.station_id_seq');

ALTER TABLE CITYMAP.VEHICLE ALTER COLUMN VEHICLE_ID
SET DEFAULT NEXTVAL('citymap.vehicle_id_seq');