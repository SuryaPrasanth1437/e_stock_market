After importing the project to eclipse, please build the project using mvn clean install

-------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------

Grafana and prometheus :
docker -p 3000:3000 grafana/grafana
docker -p 9090:9090 -v /E:/e-stock-market-fse2/promethus.yml prom/prometheus

grafana uri: http://172.21.208.1:3000/d/wpOUmxQnz/go_gc_duration_seconds?orgId=1
prom/prometheus uri: http://172.21.208.1:9090/graph?g0.expr=go_gc_duration_seconds&g0.tab=0&g0.stacked=0&g0.show_exemplars=1&g0.range_input=1h&g0.end_input=2022-04-25%2002%3A51%3A02&g0.moment_input=2022-04-25%2002%3A51%3A02

-------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------

kafka commands
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
.\bin\windows\kafka-server-start.bat .\config\server.properties
.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic stock-market
.\bin\windows\kafka-console-producer.bat --topic stock-market --bootstrap-server localhost:9092
.\bin\windows\kafka-console-consumer.bat --topic stock-market --from-beginning --bootstrap-server localhost:9092


-------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------

swagger url
http://localhost:8081/swagger-ui/index.html#/company-details-controller/getCompanyDetailsByCompanyCode
http://localhost:8082/swagger-ui/index.html#/stock-price-controller/viewStockDetails

--------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------
download sonarqube and run the StartSonar.bat

mvn clean install
clean org.jacoco:jacoco-maven-plugin:prepare-agent install
sonar:sonar -Dsonar.login=<authentication token>

--------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------

download kibana, elastic search and logstash

1. changes the security setting to false in elasticsearch.yml and run the elasticsearch.bat
2. uncomment the host in kibana.yml and run the kibana.bat
3. add the logstash.conf in bin location and run the logstash from root directory with this command bin\logstash -f .\bin\logstash.conf
4. run the elasticsearch-create-enrollment-token.bat if needed