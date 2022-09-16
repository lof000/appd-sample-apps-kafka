#!/bin/bash
for i in {1..500}; do
  echo "counter: $i"
  curl http://localhost:8081/send/kafka
  sleep 5
done
