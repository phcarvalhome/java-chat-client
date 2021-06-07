# Chat Client

Distributed Java application client using JMS, SOAP and Swing UI.

---

## Instituto Federal de Educação, Ciência e Tecnologia do Ceará

### Computer Engineering - Parallel and Distributed Programming 
### Project of Messaging and Web Services Oriented Middleware

- Objective: Implement a message exchange system with offline message control:
  - Communication between clients must be implemented using any distributed communication technology studied in the discipline (Sockets, RMI or CORBA).
  - Clients must have a “contact name” and this name must be used to register friends who will be able to exchange messages, and this list must be displayed in the GUI at all times.
  - Communication must be carried out online or offline, being necessary to allow customers to change state between on and off.
  - When customers are online, messages from your contacts are delivered instantly.
  - If they are offline, the messages must be left in an offline message server, which must be accessed through a WebService.
  - There must be a message queue managed by a Message Oriented Middleware on the messaging server for each client.
  - If when trying to send a message and the contact is offline, it must be sent to the recipient's queue.
  - When entering the system, each new client must ask the message server, viaWebService, to create a queue for it.
