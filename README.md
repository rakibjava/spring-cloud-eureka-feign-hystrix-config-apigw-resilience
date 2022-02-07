# spring-cloud-eureka-feign-hystrix-config-apigw-resilience

1. Spring-cloud-netflix-eureka-discovery-server: under eureka-discovery-service
2. Spring-cloud-feign-resilience: under movie-catalog-feign-resilience
3. spring-cloud-hystrix-resttemplate: movie-catalog
4. spring-cloud-api-gateway
5. spring-cloud-config-server: under spring-cloud-config-server-github
6. movie-rating service return the user rating info based on users id
7. movie-info service return movie description based on movie id which get from movie-rating service


First run eureka-server: **http://localhost:8761**

1. call to movie-rating service: **http://localhost:8380/ratingsdata/user/foo**


       {
           "userId": "foo",
           "ratings": [
               {
                   "movieId": "1234",
                   "rating": 3
               },
               {
                   "movieId": "5678",
                   "rating": 4
               }
           ]
       }

2. call to movie-info service: **http://localhost:8280/movies/1234**
    

    {
        "movieId": "1234",
        "name": "SpiderMan"
    }

3. now if call happen to movie-catalog-resilience-feign it will call to movie-rating and then get the rating info and call next to movie-info service and finally return the following response
   **http://localhost:8580/feign/resilience/catalog/foo**


    {
        "id": "foo",
        "catalogItems": [
            {
                "name": "SpiderMan",
                "desc": "SpiderManDescription from: ",
                "rating": 3
            },
            {
                "name": "Transformer",
                "desc": "TransformerDescription from: ",
                "rating": 4
            }
        ]
    }

### Dockerize the eureka-discovery-service, movie-catalog-feign-resilience, movie-info, movie-rating service

###Using spring boot maven plugin:
Add the following **<configuration>** section in the build to the every pom in eureka-discovery-service, movie-catalog-feign-resilience, movie-info, movie-rating service

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <image>
                        <name>${artifactId}-1.0</name>
                    </image>
                    <pullPolicy>IF_NOT_PRESENT</pullPolicy>
                </configuration>
            </plugin>
        </plugins>
    </build>

Now all the eureka client service change the url of eureka from localhost to host machine ip: otherwise eureka client will connect to the eureka service

    eureka.client.serviceUrl.defaultZone= http://10.0.0.169:8761/eureka/

####To create image run the following command to every service: 


      mvn spring-boot:build-image -DskipTests=true



In above way you have to create individual docker image and run also individually.

##Docker compose:
   
 1. create docker images first with _**mvn spring-boot:build-image -DskipTests=true**_ and here no need to the eureka client service change the url of eureka from localhost to host machine ip
 2. create docker compose file: see the docker-compose file under the project:
  

### Dockerize using dockerfile: The following url has docker file for spring boot

      https://spring.io/guides/gs/spring-boot-docker/