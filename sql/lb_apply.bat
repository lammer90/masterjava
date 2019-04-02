liquibase.bat --driver=org.postgresql.Driver ^
--classpath=C:\masterjava\sql ^
--changeLogFile=databaseChangeLog.sql ^
--url="jdbc:postgresql://localhost:5432/masterjava" ^
--username=user ^
--password=password ^
migrate