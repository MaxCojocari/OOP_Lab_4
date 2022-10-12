# Blockchain simulation. Part 2: Inheritance

![image](https://user-images.githubusercontent.com/92053176/192138851-6466d959-734a-4a0c-ab41-3c604095add6.png)

This project implements a simple analogue of a blockchain system that can deal with transactions, accounts and their balances.

In order to run this project, complete the following steps:

- `git clone git@github.com:MaxCojocari/OOP_Lab_3.git`
- `cd OOP_Lab_3/BlockchainProject`
- `javac -cp src -d out src/dir/*.java`, where `dir = actors, core, crypto, merkletree`
- `javac -cp src -d out src/*.java`
- `java -cp out Simulation`

## Inheritance implementation

The core elements of blockchain model *block*, *asset*, *account* and *transaction* are children of an abstract class *EthereumObject* which contains the main information about the system in general. Each children of the parent classes mentioned above either implements, or overrides methods from inherited (abstract) classes from higher levels down to descendents. 

Furthermore, the `Object` class is the parent class of all the classes in Java by default. In other words, it is the topmost class of Java. Therefore, the concept *everything is an object* so every class provided by standard library is a descendent of a common-to-all class, typically called `Object` can be clearly seen here.

For more detailes about inheritance in this project, see the UML class diagrams below.

![oop_lab3_global](https://user-images.githubusercontent.com/92053176/194164887-b52a57fc-b676-4f36-8dc1-702218778b57.png)
![image](https://user-images.githubusercontent.com/92053176/194158270-60ba3cc1-671b-4b9a-8150-214be6216b8d.png)
![oop_lab3_crypto](https://user-images.githubusercontent.com/92053176/194158407-9d8085a6-3fb9-4cf4-ba0e-4421a0453105.png)

