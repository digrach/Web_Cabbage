!#/bin/sh

mvn clean install
rm -R ../tomcat/web/app/ROOT
rm ../tomcat/web/app/ROOT.war
cp target/playcabbage.war ../tomcat/web/app/ROOT.war
