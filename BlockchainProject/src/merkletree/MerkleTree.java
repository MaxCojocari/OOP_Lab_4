package merkletree;

import java.security.*;
import java.util.*;

import core.LockVaultTx;
import core.SwapTx;
import core.Transaction;
import core.TransferTx;
import global.EthereumObject;

public class MerkleTree implements EthereumObject {	
    private ArrayList<Object> leaves;
    private ArrayList<Leaf> leavesObjects = new ArrayList<Leaf>();

    public MerkleTree(ArrayList<Object> leaves) {
        this.leaves = leaves;
    }

    public Leaf getMerkleRoot() {
        Queue<Leaf> queue = new LinkedList<Leaf>();

        for (Object t: leaves) {
            if (t instanceof TransferTx) {
                queue.add(
                    new Leaf(computeHash(((TransferTx)t).transactionInfo()), null, null)
                );
            } else if (t instanceof SwapTx) {
                queue.add(
                    new Leaf(computeHash(((SwapTx)t).transactionInfo()), null, null)
                );
            } else if (t instanceof LockVaultTx) {
                queue.add(
                    new Leaf(computeHash(((LockVaultTx)t).transactionInfo()), null, null)
                );
            }
        }

        while (queue.size() != 1) {
            Leaf leftLeaf = queue.remove();
            Leaf rightLeaf = queue.remove();

            Leaf newLeaf = new Leaf(
                computeHash(leftLeaf.getHash() + rightLeaf.getHash()), 
                leftLeaf, 
                rightLeaf
            );
            queue.add(newLeaf);
        }

        return queue.remove();
    }

    public void getLeaves() {
        for (Object t: leaves) {
            String l = "";
            if (t instanceof TransferTx) {
                l = ((TransferTx)t).transactionInfo(); 
            } else if (t instanceof SwapTx) {
                l = ((SwapTx)t).transactionInfo(); 
            } else if (t instanceof LockVaultTx) {
                l = ((LockVaultTx)t).transactionInfo(); 
            }
            leavesObjects.add(new Leaf(computeHash(l), null, null));
        }

        if (leavesObjects.size() % 2 != 0) {
            leavesObjects.add(new Leaf(computeHash(""), null, null));
        }
    }

    public String computeHash(String input) {
        try {
            MessageDigest SHA256 = MessageDigest.getInstance("SHA-256");
            byte[] hash = SHA256.digest(input.getBytes("UTF-8"));
            
            StringBuffer hexString = new StringBuffer();
            for (byte b: hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
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
