@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7')
import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.JSON
import groovy.json.JsonSlurper

url = "http://nexus/service/rest/v1/script"
def authSite = new RESTClient(url)
authSite.setHeaders([Authorization: "Basic YWRtaW46YWRtaW4xMjM="])


def changePassword = new JsonSlurper().parseText('{ "name": "Change_Password", "content": "security.securitySystem.changePassword("admin","admin")", "type": "groovy"}')
def response1 = authSite.post(
        contentType: JSON,
        body: changePassword,
        headers: [Accept: 'application/json'])
println("Status: " + response1.status)

def addUser = new JsonSlurper().parseText('{"name": "Create_User", "content": "security.addUser("new_user", "new_user", "new_user", "new_user@epam.com", true, "new_user", ["nx-admin"])","type": "groovy" }')
def response2 = authSite.post(
        contentType: JSON,
        body: addUser,
        headers: [Accept: 'application/json'])
println("Status: " + response2.status)

def createRepo = new JsonSlurper().parseText('{   "name": "Create_Repo","type": "groovy", "content": "repository.createMavenHosted("repo")" }')
def response = authSite.post(
        contentType: JSON,
        body: createRepo,
        headers: [Accept: 'application/json'])
println("Status: " + response.status)