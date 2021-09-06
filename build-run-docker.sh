mvn verify && docker build -t url-shortener . 

docker tag url-shortener:latest damirnovosel/url-shortener:1.0.0

sudo docker login 
sudo docker push damirnovosel/url-shortener:1.0.0


docker run -it -p 8080:8080 url-shortener:1.0.0


# usage from terminal

docker pull damirnovosel/url-shortener:1.0.0

docker images | grep url-shortener

docker run -it -p 8080:8080 damirnovosel/url-shortener:1.0.0



