import jenkins.model.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*
import com.cloudbees.plugins.credentials.domains.*;
import com.cloudbees.plugins.credentials.*;


// Read our values into strings from the volume mount
privKey = new File('/usr/git/id_rsa').text.trim()
passPhrase = new File('/usr/git/git.pw').text.trim()
gitUser = new File('/usr/git/git.user').text.trim()

// Get a handle on our Jenkins instance
def jenkins = Jenkins.getInstance()

// Define the security domain. We're making these global but they can also
// be configured in a more restrictive manner. More on that later
def domain = Domain.global()

// Get our existing Credentials Store
def store = jenkins.getExtensionList(
  'com.cloudbees.plugins.credentials.SystemCredentialsProvider'
  )[0].getStore()

// Create a new BasicSSHUserPrivateKey object with our values
gitHubSSHKey = new BasicSSHUserPrivateKey(
  CredentialsScope.GLOBAL,
  "git-ssh-key",
  gitUser,
  new BasicSSHUserPrivateKey.DirectEntryPrivateKeySource(privKey),
  passPhrase,
  "GitHub Machine User SSH Creds"
)

// Add the new object to the credentials store
store.addCredentials(domain, gitHubSSHKey)

// Get the config descriptor for the overall Git config
def desc = jenkins.getDescriptor("hudson.plugins.git.GitSCM")

// Set the username and email for git interactions
desc.setGlobalConfigName("${gitUser}")
desc.setGlobalConfigEmail("yassine.Selllami@gmail.com")

// Save the descriptor
desc.save()

// Echo out (or log if you like) that we've done something
println("INFO: GitHub Credentials and Configuration complete!")

