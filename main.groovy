pipeline {
  agent any
  
  parameters {
            string(defaultValue: "NULL", description: 'Argument 1 (String Value)', name: 'argv_1')
  }
  
  environment {
    ARGV_1 = "${params.argv_1}"
  }
  
  stages {
    stage('Initial Stage') {
      steps {
        echo "Initial Stage"
        deleteDir()
      }
    }
    stage('Init Git') {
      steps {
        echo "Init Git"
        echo "DEBUG: ARGV_1 = " + ARGV_1
        withCredentials([usernamePassword(credentialsId: 'oc-cluster', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
          oc login -u ${USERNAME} ${PASSWORD} https://master:8443
        }
      }
    }
  }
}
