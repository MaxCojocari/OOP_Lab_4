package core;

import java.util.ArrayList;

public class Blockchain {
    private ArrayList<Block> blockchain = new ArrayList<Block>();

    public void addBlock(Block block) {
        if (blockchain.size() == 0) {
            if (validateBlock(null, block)) blockchain.add(block); 
        } else {
            Block lastBlock = getPrevBlock();
            if (validateBlock(lastBlock, block)) blockchain.add(block);
        }
    }

    public boolean validateBlock(Block lastBlock, Block newBlock) {
        if (lastBlock == null) {
            return  newBlock.index == 0 && 
                    newBlock.prevHash == null && 
                    newBlock.computeHash().equals(newBlock.currHash);
        }
        
        return  lastBlock.currHash.equals(newBlock.prevHash) && 
                lastBlock.timestamp < newBlock.timestamp && 
                lastBlock.index + 1 == newBlock.index &&
                newBlock.computeHash().equals(newBlock.currHash);
    }

    public boolean validateBlockchain() {
        for (int i = 0; i < blockchain.size() - 1; ++i) {
            if (!validateBlock(blockchain.get(i), blockchain.get(i+1))) return false;
        }
        return true;
    }

    public void getBlocks() {
        for (Block b: blockchain) {
            System.out.println(b.toString());
        }
    }

    public Block getPrevBlock() {
        return blockchain.get(blockchain.size() - 1);
    }
}
