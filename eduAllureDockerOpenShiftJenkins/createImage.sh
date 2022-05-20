#!/bin/bash
docker build baseImage -t mytestbi
docker build . --no-cache -t 192.168.0.109:5000/mytest
docker push 192.168.0.109:5000/mytest