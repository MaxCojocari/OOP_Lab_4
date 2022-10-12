package merkletree;

import global.EthereumObject;

public class Leaf implements EthereumObject{
	private String hash;
    private Leaf rightChild;
    private Leaf leftChild;

    public Leaf(String hash, Leaf rightChild, Leaf leftChild) {
        this.hash = hash;
        this.rightChild = rightChild;
        this.leftChild = leftChild;
    }

    public String getHash() {
        return hash;
    }

    public Leaf leftChild() {
        return leftChild;
    }

    public Leaf rightChild() {
        return rightChild;
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
