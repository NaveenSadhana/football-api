pipeline {
    agent {
        docker {
            image 'maven:3.9.9-eclipse-temurin-21'
            args '-v $HOME/.m2:/root/.m2'  // cache dependencies
        }
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
    }

    post {
        success {
            echo '✅ Build successful using Docker'
        }
        failure {
            echo '❌ Build failed'
        }
    }
}
