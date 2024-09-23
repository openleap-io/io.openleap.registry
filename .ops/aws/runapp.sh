#!/usr/bin/env bash
echo 'Starting Spring Boot app'
cd '/home/ubuntu/app'
java -Dserver.port=80 -jar openleap-registry.jar &
