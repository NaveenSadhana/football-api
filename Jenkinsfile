pipeline {
    agent {
        kubernetes {
            yaml """
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: jnlp
    image: jenkins/inbound-agent:3355.v388858a_47b_33-3-jdk21
    imagePullPolicy: IfNotPresent
    env:
    - name: HTTP_PROXY
      value: "http://192.168.64.8:9001"
    - name: HTTPS_PROXY
      value: "http://192.168.64.8:9001"
    - name: NO_PROXY
      value: "localhost,127.0.0.1,10.0.0.0/8,172.16.0.0/12,192.168.0.0/16,jenkins.jenkins.svc.cluster.local"
    - name: GIT_SSL_CAINFO
      value: "/etc/ssl/certs/zscaler-ca.pem"
  - name: maven
    image: maven:3.9.9-eclipse-temurin-21
    imagePullPolicy: IfNotPresent
    env:
    - name: HTTP_PROXY
      value: "http://192.168.64.8:9001"
    - name: HTTPS_PROXY
      value: "http://192.168.64.8:9001"
    - name: NO_PROXY
      value: "localhost,127.0.0.1,10.0.0.0/8,172.16.0.0/12,192.168.0.0/16,jenkins.jenkins.svc.cluster.local"
    - name: GIT_SSL_CAINFO
      value: "/etc/ssl/certs/zscaler-ca.pem"
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
    volumeMounts:
    - name: zscaler-ca
      mountPath: /etc/ssl/certs/zscaler-ca.pem
      subPath: zscaler-ca.pem
  volumes:
  - name: zscaler-ca
    configMap:
      name: zscaler-ca
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

