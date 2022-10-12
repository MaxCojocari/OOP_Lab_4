package core;

import actors.EOAccount;
import global.EthereumObject;

public class Transaction implements EthereumObject {
    private EOAccount sender;
    private EOAccount receiver;
    private boolean checked;

    public Transaction(EOAccount sender, EOAccount receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.checked = false;
    }

    //public abstract String transactionInfo();

    public EOAccount sender() {
        return sender;
    }

    public EOAccount receiver() {
        return receiver;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked() {
        this.checked = true;
    }
    public int chainId() {
        return 144;
    };

    public String currentFork() {
        return "Paris";
    };

    public String nativeCurrency() {
        return "ETH";
    };

    public String consensusAlgorithm() {
        return "PoS";
    };
}
