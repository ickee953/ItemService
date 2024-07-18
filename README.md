# ItemService

First of all you need to deploy [StorageService](https://github.com/ickee953/StorageService) and [PictureService](https://github.com/ickee953/PictureService):

For StorageService:
  1. $ git clone https://github.com/ickee953/StorageService.git
  2. $ cd StorageService
  3. $ mvn clean package
  4. $ docker build -t storage-api . && docker run storage-api

For PictureService:
  1. $ git clone https://github.com/ickee953/PictureService.git
  2. $ cd PictureService
  3. $ mvn clean package
  4. $ docker build -t picture-api . && docker run picture-api
     
After that you also need to run postgresql with seettings from db-compose-env.yaml:
  5. $ docker-compose -f db-compose-env.yaml up

PictureService and ItemService has [Apache Kafka](https://kafka.apache.org/quickstart) service that can produce and consume picure files. Apache Kafka can be started using ZooKeeper. To get started with either configuration follow below.

Download, extract from [here](https://www.apache.org/dyn/closer.cgi?path=/kafka/3.7.1/kafka_2.13-3.7.1.tgz) and run the following commands in order to start all services in the correct order:
  
  6. $ tar -xzf kafka_2.13-3.7.1.tgz
  7. $ cd kafka_2.13-3.7.1
  8. $ ./bin/zookeeper-server-start.sh config/zookeeper.properties

Open another terminal session and run:

  9. $ ./bin/kafka-server-start.sh config/server.properties
