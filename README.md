# spring-cloud-eureka-feign-hystrix-config-apigw-resilience

1. Spring-cloud-netfix-eureka-discovery-server: under eureka-discovery-service
2. Spring-cloud-feign-resilience: under movie-catalog-feign-resilience
3. spring-cloud-hystrix-restremplate: movie-catalog
4. spring-cloud-api-gatway
5. spring-cloud-config-server: under spring-cloud-config-server-github


movie-catalog-feign-resilience will call to movie-rating and then get the rating info and call next to movie-info service and finally return response
