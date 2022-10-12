package actors;

import java.util.*;
import core.Block;

public class Miner extends EOAccount {
    private int rewardsETH;
    private ArrayList<Block> blocksMined = new ArrayList<Block>();

    public Miner(String address, int balanceETH) {
        super(address, balanceETH, 0);
        this.rewardsETH = 0;
    }

    public int minerRewards() {
        return rewardsETH;
    }

    public void mineBlock(Block b, int rewards) {
        if (rewards <= 0) return;
        blocksMined.add(b);
        gatherRewards(rewards);
    }
    
    public void gatherRewards(int newRewards) {
        if (newRewards <= 0) return;
        rewardsETH += newRewards;
    }
}
