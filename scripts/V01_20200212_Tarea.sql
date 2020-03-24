--------------------------------------------------------
--  DDL for Table GBS_TAREA
--------------------------------------------------------

  CREATE TABLE "GBS_TAREA" 
   (	"ID" NUMBER, 
	"DSC_TAREA" VARCHAR2(40 BYTE), 
	"FEC_TAREA" DATE, 
	"EST_TAREA" VARCHAR2(40 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Index GBS_TAREA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "GBS_TAREA_PK" ON "GBS_TAREA" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index GBS_TAREA_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "GBS_TAREA_UK1" ON "GBS_TAREA" ("DSC_TAREA") 
  ;
--------------------------------------------------------
--  Constraints for Table GBS_TAREA
--------------------------------------------------------

  ALTER TABLE "GBS_TAREA" ADD CONSTRAINT "GBS_TAREA_UK1" UNIQUE ("DSC_TAREA")
  USING INDEX  ENABLE;
  ALTER TABLE "GBS_TAREA" ADD CONSTRAINT "GBS_TAREA_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
  ALTER TABLE "GBS_TAREA" MODIFY ("EST_TAREA" NOT NULL ENABLE);
  ALTER TABLE "GBS_TAREA" MODIFY ("FEC_TAREA" NOT NULL ENABLE);
  ALTER TABLE "GBS_TAREA" MODIFY ("DSC_TAREA" NOT NULL ENABLE);
  ALTER TABLE "GBS_TAREA" MODIFY ("ID" NOT NULL ENABLE);
  
--------------------------------------------------------
--  DDL for Sequence SEQ_GBS_TAREA
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ_GBS_TAREA"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  NOCYCLE ;
