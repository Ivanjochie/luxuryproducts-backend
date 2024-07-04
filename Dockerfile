FROM ubuntu:latest
LABEL authors="Ivan Kroetkov"

ENTRYPOINT ["top", "-b"]