pipeline {
    agent any

    tools {
        // Ensure Maven is properly configured
        maven "M2_HOME"
    }

    stages {
        stage('GIT') {
            steps {
                // Checkout the Git repository
                git branch: 'Walid', url: 'https://github.com/romdhanikhalil16/achat.git'
            }
        }

        stage('MAVEN BUILD') {
            steps {
                // Clean and compile the project using Maven
                sh "mvn clean compile"
            }
        }

         stage('MOCKITO') {
            steps {
                sh 'mvn test'
            }
        }

        stage('SONARQUBE') {
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

        stage('DOCKER IMAGE'){
            steps {
                sh 'docker build -t waliddocker20/achat:1.0.0 .'
            }
        }

        stage('DOCKER HUB') {
            steps {
                echo 'deploying docker image...';
                sh '''docker login -u waliddocker20 -p docker789
                      docker push waliddocker20/achat:1.0.0'''
             }
         }

         stage('DOCKER-COMPOSE') {
            steps {
                echo 'composing docker image...';
                sh 'docker-compose up -d'
             }
         }


        stage('Prometheus') {
            steps {
                script {
                    sh 'curl -X POST -d "status=success" http://localhost:9090/metrics/job/prometheus_stage'
                }
            }
        }

        stage('Grafana') {
            steps {
                script {
                    sh 'curl -X POST -d "prometheus_url=http://localhost:9090/" -d "grafana_url=http://localhost:3000/" http://localhost:8080/api/datasources'

                        }
                 }
            }

    }
}