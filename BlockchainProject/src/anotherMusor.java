import java.util.concurrent.TimeUnit;

import actors.EOAccount;
import core.Block;
import core.Blockchain;
import core.LockVaultTx;
import core.SwapTx;
import core.TransactionPool;
import core.TransferTx;

public class anotherMusor {
  public static void main(String[] args) {
    EOAccount alice = new EOAccount(
        "0xf573d99385c05c23b24ed33de616ad16a43a0919",
        1000, 
        1000
    );
    EOAccount bob = new EOAccount(
        "0xf573d99385c05c23b24ed33de616ad16a43a0919",
        500, 
        3000
    );
    EOAccount john = new EOAccount(
        "0xc457c11e2d7b6ed68516cded897c2ab9f41e022c",
        1500, 
        7000
    );

    EOAccount exchange = new EOAccount(
        "0xc457c11e2d7b6ed68516cded897c2ab9f41e022c",
        0, 
        7000
    );

    TransactionPool TxPool = new TransactionPool();
    TxPool.setPoolSize(5);

    TransferTx tx = new TransferTx(bob, john, "ETH", 10);
    System.out.println(bob.balanceETH());
    System.out.println(john.balanceETH());
    TxPool.addTransaction(tx);
    
    tx = new TransferTx(bob, john, "ETH", 10);
    System.out.println(bob.balanceETH());
    System.out.println(john.balanceETH());
    TxPool.addTransaction(tx);
    
    System.out.println("Balance ETH bob before" + bob.balanceETH());
    SwapTx stx = new SwapTx(bob, exchange, 50, 0.5);
    TxPool.addTransaction(stx);
    System.out.println("Balance ETH bob after" + bob.balanceETH());
    System.out.println(exchange.balanceETH());
    System.out.println(bob.balanceUSDT());
    
    System.out.println("Another huinea:");
    EOAccount vault = new EOAccount("0xf573d99385c05c23b24ed33de616ad16a43a0919", 0, 0);
    LockVaultTx ltx = new LockVaultTx(alice, vault, 10);
    TxPool.addTransaction(ltx);
    System.out.println(vault.balanceUSDT());
    System.out.println(alice.balanceUSDT());
    ltx = new LockVaultTx(john, vault, 100);
    TxPool.addTransaction(ltx);
    System.out.println(john.balanceUSDT());
    System.out.println(vault.balanceUSDT());
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    ltx.unlockAmount(john, vault);
    System.out.println(john.balanceUSDT());

    Block block = new Block(0, null, TxPool.getPool());
    block.mineBlock(1);
    Blockchain blockchain = new Blockchain();
    blockchain.addBlock(block);
    blockchain.getBlocks();
  }
}
