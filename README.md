# spring-cloud
spring-cloud 관련

# docker network
docker network create --gateway 172.18.0.1 --subnet 172.18.0.0/16 ecommerce-network

# docker rabbitMq
docker run -d --name rabbitmq --network ecommerce-network -p 15672:15672 -p 5672:5672 -p 15671:15671 -p 5671:5671 -p 4369:4369 -e RABBITMQ_DEFAULT_USER=guest -e RABBITMQ_DEFAULT_PASS=guest rabbitmq:management