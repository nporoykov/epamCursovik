pipeline {
    agent any
    
      stages{
         stage('Pull from GitHub') {
           steps{
               git([
                  branch: 'master', 
                  credentialsId: '00f62077-f2ee-45dc-a085-929d3fb0b1f4', 
                  url: 'https://github.com/nporoykov/epamCursovik.git'
               ])
           }
         }
         
         stage('Run mvn clean test') {
             steps{
                 script{
                     try{
                         bat 'mvn clean test'
                     }catch(err){echo err.getMessage()}
                 }
                 echo currentBuild.result
             }
         }
         
         stage('Run allure Report') {
             steps{
                 allure includeProperties: false, 
                 jdk: '', 
                 results: [[path: 'allure-results']]
             }
         }
         
         
   stage('Sending status email') {
       steps{
               emailext body: '''${SCRIPT, template="groovy-html.template"}''',
                  mimeType: 'text/html',
                  subject: "[Jenkins] ${currentBuild.fullDisplayName}",
                  to: "nik82@outlook.com",
                  replyTo: "noreply@gmail.com",
                  recipientProviders: [[$class: 'CulpritsRecipientProvider']]
        
        }
   }
   
         
     }
    
}
