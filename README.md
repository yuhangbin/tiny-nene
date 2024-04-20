# tiny-nene

## Modules
- Connection Server (for maintaining user connection)
- Business Server (for handle business logic)
- Infrastructure Server (for storing user data and metadata. such as: NoSQL Database and OLTP Database)
- Middleware Server (for asynchronous communication and high throughput cases. such as: RocketMQ, Kafka, Pulsar)

## Features
- client and server connection keep live (by heartbeat)
- room (A room can contain x people, people which in the room can send message to each other)
- delay message (People in the room need to kill a monster in 1 min to finish this task to get reward.)