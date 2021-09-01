#!/bin/bash
# Executes endless http requests
#
# First argument is the URL of the web service
# Second argument is the initial number to send as request parameter

URL=$1
COUNTER=$2
while :
do
  echo "Requesting fibonacci number $COUNTER"
	curl -s $URL/fib\?n\=$COUNTER >> /dev/null;
	((COUNTER++));
done
