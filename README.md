# simple url shortener service

Service is implemented as a quarkus.io REST service, with two endpoints:
- POST http://localhost:8080/shorten
- GET  http://localhost:8080/{shortUrl}

Simple implementation:
- persistance layer is in memory
- no security
- simple short url generator

Improvements needed for production:
- estimate expected load: how many shortenings per second, redirects per second, expiry time, etc, since this affect architecture immensely
- implement persistance with distributed Redis KV store
- implement OAuth2 authentication with JWT
- implement more complex url generator, that returns unpredictable urls
- protect from excessive shortenings attack by users
- user handling
- potential monetization by offline-processing of redirections stats


# Usage
Download docker image from dockerhub
>  docker pull damirnovosel/url-shortener:1.0.0

Run docker container
> docker run -it -p 8080:8080 damirnovosel/url-shortener:1.0.0

Call endpoints and check response
> curl localhost:8080/1 -v
  should return 404
> curl -X POST localhost:8080/shorten -H "Content-Type: text/plain" -d "http://google.com" -v
  returns shortened url, for example: '2'
> curl localhost:8080/2 -v
  redirects to 'http://google.com'

Open web browser and navigate to: localhost:8080/2
Browser will redirect you to http://google.com
