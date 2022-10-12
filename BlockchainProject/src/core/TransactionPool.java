package core;

import java.util.*;
import actors.EOAccount;
import crypto.USDTCoin;
import crypto.ETHCoin;

public class TransactionPool {
    private USDTCoin USDT = new USDTCoin(
        "0xC6CDE7C39eB2f0F0095F41570af89eFC2C1Ea828",    
        1000000
    );
    private ETHCoin ETH = new ETHCoin(1000000);

    public EOAccount alice = new EOAccount(
        "0xf573d99385c05c23b24ed33de616ad16a43a0919",
        1000, 
        1000
    );
    public EOAccount bob = new EOAccount(
        "0xf573d99385c05c23b24ed33de616ad16a43a0919",
        500, 
        3000
    );
    public EOAccount john = new EOAccount(
        "0xc457c11e2d7b6ed68516cded897c2ab9f41e022c",
        1500, 
        7000
    );

    private ArrayList<Object> T = new ArrayList<Object>();
    private int maxPoolSize;

    public void setPoolSize(int size) {
        maxPoolSize = size;
    }

    public Object getTransaction(int i) {
        return T.get(i);
    }

    public int getNrTransactions() {
        return T.size();
    }

    public void addTransaction(Object t) {
        ((Transaction) t).setChecked();
        if (isPoolFull()) T.clear();
        T.add(t);
    }

    public boolean isPoolFull() {
        return T.size() > maxPoolSize;
    }

    public boolean checkTotalBTC() {
        return alice.balanceUSDT() + bob.balanceUSDT() <= USDT.totalSupply();
    }

    public boolean checkTotalETH() {
        return alice.balanceETH() + bob.balanceETH() <= ETH.totalSupply();
    }

    public ArrayList<Object> getPool() {
        return T;
    }
}
