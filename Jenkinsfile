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
                sh 'sudo docker rm \$(docker ps --filter status=exited -q) || echo "no docker image needs to remove"'
            }
        }
        stage('Pull dummy trader'){
            steps{
                sh "docker pull callalyf/dummy-order-filler:0.0.1"
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
                sh "oc delete all --selector app=${projectNameFront} || echo 'Unable to delete all previous openshift resources'"
                sh "oc new-app ${dockerImageTag}"
                sh "oc new-app ${dockerImageTagFront}"
                sh "oc new-app callalyf/dummy-order-filler -l version=0.0.1 -e DB_NAME=portfolio_DB -e DB_TABLE=order_history -e DB_HOST=100.42.65.208 -e DB_PORT=8380 -e DB_USER=dummy_trade -e DB_PASS=123456"
                sh "oc expose svc/${projectName}"
            }
        }
    }
}