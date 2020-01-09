# DevStack  
This repository contains a devops stack.

![Image of Stack](https://i.ibb.co/yQfmRWN/stack.png)

## TODO
- [x] Stack setup, create project architecture.
- [x] Setup Jenkins/MySQL/Nexus/Portainer and create sime spring biit application.
- [x] Setup and config Nginx proxy.
- [ ] Setup Jenkinsfile and connect Jenkins with Github.
- [ ] Config Nexus and Docker repository.

## Quick Start Guide 

1. Make sure you have install Docker and Docker-compose on your machine.
2. Clone this repository with:
```
git clone https://github.com/selllami/devstack.git
```
3. Add host to your machien by run the following commande:
```
sudo -- sh -c 'echo "192.168.0.XX\t jenkins-stack.local \n192.168.9.XX\t nexus-stack.local \n192.168.0.XX\t portainer-stack.local" >> /etc/hosts'
```

4.Then you need to run docker-compose -p devstack up -d from the root folder. After the services are executed, you can navigate to urls bellow to see the applications running.
* JENKINS:   http://jenkins-stack.local/
* NEXUS:     http://nexus-stack.local/
* PORTAINER: http://portainer-stack.local/

