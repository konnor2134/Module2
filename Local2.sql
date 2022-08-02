--------------------------------------------------------
--  DDL for Table USERS
--------------------------------------------------------
  CREATE TABLE "USER2"."QUESTIONS" 
   (	"ID" NUMBER, 
	"QUESTION" VARCHAR2(50 BYTE), 
	"ANSWER" VARCHAR2(50 BYTE), 
	"POINTS" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table QUESTIONS
--------------------------------------------------------

  ALTER TABLE "USER2"."QUESTIONS" MODIFY ("QUESTION" NOT NULL ENABLE);
  ALTER TABLE "USER2"."QUESTIONS" MODIFY ("ANSWER" NOT NULL ENABLE);

--------------------------------------------------------
--  DDL for Table USERS
--------------------------------------------------------
  CREATE TABLE "USER2"."USERS" 
   (	"ID" NUMBER, 
	"NAME" VARCHAR2(50 BYTE), 
	"MAX_TOTAL_POINTS" NUMBER
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table USERS
--------------------------------------------------------

  ALTER TABLE "USER2"."USERS" MODIFY ("NAME" NOT NULL ENABLE);
