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

# Usage

Start postman, load the collection and the environment variables located in the postman directory.

For the PUT queries you will need to specify a version of the entity you would like to update. The version represents the current state of the entity you would like to modify. For each modification the version number will be incremented.

Example:
```

POST /cars
{
    "version": 0,
    "id": "81",
    "rate": 1337,
    "carModel": "Volvo V70",
    "available": true
}

# PUT checkin/checkout requests are on the format:
# PUT /cars/<id>/checkin/<version>

PUT /cars/81/checkin/0

GET /cars
{
    "version": 1, # note that version is incremented
    "id": "81",
    "rate": 1337,
    "carModel": "Volvo V70",
    "available": true
}


# increment version for the next change request, else the request will fail
PUT /cars/81/checkout/1
```
