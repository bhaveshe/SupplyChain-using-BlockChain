import java.security.MessageDigest;
import java.util.*;

public class Block {
    private int index;
    private String timestamp;
    private List<Transaction> transactions;
    private String previousHash;
    private String hash;
    private int nonce;

    public Block(int index, List<Transaction> transactions, String previousHash) {
        this.index = index;
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.timestamp = new Date().toString();
        this.hash = calculateHash();
    }

    public int getIndex() {
        return index;
    }

    public String calculateHash() {
        try {
            String dataToHash = index + timestamp + transactions.toString() + previousHash + nonce;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(dataToHash.getBytes("UTF-8"));
            StringBuilder buffer = new StringBuilder();
            for (byte b : hashBytes) {
                buffer.append(String.format("%02x", b));
            }
            return buffer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined! Hash: " + hash);
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void displayBlockDetails() {
        System.out.println("Block Index: " + index);
        System.out.println("Timestamp: " + timestamp);
        System.out.println("Previous Hash: " + previousHash);
        System.out.println("Hash: " + hash);
        System.out.println("Transactions: ");
        for (Transaction t : transactions) {
            t.displayTransaction();
        }
    }
}
