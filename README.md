# Blockchain simulation. Part 2: Polymorphism

![image](https://user-images.githubusercontent.com/92053176/192138851-6466d959-734a-4a0c-ab41-3c604095add6.png)

This project implements a simple analogue of a blockchain system that can deal with transactions, accounts and their balances.

In order to run this project, complete the following steps:

- `git clone git@github.com:MaxCojocari/OOP_Lab_4.git`
- `cd OOP_Lab_3/BlockchainProject`
- `javac -cp src -d out src/dir/*.java`, where `dir = actors, core, crypto, merkletree`
- `javac -cp src -d out src/*.java`
- `java -cp out Simulation`

## Scenarios implemented

Actors in this use cases are Alice, Bob and Ciprian.

1. SIMULATION 1
```
Alice sends to Bob 10 USDT
Bob sends to Ciprian 5 ETH
Ciprian sends to Bob 1 USDT
Alice sends to Ciprian 2 USDT, but her balance is insufficient -> ERROR
```

2. SIMULATION 2
```
Alice locks 1 USDT in vault
Alice sends 6 ETH to Bob
Bob locks 1 USDT in vault
Bob swaps 6 ETH for USDT and sends them to Ciprian
Bob tries to unlock his funds, but it fails
```

3. SIMULATION 3
```
Alice locks 5 USDT in vault
Bob locks 1 USDT in vault
Ciprian locks 3 USDT in vault
Bob locks another 1 USDT in magicVault
Bob sends to Alice 10 ETH 
Bob withdraws funds from both vaults
```

4. SIMULATION 4
```
Ciprian swaps 4 ETH for USDT
Ciprian locks 3 USDT in vault
Bob sends 2 ETH to Ciprian
Bob sends to Alice 10 ETH 
```

5. SIMULATION 5
```
Alice, Bob and Ciprian simultaneously deposit 1 USDT in vault
After some enough time they withdraw
Bob deposits again 1 USDT
```

## Hierarchy

For more detailes about hierarchy in this project, see the UML class diagrams below.

![oop_lab3_global](https://user-images.githubusercontent.com/92053176/194164887-b52a57fc-b676-4f36-8dc1-702218778b57.png)
![image](https://user-images.githubusercontent.com/92053176/194158270-60ba3cc1-671b-4b9a-8150-214be6216b8d.png)
![oop_lab3_crypto](https://user-images.githubusercontent.com/92053176/194158407-9d8085a6-3fb9-4cf4-ba0e-4421a0453105.png)

