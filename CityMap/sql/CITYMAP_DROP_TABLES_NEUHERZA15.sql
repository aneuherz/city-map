/*DDL-Script 
   PROJECT:    CITY MAP
  STUDENTS:    LADAR | NEUHERZ
   VERSION:    0.2
      DATE:    2015-11-07 | UPDATE 2015-11-24
   SUBJECT: DB_ANW | HABIGER
*/
DECLARE
  CURSOR user_tabs IS SELECT table_name FROM user_tables
  WHERE table_name NOT LIKE '%APEX%'
        AND table_name NOT LIKE 'DEMO%'
        AND table_name NOT IN (
    'DEPT','EMP','HTMLDB_PLAN_TABLE'
  )
  ;
  v_sql VARCHAR2(255);
  CURSOR  user_const IS SELECT constraint_name,table_name
                       FROM user_constraints
                       WHERE constraint_name LIKE '%_FK';
BEGIN
  FOR constr IN user_const
  LOOP
    v_sql := 'ALTER TABLE '||constr.table_name
    ||' DROP constraint ' || constr.constraint_name;
    EXECUTE IMMEDIATE v_sql;
  END LOOP;
  FOR table_td IN user_tabs
  LOOP
    v_sql := 'DROP TABLE ' || table_td.table_name;
    v_sql := v_sql;
    DBMS_OUTPUT.put_lINe(v_sql);
    EXECUTE IMMEDIATE v_sql;
  END LOOP;

END;
/