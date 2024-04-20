# tiny-nene

## Modules
1. Connection Server (for maintaining user connection)
2. Business Server (for handle business logic)
3. Infrastructure Server (for storing user data and metadata. such as: NoSQL Database and OLTP Database)
4. Middleware Server (for asynchronous communication and high throughput cases. such as: RocketMQ, Kafka, Pulsar)

## Features
1. client and server connection keep live (by heartbeat)
2. room (A room can contain x people, people which in the room can send message to each other)
3. delay message (People in the room need to kill a monster in 1 min to finish this task to get reward.)