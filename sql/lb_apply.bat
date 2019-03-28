C:\liquibase-3.5.3\liquibase.bat --driver=org.postgresql.Driver ^
--classpath=C:\liquibase-3.5.3\lib ^
--changeLogFile=databaseChangeLog.sql ^
--url="jdbc:postgresql://localhost:5432/masterjava" ^
--username=user ^
--password=password ^
migrate