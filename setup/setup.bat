@echo off

SET CURRDIR=%CD%

echo Installing 'Maven Glassfish Plugin' in local repository.
cd %CURRDIR%\plugin
call mvn -o install:install-file -Dfile=maven-glassfish-plugin-2.3-SNAPSHOT.jar -DgroupId=org.glassfish.maven.plugin -DartifactId=maven-glassfish-plugin -Dversion=2.3-SNAPSHOT -Dpackaging=jar -DgeneratePom=false
call mvn -o install:install-file -Dfile=maven-glassfish-plugin-2.3-SNAPSHOT.pom -DgroupId=org.glassfish.maven.plugin -DartifactId=maven-glassfish-plugin -Dversion=2.3-SNAPSHOT -Dpackaging=pom -DgeneratePom=false

cd %CURRDIR%
echo Configuring and starting domain 'springstore'.
call mvn glassfish:deploy

echo Preparing database
call mvn process-test-resources