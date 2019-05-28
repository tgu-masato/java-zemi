FROM tomcat:8.5.41

RUN apt-get -y update
RUN apt-get -y install default-jdk

RUN wget https://services.gradle.org/distributions/gradle-5.4.1-bin.zip -P /tmp
RUN unzip -d /opt/gradle /tmp/gradle-*.zip

ENV GRADLE_HOME /opt/gradle/gradle-5.4.1
ENV PATH ${GRADLE_HOME}/bin:${PATH}

RUN rm -rf /usr/local/tomcat/webapps/ROOT
