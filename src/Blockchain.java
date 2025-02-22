import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> blockchain;
    private int difficulty;

   
    public Blockchain(int difficulty) {
        this.blockchain = new ArrayList<>();
        this.difficulty = difficulty;
        
        
        blockchain.add(createGenesisBlock());
    }

   
    private Block createGenesisBlock() {
        List<Transaction> transactions = new ArrayList<>();
        return new Block(0, transactions, "0");
    }

   
    public void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }

   
    public Block getLatestBlock() {
        return blockchain.get(blockchain.size() - 1);
    }

   
    public void displayBlockchain() {
        for (Block block : blockchain) {
            System.out.println(block);
        }
    }

    
    public Block getBlockByIndex(int index) {
        if (index >= 0 && index < blockchain.size()) {
            return blockchain.get(index);
        } else {
            System.out.println("Block not found at index " + index);
            return null;
        }
    }

   
    public Block getBlockByTransactionId(String transactionId) {
        for (Block block : blockchain) {
            for (Transaction tx : block.getTransactions()) {
                if (tx.getTransactionId().equals(transactionId)) {
                    return block;
                }
            }
        }
        System.out.println("Transaction with ID " + transactionId + " not found.");
        return null;
    }
}
