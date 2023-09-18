# quizAppMicroService
Quiz Application is divided into 2 microservice applications for question and quiz.
Question service has a separate DB and so is the case for quiz service.
Feignclient is used to interact between the services. This helps to avoid hostname/port details in the code.
Eureka Server is used to register the services with each as a separate eureka client.
Load balancer is used to auto-select the service based on load when a request comes from the user. Feign client does this automatically.
API gateway service is used to act as entry point for any request and guide them to the correct service. 
