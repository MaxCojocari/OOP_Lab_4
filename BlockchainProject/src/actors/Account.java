package actors;

import global.EthereumObject;

abstract class Account implements EthereumObject {
    public String address;
    public double nonce;
    public double balanceETH;

    public abstract String address();
    public abstract int nonce();
    public abstract void incNonce();
    
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
