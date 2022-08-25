def projectName = 'portfolio-management'
def projectNameFront = 'portfolio-management-front-end'
def version = "0.0.${currentBuild.number}"
def dockerImageTag = "${projectName}:${version}"
def dockerImageTagFront = "${projectNameFront}:${version}"

pipeline{
    agent any
    stages{
        stage('Clean up docker images'){
            steps{
                sh 'docker stop $(docker ps -q)'
                sh 'docker rm $(docker ps -a -q)'
                sh 'docker rmi $(docker images -q -f dangling=true)'
            }
        }
        stage('Build Docker Containers') {
            steps {
                sh "docker build -t ${dockerImageTag} ./server_new"
                sh "docker build -t ${dockerImageTagFront} ./client"
            }
        }
        stage('Deploy Containers to Openshift'){
            steps{
                sh "oc login https://localhost:8443 --username admin --password admin --insecure-skip-tls-verify=true"
                sh "oc project ${projectName} || oc new-project ${projectName}"
                sh "oc delete all --selector app=${projectName} || echo 'Unable to delete all previous openshift resources'"
                sh "oc new-app ${dockerImageTag}"
                sh "oc new-app ${dockerImageTagFront}"
                sh "oc new-app callalyf/dummy-order-filler -l version=0.0.1"
                sh "oc expose svc/${projectName}"
            }
        }
    }
}