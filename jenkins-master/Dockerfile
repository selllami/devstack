FROM jenkins/jenkins:lts

USER root
RUN apt-get update \
      && apt-get install -y sudo curl\
      && apt-get install -y libltdl7\
      && rm -rf /var/lib/apt/lists/*
RUN echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers

ARG http_port=8080
ARG agent_port=50000

#RUN curl -sSL -o /bin/docker https://get.docker.io/builds/Linux/x86_64/docker-latest
#RUN chmod 777 /bin/docker
#RUN curl -sSL -o /bin/docker https://get.docker.io/builds/Linux/x86_64/docker-latest
#RUN chmod 777 /bin/docker

RUN mkdir /var/log/jenkins
RUN mkdir /var/cache/jenkins

#RUN apt-get update


RUN chown -R  jenkins:jenkins /var/log/jenkins
RUN chown -R jenkins:jenkins /var/cache/jenkins

# Authorize Github SSH keys
COPY conf/key/id_rsa	 /root/.ssh/id_rsa
COPY conf/key/id_rsa.pub /root/.ssh/id_rsa.pub
RUN chmod 600 /root/.ssh/id_rsa &&\
    chmod 600 /root/.ssh/id_rsa.pub
RUN echo "    IdentityFile /root/.ssh/id_rsa" >> /etc/ssh/ssh_config &&\
    echo "    StrictHostKeyChecking no      " >> /etc/ssh/ssh_config
RUN /bin/bash -c "eval '$(ssh-agent -s)'; ssh-add /root/.ssh/id_rsa;"

RUN groupadd docker
RUN usermod -aG docker jenkins

USER jenkins

ENV JAVA_OPTS="-Xmx3192m"
ENV JENKINS_OPTS="--handlerCountMax=100 --logfile=/var/log/jenkins/jenkins.log --webroot=/var/cache/jenkins/war"

# for main web and slave agents:
EXPOSE ${http_port}
EXPOSE ${agent_port}

#COPY conf/init.groovy.d	  /usr/share/jenkins/ref/init.groovy.d
COPY conf/*.xml	/usr/share/jenkins/ref/
#COPY conf/plugins/*.hpi	/usr/share/jenkins/ref/plugins/
COPY conf/plugins.txt	/usr/share/jenkins/plugins.txt
#RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/plugins.txt
