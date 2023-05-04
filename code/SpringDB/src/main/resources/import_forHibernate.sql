INSERT INTO COURSE (CODE,CREDITS,TITLE) VALUES ('BKTW-101',3.0,'Introduction to BasketWeaving');
INSERT INTO COURSE (CODE,CREDITS,TITLE) VALUES ('BOT-202',2.0,'Yet more Botany');
INSERT INTO COURSE (CODE,CREDITS,TITLE) VALUES ('MATH-101',4.0,'Intro To Math');

INSERT INTO SCHEDULEDCLASS (STARTDATE,ENDDATE,COURSE_ID) VALUES ('2012-10-10','2013-2-20',1);
INSERT INTO SCHEDULEDCLASS (STARTDATE,ENDDATE,COURSE_ID) VALUES ('2012-10-10','2013-08-10',2);
INSERT INTO SCHEDULEDCLASS (STARTDATE,ENDDATE,COURSE_ID) VALUES ('2012-10-10','2013-10-10',3);

INSERT INTO STUDENT (NAME,PHONENUMBER,DOB, STATUS) VALUES ('Manoj-h2','222 333-4444','1956-08-15', 'FULL_TIME');
INSERT INTO STUDENT (NAME,PHONENUMBER,DOB, STATUS) VALUES ('Ana-h2','222 333-7900','1978-03-10', 'PART_TIME');
INSERT INTO STUDENT (NAME,PHONENUMBER,DOB, STATUS) VALUES ('Roberta-h2','383 343-5879','2000-07-15', 'HIBERNATING');
INSERT INTO STUDENT (NAME,PHONENUMBER,DOB, STATUS) VALUES ('Madhu-h2','383 598-8279','1994-10-07', 'PART_TIME');

INSERT INTO STUDENT_SCHEDULEDCLASS (STUDENTS_ID,CLASSES_ID) VALUES (1,2);
INSERT INTO STUDENT_SCHEDULEDCLASS (STUDENTS_ID,CLASSES_ID) VALUES (2,2);
INSERT INTO STUDENT_SCHEDULEDCLASS (STUDENTS_ID,CLASSES_ID) VALUES (2,3);