#! /bin/sh
# $Id: setup.sh,v 1.7 2004/08/06 00:12:05 gmurray71 Exp $
mvn install:install-file -Dfile=./plugin/maven-glassfish-plugin-2.2-SNAPSHOT.jar -DgroupId=org.glassfish.maven.plugin -DartifactId=maven-glassfish-plugin -Dversion=2.2-SNAPSHOT -Dpackaging=jar

mvn glassfish:deploy

