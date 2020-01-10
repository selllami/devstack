FROM jenkins/jenkins:lts

USER root
RUN apt-get update \
      && apt-get install -y sudo curl\
      && apt-get install -y libltdl7\
      && rm -rf /var/lib/apt/lists/*
RUN echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers

ARG http_port=8080
ARG agent_port=50000

# see: https://issues.jenkins-ci.org/browse/JENKINS-35025
# see: https://get.docker.com/builds/
# see: https://wiki.jenkins-ci.org/display/JENKINS/CloudBees+Docker+Custom+Build+Environment+Plugin#CloudBeesDockerCustomBuildEnvironmentPlugin-DockerinDocker
RUN curl -sSL -o /bin/docker https://get.docker.io/builds/Linux/x86_64/docker-latest
RUN chmod +x /bin/docker

RUN mkdir /var/log/jenkins
RUN mkdir /var/cache/jenkins

RUN chown -R  jenkins:jenkins /var/log/jenkins
RUN chown -R jenkins:jenkins /var/cache/jenkins

RUN groupadd docker
#RUN apt-get update
RUN usermod -aG docker jenkins


USER jenkins

ENV JAVA_OPTS="-Xmx3192m"
ENV JENKINS_OPTS="--handlerCountMax=100 --logfile=/var/log/jenkins/jenkins.log --webroot=/var/cache/jenkins/war"

# for main web and slave agents:
EXPOSE ${http_port}
EXPOSE ${agent_port}

COPY conf/credentials.xml /usr/share/jenkins/ref/credentials.xml
COPY conf/plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/plugins.txt
