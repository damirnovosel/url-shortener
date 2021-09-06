mvn verify && docker build -t url-shortener . 

docker tag url-shortener:1.0.0 damirnovosel/url-shortener:1.0.0

docker push damirnovosel/freshcells:1.0.0

docker run -it url-shortener:0.0.1-SNAPSHOT


# usage from terminal

docker pull damirnovosel/url-shortener:1.0.0

docker images | grep fresh

docker run -it damirnovosel/url-shortener:1.0.0
