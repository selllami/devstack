FROM jenkins/jenkins:lts
MAINTAINER Yassine SELLAMI <yassine.selllami@gmail.com>
LABEL Description="This demo shows how to setup Jenkins into devstack" Vendor="Selllami" Version="1.0.0"

ARG HTTP_PORT=8080
ARG AGENT_PORT=50000
ARG JENKINS_USER=admin
ARG JENKINS_PASS=admin
ARG JENKINS_URL=http://jenkins-stack.local/
ARG MAVEN_TOOL=M3
ARG DOCKER_NAME=docker-19.03.5.tgz
USER root

RUN apt-get update \
      && apt-get install -y sudo curl\
      && apt-get install -y libltdl7\
      && rm -rf /var/lib/apt/lists/*

# Install docker client 
RUN wget -O /tmp/docker.tgz https://download.docker.com/linux/static/stable/x86_64/${DOCKER_NAME} && \
    tar zxf /tmp/docker.tgz -C /tmp && \
    cp /tmp/docker/docker /usr/bin/docker  && \
    rm -rf /tmp/*


RUN mkdir /var/log/jenkins
RUN mkdir /var/cache/jenkins
RUN chown -R  jenkins:jenkins /var/log/jenkins
RUN chown -R jenkins:jenkins /var/cache/jenkins


# Authorize Github SSH keys
RUN mkdir /usr/git
COPY conf/git/id_rsa	 /usr/git/id_rsa
COPY conf/git/id_rsa.pub /usr/git/id_rsa.pub
COPY conf/git/git.pw	 /usr/git/git.pw
COPY conf/git/git.user   /usr/git/git.user

#RUN curl -sSL https://get.docker.com/ | sh
RUN groupadd docker
RUN usermod -aG docker jenkins



# Set env variable 
ENV JENKINS_USER    ${JENKINS_USER}
ENV JENKINS_PASS    ${JENKINS_PASS}
ENV JENKINS_UI_URL  ${JENKINS_URL}
ENV MAVEN_TOOL      ${MAVEN_TOOL}
ENV JAVA_OPTS="-Xmx3192m -Djenkins.install.runSetupWizard=false"
ENV JENKINS_OPTS="--handlerCountMax=100 --logfile=/var/log/jenkins/jenkins.log --webroot=/var/cache/jenkins/war"

COPY conf/plugins.txt /usr/share/jenkins/plugins.txt
#RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/plugins.txt
COPY conf/init.groovy.d	  /usr/share/jenkins/ref/init.groovy.d


# Main web interface and JNLP slaves
EXPOSE ${HTTP_PORT}  ${AGENT_PORT}
