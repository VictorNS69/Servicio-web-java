#!/bin/bash

# Some colors
YELLOW=$(tput setaf 3)
GREEN=$(tput setaf 2)
NORMAL=$(tput sgr0)

printf "%40s\n" "${YELLOW}Be sure that your tomcat folder is \"/usr/local/tomcat7\"${YELLOW}"
# Stop the tomcat server (if it is running)
printf "\n%40s\n\n" "${GREEN}Stoping actual server${NORMAL}" 
sudo /usr/local/tomcat7/bin/shutdown.sh
# Directory where build.xml is
cd JavaWebService
# Create the aar
printf "\n%40s\n\n" "${GREEN}Creating UserManagementWS.aar${NORMAL}"
ant
# Move where the aar is
cd build/lib
# Copy the file in the tomcat-axis
sudo cp UserManagementWS.aar /usr/local/tomcat7/webapps/axis2/WEB-INF/services/
# Run the server
printf "\n%40s\n\n" "${GREEN}Starting server${NORMAL}"
sudo /usr/local/tomcat7/bin/startup.sh
