import jenkins.model.Jenkins
import jenkins.security.s2m.*

jenkins = Jenkins.instance

adminWhitelistRule = jenkins.injector.getInstance(AdminWhitelistRule.class)
if (adminWhitelistRule.getMasterKillSwitch()) {
    adminWhitelistRule.setMasterKillSwitch(false)
    jenkins.save()
}



