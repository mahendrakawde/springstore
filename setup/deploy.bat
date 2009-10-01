@echo off

set OLDDIR=%CD%

echo Deploying OPC 
cd %OLDDIR%/../apps/opc/ear
call mvn glassfish:deploy

echo Deploying Consumer Website
cd %OLDDIR%/../apps/consumerwebsite/ear
call mvn glassfish:deploy

cd %OLDDIR%
