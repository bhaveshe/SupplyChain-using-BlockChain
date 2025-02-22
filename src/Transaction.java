import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private String transactionId;
    private Participant sender;
    private Participant receiver;
    private Product product;
    private String timestamp;
    private double transactionFee;

    public Transaction(String transactionId, Participant sender, Participant receiver, Product product) {
        this.transactionId = transactionId;
        this.sender = sender;
        this.receiver = receiver;
        this.product = product;
        this.timestamp = getFormattedTimestamp();  
        this.transactionFee = calculateTransactionFee(product.getPrice());
    }

    private double calculateTransactionFee(double price) {
        return price * 0.02; 
    }

    private String getFormattedTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public String getTimestamp() {
        return timestamp; 
    }

    public Participant getSender() {
        return sender;
    }

    public Participant getReceiver() {
        return receiver;
    }

    public Product getProduct() {
        return product;
    }

    public void displayTransaction() {
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Sender: " + sender.getName() + " -> Receiver: " + receiver.getName());
        System.out.println("Product: " + product.getProductName() + ", Fee: " + transactionFee);
    }
}
