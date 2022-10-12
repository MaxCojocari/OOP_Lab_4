package crypto;

import java.util.*;

public class ETHCoin extends Currency {
    private int totalSupply;
    public Map<String, Integer> balances = new HashMap<String, Integer>();
    
    public ETHCoin(int totalSupply) {
        super("Ethereum", "ETH");
        this.totalSupply = totalSupply;
    }

    public void transfer(int amount, String from, String to) {
        if (amount <= 0) return;
        balances.put(to, balances.get(to) + amount);
        balances.put(from, balances.get(from) - amount);
    }

    public int balanceOf(String address) {
        return balances.get(address).intValue();
    }

    public int totalSupply() {
        return totalSupply;
    }
}