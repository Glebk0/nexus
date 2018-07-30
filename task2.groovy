
import hudson.model.FreeStyleProject
import hudson.plugins.git.GitSCM
import hudson.plugins.ws_cleanup.*
import hudson.tasks.Shell
import jenkins.model.Jenkins

task2 = Jenkins.instance.createProject(FreeStyleProject, 'MNT-CD-module9-extcreated-task2')

def url = "https://github.com/Glebk0/simple_webapp.git"
def gitScm = new GitSCM(url)
gitScm.branches = [new hudson.plugins.git.BranchSpec("*/master")]
task2.scm = gitScm

task2.buildersList.add(new Shell('Hello'))
task2.save()

task2.buildWrappersList.add(new PreBuildCleanup(null, true, "", ""))