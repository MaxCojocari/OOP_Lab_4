import java.util.concurrent.TimeUnit;

import javax.swing.tree.VariableHeightLayoutCache;

import actors.EOAccount;
import core.Block;
import core.Blockchain;
import core.LockVaultTx;
import core.SwapTx;
import core.TransferTx;
import core.TransactionPool;
import core.Transaction;

public class Simulation {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        int i = 0;
        TransactionPool TxPool = new TransactionPool();
        
        
        EOAccount Alice = new EOAccount(
            "0xf573d99385c05c23b24ed33de616ad16a43a0919",
            1000, 
            11
        );
        EOAccount Bob = new EOAccount(
            "0x3f83f3199f8e81b010d32f3d6ae5bdc9eab01bb2",
            500, 
            3000
        );
        EOAccount Ciprian = new EOAccount(
            "0xc457c11e2d7b6ed68516cded897c2ab9f41e022c",
            1500, 
            7000
        );
        EOAccount Exchange = new EOAccount(
            "0xe9e83d2917ebd2b78c58011897bbbb0b303317ce",
            0, 
            7000
        );
        
        System.out.println("Balances before:");
        System.out.println("Alice: " + Alice.balanceETH + " ETH " + Alice.balanceUSDT + " USDT");
        System.out.println("Bob: " + Bob.balanceETH + " ETH " + Bob.balanceUSDT + " USDT");
        System.out.println("Ciprian: " + Ciprian.balanceETH + " ETH " + Ciprian.balanceUSDT + " USDT" + "\n");
        



        // ###### SIMULATION1 ######
        // Alice sends to Bob 10 USDT
        // Bob sends to Ciprian 5 ETH
        // Ciprian sends to Bob 1 USDT
        // Alice sends to Ciprian 2 USDT, but her balance is insufficient -> ERROR
        System.out.println("SIMULATION1:\n");
        TxPool.setPoolSize(4);
        
        TransferTx tx = new TransferTx(Alice, Bob, "USDT", 10);
        TxPool.addTransaction(tx);
        System.out.println("Alice sends to Bob 10 USDT:");
        System.out.println("Balance Alice after " + Alice.balanceUSDT);
        System.out.println("Balance Bob after " + Bob.balanceUSDT + "\n");
        
        tx = new TransferTx(Bob, Ciprian, "ETH", 5);
        TxPool.addTransaction(tx);
        System.out.println("Bob sends to Ciprian 5 ETH:");
        System.out.println("Balance Bob after " + Bob.balanceETH);
        System.out.println("Balance Ciprian after " + Ciprian.balanceETH + "\n");
        
        tx = new TransferTx(Ciprian, Bob, "USDT", 1);
        TxPool.addTransaction(tx);
        System.out.println("Ciprian sends to Bob 1 USDT:");
        System.out.println("Balance Ciprian after " + Ciprian.balanceUSDT);
        System.out.println("Balance Bob after " + Bob.balanceUSDT + "\n");
        
        tx = new TransferTx(Alice, Ciprian, "USDT", 2);
        TxPool.addTransaction(tx);
        System.out.println("Alice sends to Ciprian 2 USDT:");
        System.out.println("Balance Alice after " + Alice.balanceUSDT);
        System.out.println("Balance Ciprian after " + Ciprian.balanceUSDT + "\n\n\n\n");
        
        Block block = new Block(i++, null, TxPool.getPool());
        block.mineBlock(1);
        blockchain.addBlock(block);

        // ###### SIMULATION2 ######
        // Alice locks 1 USDT in vault
        // Alice sends 6 ETH to Bob
        // Bob locks 1 USDT in vault
        // Bob swaps 6 ETH for USDT and sends them to Ciprian
        // Bob tries to unlock his funds, but it fails
        System.out.println("SIMULATION2:\n");
        TxPool.setPoolSize(4);

        EOAccount vault = new EOAccount(
            "0xf573d99385c05c23b24ed33de616ad16a43a0919", 
            0, 
            1000
        );

        System.out.println("Balances before:");
        System.out.println("Alice: " + Alice.balanceETH + " ETH " + Alice.balanceUSDT + " USDT");
        System.out.println("Bob: " + Bob.balanceETH + " ETH " + Bob.balanceUSDT + " USDT");
        System.out.println("Ciprian: " + Ciprian.balanceETH + " ETH " + Ciprian.balanceUSDT + " USDT" + "\n");

        LockVaultTx ltx = new LockVaultTx(Alice, vault, 1);
        TxPool.addTransaction(ltx);
        System.out.println("Alice locks 1 USDT in vault:");
        System.out.println("Balance Alice after " + Alice.balanceUSDT);
        System.out.println("Balance vault after " + vault.balanceUSDT + "\n");
        
        tx = new TransferTx(Alice, Bob, "ETH", 6);
        TxPool.addTransaction(tx);
        System.out.println("Alice sends 6 ETH to Bob:");
        System.out.println("Balance Alice after " + Alice.balanceETH);
        System.out.println("Balance Bob after " + Bob.balanceETH + "\n");
        
        ltx = new LockVaultTx(Bob, vault, 1);
        TxPool.addTransaction(ltx);
        System.out.println("Bob locks 1 USDT in vault:");
        System.out.println("Balance Bob after " + Bob.balanceUSDT);
        System.out.println("Balance vault after " + vault.balanceUSDT + "\n");
        
