FROM centos:centos7
LABEL maintainer=”yassine.selllami@gmail.com”

RUN yum -y update; yum clean all
RUN yum -y install http://nginx.org/packages/centos/7/noarch/RPMS/nginx-release-centos-7-0.el7.ngx.noarch.rpm; yum -y makecache
RUN yum -y install nginx-1.10.1

RUN rm /etc/nginx/conf.d/default.conf

#ADD conf/jenkins.conf /etc/nginx/conf.d/jenkins.conf
#ADD conf/portainer.conf /etc/nginx/conf.d/portainer.conf
#ADD conf/nexus.conf /etc/nginx/conf.d/nexus.conf
COPY conf/stack.conf /etc/nginx/conf.d/stack.conf
COPY conf/nginx.conf /etc/nginx/nginx.conf

EXPOSE 80
CMD ["nginx"]
