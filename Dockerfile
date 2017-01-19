FROM alpine

ADD . /tmp
RUN echo http://dl-cdn.alpinelinux.org/alpine/v3.5/main >/etc/apk/repositories && \
    echo http://dl-cdn.alpinelinux.org/alpine/v3.5/community >>/etc/apk/repositories && \
    apk -U upgrade && \
    apk add -U maven openjdk8
RUN cd /tmp && \
    mvn verify

EXPOSE 8000 9000
WORKDIR /tmp
CMD ./start.sh
