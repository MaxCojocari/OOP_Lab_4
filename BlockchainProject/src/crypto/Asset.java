package crypto;

import global.EthereumObject;

abstract class Asset implements EthereumObject {
    public abstract String name();
    public abstract String symbol();
    public abstract int decimals();
    public abstract void transfer(int amount, String from, String to);
    public abstract int balanceOf(String address);

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
