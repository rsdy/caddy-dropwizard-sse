Dropwizard SSE Example
----------------------

This is a minimal example to demonstrate Server-Sent Events using Dropwizard 1.0.
A `Caddyfile` is provided as an example for getting to web-scale.

You can use docker to get started quickly:

    docker build -t caddy-dropwizard .
    docker run -p 8000:8000 -p 9000:9000 -d caddy-dropwizard

Dropwizard will bind to port `:9000`, and caddy to `:8000`.
You can test the API is working through caddy using:

    curl localhost:8000/application/events
    
And directly the Dropwizard server using:

    curl localhost:9000/application/events    

License
-------
This project is in the public domain.
