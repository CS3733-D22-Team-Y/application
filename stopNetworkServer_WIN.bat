ECHO OFF
ECHO Stopping Network Server...

java -jar %DERBY_HOME%\lib\derbyrun.jar server shutdown

ECHO Stopped Network Server