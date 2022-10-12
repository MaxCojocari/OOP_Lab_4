package crypto;

import java.util.*;

public class USDTCoin extends Token {
    private String owner;
    private int totalSupply;
    public Map<String, Integer> balances = new HashMap<String, Integer>();
    public Map<String, Map<String, Integer>> allowances = new HashMap<String, Map<String, Integer>>();

    public USDTCoin( 
        String owner, 
        int totalSupply
    ) {
        super("Tether USD", "USDT");
        this.owner = owner;
        this.totalSupply = totalSupply;
    }

    public String getOwner() {
        return owner;
    }

    public int totalSupply() {
        return totalSupply;
    }

    public void transfer(int amount, String from, String to) {
        if (amount <= 0) return;
        balances.put(to, balances.get(to) + amount);
        balances.put(from, balances.get(from) - amount);
    }

    public int balanceOf(String address) {
        return balances.get(address).intValue();
    }

    public int allowance(String owner, String spender) {
        return allowances.get(owner).get(spender);
    };

    public void approve(String owner, String spender, int value) {
        if (value <= 0) return;
        Map<String, Integer> s = new HashMap<String, Integer>();
        s.put(spender, value);
        allowances.put(owner, s);
    }

    @Override
    public int decimals() {
        return 9;
    }
}
