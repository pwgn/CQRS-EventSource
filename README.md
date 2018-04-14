# Install dependencies

ubuntu 17.10

## Java

Is probably installed, else use your favourite browser to get instructions on how to install it.

## Rabbit mq
https://www.rabbitmq.com/

Is used as a event bus. It publishes events stored in the event store.

### installation

`sudo apt install rabbitmq`

It is also possible to run Rabbitmq in a docker container if you check out rabbit mq github page.

## Postman

This one is optional, but it is handy to run the requests.

# Run

1. Make sure rabbitmq is running. It is probably running as a deamon already, anyhow on linux:
`sudo service rabbitmq-server start`

2. Either load the project into your gradle compatible IDE and run from there or run from commandline (project root):
`./gradlew bootRun`

3. ???

4. profit
