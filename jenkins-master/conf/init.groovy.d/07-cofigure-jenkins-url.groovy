import jenkins.model.*

def env = System.getenv()
// Read the environment variable
url = env.JENKINS_UI_URL
// Get the config from our running instance
urlConfig = JenkinsLocationConfiguration.get()
// Set the config to be the value of the env var
urlConfig.setUrl(url)
// Save the configuration
urlConfig.save()
// Print the results
println("*****[ Jenkins URL Set to : " + url + " ]*****")

