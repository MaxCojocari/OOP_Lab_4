package core;

import java.util.*;
import actors.EOAccount;

public class Wallet extends EOAccount {
    private String owner;
    private Map<String, Integer> coins = new HashMap<String, Integer>();

    public Wallet (
        String owner, 
        Map<String, Integer> coins, 
        String address,
        int balanceETH,
        int balanceUSDT 
    ) {
        super(address, balanceETH, balanceUSDT);
        this.owner = owner;
        this.coins = coins;

    }

    public String getOwner() {
        return owner;
    }

    public Map<String, Integer> getCoins() {
        return coins;
    }

    public int getTokenAmount(String coinName) {
        return coins.get(coinName);
    }

    public void substractAmount(String coinName, int amount) {
        if (amount <= 0) return;
        coins.put(coinName, coins.get(coinName) - amount);
    }

    public void addAmount(String coinName, int amount) {
        if (amount <= 0) return;
        coins.put(coinName, coins.get(coinName) + amount);
    }

    public ArrayList<String> getCoinsList() {
        ArrayList<String> coinsList = new ArrayList<String>();
        
        for (Map.Entry<String, Integer> entry : coins.entrySet()) {
            System.out.println(entry.getKey());
            coinsList.add(entry.getKey());
        }
            
        return coinsList;
    }
}
