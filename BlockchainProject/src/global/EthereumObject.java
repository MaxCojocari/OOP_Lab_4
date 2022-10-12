package global;

public interface EthereumObject {
    public abstract int chainId();
    public abstract String currentFork();
    public abstract String nativeCurrency();
    public abstract String consensusAlgorithm();
}
