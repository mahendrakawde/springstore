@echo off

echo Deploying 'External' applications
call mvn glassfish:deploy

call deploy