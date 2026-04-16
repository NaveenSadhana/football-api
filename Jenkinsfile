pipeline {
    agent {
        kubernetes {
            yaml """
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: maven
    image: maven:3.9.9-eclipse-temurin-21
    imagePullPolicy: IfNotPresent
    command:
    - cat
    tty: true
    resources:
      requests:
        memory: "512Mi"
        cpu: "500m"
      limits:
        memory: "2Gi"
        cpu: "2"
"""
        }
    }

    options {
        disableConcurrentBuilds()
        skipStagesAfterUnstable()
        timeout(time: 30, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '10'))
        skipDefaultCheckout(true)   // prevent @script cache corruption on restart
    }

    stages {
        stage('Checkout') {
            steps {
                cleanWs()       // wipe workspace before checkout
                checkout scm    // fresh clone every time
            }
        }

        stage('Build') {
            steps {
                container('maven') {
                    sh 'mvn clean install -DskipTests'
                }
            }
        }
    }

    post {
        always {
            cleanWs()   // clean workspace after every build
        }
        success {
            echo '✅ Build succeeded!'
        }
        failure {
            echo '❌ Build failed!'
        }
    }
}

