package actors;

public class EOAccount extends Account {
    public String address;
    public int nonce;
    public double balanceETH;
    public double balanceUSDT;

    public EOAccount(String address, double balanceETH, double balanceUSDT) {
        this.address = address;
        this.nonce = 0;
        this.balanceETH = balanceETH;
        this.balanceUSDT = balanceUSDT;
    }

    public String address() {
        return address;
    };

    public int nonce() {
        return nonce;
    };

    public void incNonce() {
        nonce++;
    };
    
    public boolean sendETH(double amount, String to) {
        if (balanceETH < amount) {
            System.out.println("INSUFFICIENT_ETH_BALANCE");
            return false;
        }
        if (amount <= 0) return false;
        balanceETH -= amount;
        return true;
    }
    
    public boolean receiveETH(double amount) {
        if (amount <= 0) return false;
        balanceETH += amount;
        return true;
    }

    public boolean sendUSDT(double amount, String to) {
        if (balanceUSDT < amount) {
            System.out.println("INSUFFICIENT_USDT_BALANCE");
            return false;
        }
        if (amount <= 0) return false;
        balanceUSDT -= amount;
        return true;
    }
    
    public boolean receiveUSDT(double amount) {
        if (amount <= 0) return false;
        balanceUSDT += amount;
        return true;
    }

    public double balanceUSDT() {
        return balanceUSDT;
    }

    public double balanceETH() {
        return balanceETH;
    }
}
