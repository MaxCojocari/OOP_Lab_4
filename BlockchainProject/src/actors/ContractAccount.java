package actors;

public class ContractAccount extends Account {
    public String address;
    public int nonce;
    public int balanceETH;
    public String storageHash;
    public String codeHash;

    public ContractAccount(
        String address, 
        int balanceETH,
        String storageHash,
        String codeHash
    ) {
        this.address = address;
        this.nonce = 0;
        this.balanceETH = balanceETH;
        this.storageHash = storageHash;
        this.codeHash = codeHash;
    }

    public String address() {
        return address;
    };

    public int nonce() {
        return nonce;
    };

    public int balance() {
        return balanceETH;
    };

    public String storageHash() {
        return storageHash;
    }

    public String codeHash() {
        return codeHash;
    }

    public void incNonce() {
        nonce++;
    };
    
    public void sendETH(double amount, String to) {
        if (amount <= 0) return;
        balanceETH -= amount;
    }

    public void receiveETH(double amount) {
        if (amount <= 0) return;
        balanceETH += amount;
    }
}
