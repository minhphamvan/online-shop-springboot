# mysql
start-mysql:
	sudo docker-compose up -d mysql_shop_springboot

stop-mysql:
	sudo docker stop mysql_shop_springboot

remove-mysql:
	sudo docker rm -f mysql_shop_springboot

# build from Dockerfile
build-image:
	sudo docker build --tag=shop-springboot:0.0.1 .

# run container from image
run-app:
	sudo docker run -p 8080:8080 shop-springboot:0.0.1
