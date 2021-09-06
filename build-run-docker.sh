mvn verify && docker build -t url-shortener . 

docker tag url-shortener:latest damirnovosel/url-shortener:1.0.0

sudo docker login 
sudo docker push damirnovosel/url-shortener:1.0.0


docker run -it -p 8080:8080 url-shortener:1.0.0


# usage from terminal

docker pull damirnovosel/url-shortener:1.0.0

docker images | grep url-shortener

docker run -it -p 8080:8080 damirnovosel/url-shortener:1.0.0


curl localhost:8080/1 -v
curl -X POST localhost:8080/shorten -H "Content-Type: text/plain" -d "http://google.com" -v
curl localhost:8080/2 -v
