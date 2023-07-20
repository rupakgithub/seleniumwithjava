FROM jenkins/jenkins:lts
# if we want to install via apt
ARG MAVEN_VERSION=3.9.3

# Changing user to root to install maven
USER root

RUN apt-get update && apt-get install -y wget

RUN wget --no-verbose -O /tmp/apache-maven-$MAVEN_VERSION-bin.tar.gz https://dlcdn.apache.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz

RUN tar xzf /tmp/apache-maven-$MAVEN_VERSION-bin.tar.gz -C /opt/
RUN ln -s /opt/apache-maven-$MAVEN_VERSION /opt/maven
RUN ln -s /opt/maven/bin/mvn /usr/local/bin
RUN rm -f /tmp/apache-maven-$MAVEN_VERSION-bin.tar.gz
ENV MAVEN_HOME /opt/maven

RUN chown -R jenkins:jenkins /opt/maven

RUN apt-get clean

USER jenkins