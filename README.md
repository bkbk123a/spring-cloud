# spring-cloud
spring-cloud 관련

# 데이터베이스 생성 
docker run -d -p 1521:1521 -p 8081:81 -v /mnt/c/User/directory/h2:/opt/h2-data -e H2_OPTIONS="-ifNotExists" --name=h2 oscarfonts/h2

# 생성 후 데이터베이스 확인 
localhost:8081

# docker network
docker network create --gateway 172.18.0.1 --subnet 172.18.0.0/16 ecommerce-network

# docker rabbitMq
docker run -d --name rabbitmq --network ecommerce-network -p 15672:15672 -p 5672:5672 -p 15671:15671 -p 5671:5671 -p 4369:4369 -e RABBITMQ_DEFAULT_USER=guest -e RABBITMQ_DEFAULT_PASS=guest rabbitmq:management