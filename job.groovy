import hudson.model.FreeStyleProject
import hudson.plugins.git.GitSCM
import hudson.plugins.ws_cleanup.*
import hudson.tasks.Shell
import jenkins.model.Jenkins

job = Jenkins.instance.createProject(FreeStyleProject, 'groovy-created-job')

def url = "https://github.com/Glebk0/simple_webapp.git"
def gitScm = new GitSCM(url)
gitScm.branches = [new hudson.plugins.git.BranchSpec("*/master")]
job.scm = gitScm

job.buildersList.add(new Shell('Hello'))
job.save()

job.buildWrappersList.add(new PreBuildCleanup(null, true, "", ""))