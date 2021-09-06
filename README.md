# simple url shortener service

Service is implemented as a Quarkus service, with two endpoints
POST http://localhost:8080/shorten
GET  http://localhost:8080/{shortUrl}

Simple implementation:
- persistance layer is in memory
- no security
- simple short url generator

Improvements needed for production:
- estimate expected load: how many shortenings per second, redirects per second, expiry time, etc, since this affect architecture immensely.
- implement persistance with distributed Redis KV store
- implement OAuth2 authentication with JWT
- implement more complex url generator, that returns unpredictable urls
- protect from excessive shortenings attack by users
- user handling
- potential monetization by offline-processing of redirections


# Usage
Download docker image of importer
>  docker pull damirnovosel/url-shortener:0.0.1

Run docker container
>docker run -it damirnovosel/url-shortener:0.0.1

Call endpoints and check response
> curl localhost:8080/1 -v
> curl -X POST localhost:8080/shorten -H "Content-Type: text/plain" -d "http://google.com" -v
> curl localhost:8080/2 -v

