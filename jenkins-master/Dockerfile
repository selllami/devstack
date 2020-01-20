FROM jenkins/jenkins:lts
MAINTAINER Yassine SELLAMI <yassine.selllami@gmail.com>
LABEL Description="This demo shows how to setup Jenkins" Vendor="Selllami" Version="0.1"

USER root
RUN apt-get update \
      && apt-get install -y sudo curl\
      && apt-get install -y libltdl7\
      && rm -rf /var/lib/apt/lists/*
#RUN echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers

ARG HTTP_PORT=8080
ARG AGENT_PORT=50000
ARG DOCKER_VERSION=17.09.0-ce

#RUN curl -sL  "https://download.docker.com/linux/static/edge/x86_64/docker-$DOCKER_VERSION.tgz" | tar zx && \
#    mv /docker/* /bin/ &&  chmod +x /bin/docker* 

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

#COPY conf/key/id_rsa	 /root/.ssh/id_rsa
#COPY conf/key/id_rsa.pub /root/.ssh/id_rsa.pub
#RUN chmod 600 /root/.ssh/id_rsa &&\
#    chmod 600 /root/.ssh/id_rsa.pub
#RUN echo "    IdentityFile /root/.ssh/id_rsa" >> /etc/ssh/ssh_config &&\
#    echo "    StrictHostKeyChecking no      " >> /etc/ssh/ssh_config
#RUN /bin/bash -c "eval '$(ssh-agent -s)'; ssh-add /root/.ssh/id_rsa;"


#RUN groupadd docker
#RUN usermod -aG docker jenkins
ENV JENKINS_USER admin
ENV JENKINS_PASS admin

ENV JAVA_OPTS="-Xmx3192m -Djenkins.install.runSetupWizard=false"
ENV JENKINS_OPTS="--handlerCountMax=100 --logfile=/var/log/jenkins/jenkins.log --webroot=/var/cache/jenkins/war"

COPY conf/plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/plugins.txt

COPY conf/init.groovy.d	  /usr/share/jenkins/ref/init.groovy.d
#COPY conf/*.xml	/usr/share/jenkins/ref/

# for main web and slave agents:
EXPOSE ${HTTP_PORT}
EXPOSE ${AGENT_PORT}