        SwapTx stx = new SwapTx(Bob, Exchange, 6, 0.5);
        TxPool.addTransaction(stx);
        tx = new TransferTx(Bob, Ciprian, "USDT", 3);
        System.out.println("Bob swaps 6 ETH for USDT and sends them to Ciprian:");
        System.out.println("Balance Bob after " + Bob.balanceETH);
        System.out.println("Balance Ciprian after " + Ciprian.balanceUSDT);
        ltx.unlockAmount(Bob, vault);
        System.out.println("\n\n\n\n");

        block = new Block(i++, block.currHash, TxPool.getPool());
        block.mineBlock(1);
        blockchain.addBlock(block);

        // ###### SIMULATION3 ######
        // Alice locks 5 USDT in vault
        // Bob locks 1 USDT in vault
        // Ciprian locks 3 USDT in vault
        // Bob locks another 1 USDT in magicVault
        // Bob sends to Alice 10 ETH 
        // Bob withdraws funds from both vaults
        System.out.println("SIMULATION3:\n");
        TxPool.setPoolSize(5);
        System.out.println("Balances before:");
        System.out.println("Alice: " + Alice.balanceETH + " ETH " + Alice.balanceUSDT + " USDT");
        System.out.println("Bob: " + Bob.balanceETH + " ETH " + Bob.balanceUSDT + " USDT");
        System.out.println("Ciprian: " + Ciprian.balanceETH + " ETH " + Ciprian.balanceUSDT + " USDT" + "\n");

        ltx = new LockVaultTx(Alice, vault, 5);
        TxPool.addTransaction(ltx);
        System.out.println("Alice locks 5 USDT in vault:");
        System.out.println("Balance Alice after " + Alice.balanceUSDT);
        System.out.println("Balance vault after " + vault.balanceUSDT + "\n");

        LockVaultTx ltx0 = new LockVaultTx(Bob, vault, 1);
        TxPool.addTransaction(ltx);
        System.out.println("Bob locks 1 USDT in vault:");
        System.out.println("Balance Bob after " + Bob.balanceUSDT);
        System.out.println("Balance vault after " + vault.balanceUSDT + "\n");
        
        ltx = new LockVaultTx(Ciprian, vault, 3);
        TxPool.addTransaction(ltx);
        System.out.println("Ciprian locks 3 USDT in vault:");
        System.out.println("Balance Ciprian after " + Ciprian.balanceUSDT);
        System.out.println("Balance vault after " + vault.balanceUSDT + "\n");
        
        EOAccount magicVault = new EOAccount(
            "0xf573d99385c05c23b24ed33de616ad16a43a0919", 
            0, 
            0
        );

        LockVaultTx ltxMagic = new LockVaultTx(Bob, magicVault, 1);
        TxPool.addTransaction(ltxMagic);
        System.out.println("Bob locks another 1 USDT in magicVault:");
        System.out.println("Balance Bob after " + Bob.balanceUSDT);
        System.out.println("Balance magicVault after " + magicVault.balanceUSDT + "\n");

        tx = new TransferTx(Bob, Alice, "ETH", 10);
        TxPool.addTransaction(tx);
        System.out.println("Bob sends to Alice 10 ETH:");
        System.out.println("Balance Bob after " + Bob.balanceETH);
        System.out.println("Balance Alice after " + Alice.balanceETH + "\n");
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        ltx0.unlockAmount(Bob, vault);
        ltxMagic.unlockAmount(Bob, magicVault);
        System.out.println("Bob withdraws funds from both vaults:");
        System.out.println("Balance Bob after " + Bob.balanceUSDT + "\n");   
        
        block = new Block(i++, block.currHash, TxPool.getPool());
        block.mineBlock(1);
        blockchain.addBlock(block);
        

        // ###### SIMULATION4 ######
        // Ciprian swaps 4 ETH for USDT
        // Ciprian locks 3 USDT in vault
        // Bob sends 2 ETH to Ciprian
        // Bob sends to Alice 10 ETH 
        TxPool.setPoolSize(4);
        stx = new SwapTx(Ciprian, Exchange, 4, 0.3);
        TxPool.addTransaction(stx);
        ltx = new LockVaultTx(Ciprian, vault, 3);
        TxPool.addTransaction(ltx);
        tx = new TransferTx(Bob, Ciprian, "ETH", 2);
        TxPool.addTransaction(tx);
        tx =  new TransferTx(Bob, Alice, "ETH", 10);
        TxPool.addTransaction(tx);
        
        block = new Block(i++, block.currHash, TxPool.getPool());
        block.mineBlock(1);
        blockchain.addBlock(block);
        

        // ###### SIMULATION5 ######
        // Alice, Bob and Ciprian simultaneously deposit 1 USDT in vault
        // After some enough time they withdraw
        // Bob deposits again 1 USDT
        Alice.receiveUSDT(10);
        Bob.receiveUSDT(10);
        Ciprian.receiveUSDT(10);
        TxPool.setPoolSize(4);
        LockVaultTx ltx1 = new LockVaultTx(Alice, vault, 1);
        TxPool.addTransaction(ltx1);
        LockVaultTx ltx2 = new LockVaultTx(Bob, vault, 1);
        TxPool.addTransaction(ltx2);
        LockVaultTx ltx3 = new LockVaultTx(Ciprian, vault, 1);
        TxPool.addTransaction(ltx3);
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ltx1.unlockAmount(Alice, vault);
        ltx2.unlockAmount(Bob, vault);
        ltx3.unlockAmount(Ciprian, vault);
        
        ltx = new LockVaultTx(Bob, vault, 1);
        TxPool.addTransaction(ltx);
        
        block = new Block(i++, block.currHash, TxPool.getPool());
        block.mineBlock(1);
        blockchain.addBlock(block);

        blockchain.getBlocks();
    }
}