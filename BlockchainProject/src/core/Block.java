package core;

import java.security.*;
import java.util.ArrayList;
import actors.Miner;
import merkletree.MerkleTree;
import global.EthereumObject;
import core.*;

public class Block implements EthereumObject{
    public int index;
    public long timestamp;
    public String currHash;
    public String prevHash;
    public String merkleRoot;
    public ArrayList<Object> transactions;
    public int nonce;

    public Miner minerJora = new Miner(
        "0x52bc8ca51ec4a6df432daddc3d4b092b1cde7417c7a47e198ac66a6c314c87a4", 
        0
    );
    private int miningRewards = 69;

    public Block(int index, String prevHash, ArrayList<Object> transactions) {
        this.index = index;
        this.timestamp = System.currentTimeMillis();
        this.prevHash = prevHash;
        this.transactions = transactions;
        this.merkleRoot = (new MerkleTree(transactions)).getMerkleRoot().getHash();
        currHash = computeHash();
    }

    public String computeHash() {
        String input = index + timestamp + prevHash + nonce;

        for (Object t: transactions) {
            if (t instanceof TransferTx) {
                input += ((TransferTx)t).transactionInfo();
            } else if (t instanceof SwapTx) {
                input += ((SwapTx)t).transactionInfo();
            } else if (t instanceof LockVaultTx) {
                input += ((LockVaultTx)t).transactionInfo();
            }
        }

        try {
            MessageDigest SHA256 = MessageDigest.getInstance("SHA-256");
            byte[] hash = SHA256.digest(input.getBytes("UTF-8"));
            
            StringBuffer hexString = new StringBuffer();
            for (byte b: hash) {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void mineBlock(int difficulty) {
        nonce = 0;
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!currHash.substring(0, difficulty).equals(target)) {
            nonce++;
            currHash = computeHash();
        }
        if (currHash.substring(0, difficulty).equals(target)) 
            minerJora.mineBlock(new Block(index, prevHash, transactions), miningRewards);
    }

    public String toString() {
        String s = "Block # " + index + "\n";
        s = s + "PreviousHash:\t" + prevHash + "\n";
        s = s + "Timestamp:\t" + timestamp + "\n";
        s = s + "Nonce:\t\t" + nonce + "\n";
        s = s + "CurrentHash:\t" + currHash + "\n";
        s = s + "Merkle root:\t" + merkleRoot + "\n";
        return s;
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
