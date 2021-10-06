# Docker file for graph2table ChRIS plugin app
#
# Build with
#
#   docker build -t <name> .
#
# For example if building a local version, you could do:
#
#   docker build -t local/pl-graph2table .
#
# In the case of a proxy (located at 192.168.13.14:3128), do:
#
#    docker build --build-arg http_proxy=http://192.168.13.14:3128 --build-arg UID=$UID -t local/pl-graph2table .
#
# To run an interactive shell inside this container, do:
#
#   docker run -ti --entrypoint /bin/bash local/pl-graph2table
#
# To pass an env var HOST_IP to container, do:
#
#   docker run -ti -e HOST_IP=$(ip route | grep -v docker | awk '{if(NF==11) print $9}') --entrypoint /bin/bash local/pl-graph2table
#

FROM python:3.8.2-buster
LABEL maintainer="Sandip Samal <sandip.samal@childrens.harvard.edu>"

ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64/
RUN apt update -y && apt-get install -y software-properties-common && \
    apt-add-repository 'deb http://security.debian.org/debian-security stretch/updates main' && apt update -y && \
    apt-get install -y openjdk-11-jdk-headless && \
    export JAVA_HOME && \
    apt-get clean

WORKDIR /usr/local/src

COPY java .

COPY requirements.txt .
RUN pip install -r requirements.txt

COPY . .
RUN pip install .

# START WEBAPP SERVICE
CMD [ "graph2table","--help" ]

