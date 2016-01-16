/*DDL-Script 
   PROJECT:    CITY MAP
  STUDENTS:    LADAR | NEUHERZ
   VERSION:    0.2
      DATE:    2015-11-07 | UPDATE 2015-11-24
   SUBJECT: DB_ANW | HABIGER
*/

--CREATE TABLE SCRIPT
--SCHEME: TICKETS
CREATE TABLE LADARPHI15.TICKETTYPE (
  TicketType_ID  NUMERIC PRIMARY KEY,
  Type  VARCHAR(200) NOT NULL); 
  
CREATE TABLE LADARPHI15.TICKETTYPEPRICE (
  TicketType_ID  NUMERIC,
  ValidFrom  DATE,
  BasePrice NUMERIC(8,2) NOT NULL,
  PRIMARY KEY (TicketType_ID, ValidFrom) ); 

CREATE TABLE LADARPHI15.DISCOUNT (
  Discount_ID NUMERIC PRIMARY KEY,
  Description VARCHAR(200),
  Percentage NUMERIC(3) NOT NULL);   

CREATE TABLE LADARPHI15.TICKETTYPE_DISCOUNT (
  Discount_ID NUMERIC,
  TicketType_ID  NUMERIC,
  PRIMARY KEY (Discount_ID, TicketType_ID)); 
  
CREATE TABLE LADARPHI15.TICKET (
  TicketID NUMERIC PRIMARY KEY,
  TicketType_ID  NUMERIC NOT NULL,
  Discount_ID  NUMERIC NOT NULL,
  Price NUMERIC(3) NOT NULL,
  PurchaseDate DATE NOT NULL);
  
   
--CREATE TABLE SCRIPT
--SCHEME: CITY MAP
CREATE TABLE LADARPHI15.RIDETYPE (
  RideType_ID NUMERIC PRIMARY KEY,
  Type  VARCHAR(200) NOT NULL);  
  
CREATE TABLE LADARPHI15.RIDE (
  Ride_ID NUMERIC PRIMARY KEY,
  Description  VARCHAR(200) NOT NULL,
  Line_ID NUMERIC NOT NULL,
  RideType_ID NUMERIC NOT NULL);    
  
CREATE TABLE LADARPHI15.LINE (
  Line_ID NUMERIC PRIMARY KEY,
  Description  VARCHAR(200) NOT NULL,
  Vehicle_ID NUMERIC NOT NULL);   
  
CREATE TABLE LADARPHI15.VEHICLE (
  Vehicle_ID NUMERIC PRIMARY KEY,
  Description  VARCHAR(200) NOT NULL); 

CREATE TABLE LADARPHI15.STATION (
  Station_ID NUMERIC PRIMARY KEY,
  Description  VARCHAR(200) NOT NULL);   
  
CREATE TABLE LADARPHI15.VEHICLE_STATION (
  Station_ID NUMERIC,
  Vehicle_ID NUMERIC,
  PRIMARY KEY (Station_ID, Vehicle_ID)); 

CREATE TABLE LADARPHI15.STOP (
  Station_ID NUMERIC,
  Ride_ID NUMERIC,
  Halt_No NUMERIC,
  WaitTime NUMERIC(3),
  TimeToNextStop NUMERIC(3),
  --TODO: TRIGGER Halt_No
  PRIMARY KEY (Ride_ID, Halt_No));       

CREATE TABLE LADARPHI15.RIDEONDAY (
  Ride_ID NUMERIC,
  RideStartTime DATE,
  --POSSIBLE ATTRIBUTES: Driver, 
  --                     Specific Vehicle with Vehicle NUMERIC (License Plate, etc.)
  PRIMARY KEY (Ride_ID, RideStartTime)); 
 
CREATE TABLE LADARPHI15.DELAY (
  Ride_ID NUMERIC,
  RideStartTime DATE,
  DelayInMinutes NUMERIC(3),
  Reason VARCHAR(255),
  PRIMARY KEY (Ride_ID, RideStartTime)); 

  --ALTER TABLE SCRIPT
--SCHEME: TICKETS
   ALTER TABLE LADARPHI15.TICKETTYPEPRICE
ADD CONSTRAINT tickettypeprice_fk
   FOREIGN KEY (TicketType_ID)
    REFERENCES LADARPHI15.TICKETTYPE (TicketType_ID);
   
   ALTER TABLE LADARPHI15.TICKETTYPE_DISCOUNT
ADD CONSTRAINT TICKETTYPE_DISCOUNT_DIS_FK
   FOREIGN KEY (Discount_ID)
    REFERENCES LADARPHI15.DISCOUNT (Discount_ID);

   ALTER TABLE LADARPHI15.TICKETTYPE_DISCOUNT
ADD CONSTRAINT TICKETTYPE_DISCOUNT_TT_FK
   FOREIGN KEY (TicketType_ID)
    REFERENCES LADARPHI15.TICKETTYPE (TicketType_ID);
    
       ALTER TABLE LADARPHI15.TICKET
ADD CONSTRAINT TICKET_FK
   FOREIGN KEY (TicketType_ID, Discount_ID)
    REFERENCES LADARPHI15.TICKETTYPE_DISCOUNT (TicketType_ID, Discount_ID);
    
--ALTER TABLE SCRIPT
--SCHEME: CITY MAP
   ALTER TABLE LADARPHI15.LINE
ADD CONSTRAINT LINE_FK
   FOREIGN KEY (Vehicle_ID)
    REFERENCES LADARPHI15.VEHICLE (Vehicle_ID);
    
   ALTER TABLE LADARPHI15.RIDE
ADD CONSTRAINT RIDE_L_FK
   FOREIGN KEY (Ridetype_ID)
    REFERENCES LADARPHI15.RIDETYPE (Ridetype_ID);
    
   ALTER TABLE LADARPHI15.RIDE
ADD CONSTRAINT RIDE_FT_FK
   FOREIGN KEY (Line_ID)
    REFERENCES LADARPHI15.LINE (Line_ID);
    
   ALTER TABLE LADARPHI15.VEHICLE_STATION
ADD CONSTRAINT VEHICLE_STATION_V_FK
   FOREIGN KEY (Vehicle_ID)
    REFERENCES LADARPHI15.VEHICLE (Vehicle_ID);
    
   ALTER TABLE LADARPHI15.VEHICLE_STATION
ADD CONSTRAINT VEHICLE_STATION_S_FK
   FOREIGN KEY (Station_ID)
    REFERENCES LADARPHI15.STATION (Station_ID);
    
       ALTER TABLE LADARPHI15.STOP
ADD CONSTRAINT STOP_RI_FK
   FOREIGN KEY (Ride_ID)
    REFERENCES LADARPHI15.RIDE (Ride_ID);
    
           ALTER TABLE LADARPHI15.STOP
ADD CONSTRAINT STOP_ST_FK
   FOREIGN KEY (Station_ID)
    REFERENCES LADARPHI15.STATION (Station_ID);

           ALTER TABLE LADARPHI15.RIDEONDAY
ADD CONSTRAINT RIDEONDAY_FK
   FOREIGN KEY (Ride_ID)
    REFERENCES LADARPHI15.RIDE (Ride_ID);
    
               ALTER TABLE LADARPHI15.DELAY
ADD CONSTRAINT DELAY_FK
   FOREIGN KEY (Ride_ID, RideStartTime)
    REFERENCES LADARPHI15.RIDEONDAY (Ride_ID, RideStartTime);​