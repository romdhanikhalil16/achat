pipeline {
    agent any
    tools {
        // Ensure Maven is properly configured
        maven "M2_HOME"
    }
    stages {
        stage('Checkout') {
            steps {
                // Checkout the Git repository
                git branch: 'Walid', url: 'https://github.com/romdhanikhalil16/achat.git'
            }
        }
        stage('Clean') {
            steps {
                // Clean the project using Maven
                sh "mvn clean"
            }
        }
        stage('Compile') {
            steps {
                // Compile the project using Maven
                sh "mvn compile"
            }
        }

        stage('Test with Mockito/JUnit') {
            steps {
                sh 'mvn test'
            }
        }

        stage('MVN SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
            }
        }

        stage('NEXUS') {
            steps {
                echo 'deploying project...';
                sh 'mvn deploy'
            }
        }

        stage('Building Image'){
            steps {
                sh 'docker build -t waliddocker20/achat:1.0.0 .'
            }
        }

        stage('Deploy Image') {
            steps {
                echo 'deploying docker image...';
                sh '''docker login -u waliddocker20 -p docker789
                      docker push waliddocker20/achat:1.0.0'''
             }
         }
         stage('Docker Compose') {
            steps {
                echo 'composing docker image...';
                sh 'docker-compose up -d'
             }
         }

        stage('Build') {
            steps {
                // Run Maven to build the project
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post {
                success {
                    // If Maven was able to run the tests, record the test results and archive the JAR file
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
                failure {
                    // If the build fails, print a message
                    echo "Build failed! Please check the Jenkins logs for more information."
                }
            }
        }
    }
}