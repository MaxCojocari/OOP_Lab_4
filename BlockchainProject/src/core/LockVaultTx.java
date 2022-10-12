package core;

import java.util.*;

import actors.EOAccount;

public class LockVaultTx extends Transaction {
    private String assetSymbol;
    private Map<String, Integer> amountLocked = new HashMap<String, Integer>();
    private Map<String, Boolean> isLocked = new HashMap<String, Boolean>();
    private long timeLock;
    private long startTime;

    
    public LockVaultTx(EOAccount sender, EOAccount vault, int amountLockedInVault) {
        super(sender, vault);
        assetSymbol = "USDT";
        timeLock = 10;
        this.startTime = System.currentTimeMillis();
        if (sender.sendUSDT(amountLockedInVault, vault.address)) {
            vault.receiveUSDT(amountLockedInVault);
            amountLocked.put(sender.address, amountLockedInVault);
            isLocked.put(sender.address, true);
        }
    }

    public String transactionInfo() {
        String s = "Sender:\t\t" + super.sender() + "\n";
        s += "Receiver:\t" + super.receiver() + "\n";
        s += "Amount:\t\t" + timeLock + " " + assetSymbol;
        return s;
    }

    public boolean isAmountLocked(EOAccount owner) {
        return isLocked.get(owner.address);
    }

    public void unlockAmount(EOAccount sender, EOAccount vault) {
        if (startTime + timeLock < System.currentTimeMillis()) {
            isLocked.put(sender.address, false);           
            if (vault.sendUSDT(amountLocked.get(sender.address), sender.address)) 
                sender.receiveUSDT(amountLocked.get(sender.address));
        } else System.out.println("EARLY_WITHDRAWING");
    }
}
