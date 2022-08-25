def projectName = 'portfolio_management'
def version = "0.0.${currentBuild.number}"
def dockerImageTag = "${projectName}:${version}"

pipeline{
    agent any
    stages{
        stage('Test') {
            steps {
                sh 'chmod a+x ./server_new/mvnw'
                sh './server_new/mvnw clean test'
            }
        }

        stage('Build') {
            steps {
                sh './server_new/mvnw package'
            }
        }

        stage('Build Container') {
            steps {
                sh "docker-compose build"
            }
        }
        stage('Deploy Containers to Openshift'){
            steps{
                sh "oc login https://localhost:8443 --username admin --password admin --insecure-skip-tls-verify=true"
                sh "oc project ${projectName} || oc new-project ${projectName}"
                sh "oc delete all --selector app=${projectName} || echo 'Unable to delete all previous openshift resources'"
                sh "oc new-app ${dockerImageTag} -l version=${version}"
                sh "oc expose svc/${projectName}"
            }
        }
    }
}