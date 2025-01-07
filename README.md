# spring-cloud
spring-cloud 관련

[ 데이터베이스 생성 ]
docker run -d -p 1521:1521 -p 8081:81 -v /mnt/c/User/directory/h2:/opt/h2-data -e H2_OPTIONS="-ifNotExists" --name=h2 oscarfonts/h2

[ 생성 후 데이터베이스 확인 ] 
localhost:8081

