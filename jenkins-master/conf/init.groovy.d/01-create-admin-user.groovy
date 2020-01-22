import jenkins.model.*
import hudson.security.*

def env = System.getenv()
def jenkins = Jenkins.getInstance()

if(!(jenkins.getSecurityRealm() instanceof HudsonPrivateSecurityRealm))
   jenkins.setSecurityRealm(new HudsonPrivateSecurityRealm(false,false,null))

//if(!(jenkins.getAuthorizationStrategy() instanceof GlobalMatrixAuthorizationStrategy))
//    jenkins.setAuthorizationStrategy(new GlobalMatrixAuthorizationStrategy())

def user = jenkins.getSecurityRealm().createAccount(env.JENKINS_USER, env.JENKINS_PASS)
user.save()

def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
//def strategy = new GlobalMatrixAuthorizationStrategy()
//    strategy.add(Jenkins.ADMINISTER, env.JENKINS_USER)
jenkins.setAuthorizationStrategy(strategy)
//jenkins.getAuthorizationStrategy().add(Jenkins.ADMINISTER, env.JENKINS_USER)
jenkins.save()
println("*****[ Created user : " + env.JENKINS_USER + " password: " + env.JENKINS_PASS + " ]*****")


