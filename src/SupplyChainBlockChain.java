import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SupplyChainBlockChain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Blockchain blockchain = new Blockchain(4); 

      
        boolean continueAdding = true;
        while (continueAdding) {
           
            System.out.print("Enter Sender ID: ");
            String senderId = scanner.nextLine();
            System.out.print("Enter Sender Name: ");
            String senderName = scanner.nextLine();
            System.out.print("Enter Sender Role: ");
            String senderRole = scanner.nextLine();
            Participant sender = new Participant(senderId, senderName, senderRole);

            System.out.print("Enter Receiver ID: ");
            String receiverId = scanner.nextLine();
            System.out.print("Enter Receiver Name: ");
            String receiverName = scanner.nextLine();
            System.out.print("Enter Receiver Role: ");
            String receiverRole = scanner.nextLine();
            Participant receiver = new Participant(receiverId, receiverName, receiverRole);

            
            ParticipantDatabase.saveParticipant(sender);
            ParticipantDatabase.saveParticipant(receiver);

           
            System.out.print("Enter Product ID: ");
            String productId = scanner.nextLine();
            System.out.print("Enter Product Name: ");
            String productName = scanner.nextLine();
            System.out.print("Enter Product Price: ");
            double productPrice = scanner.nextDouble();
            scanner.nextLine();  
            Product product = new Product(productId, productName, productPrice);

           
            ProductDatabase.saveProduct(product);

         
            System.out.print("Enter Transaction ID: ");
            String transactionId = scanner.nextLine();

         
            Transaction transaction = new Transaction(transactionId, sender, receiver, product);
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);

          
            TransactionDatabase.saveTransaction(transaction);

           
            Block block = new Block(blockchain.getLatestBlock().getIndex() + 1, transactions, blockchain.getLatestBlock().getHash());
            
          
            blockchain.addBlock(block);

           
            blockchain.displayBlockchain();


            System.out.print("Do you want to add another transaction? (yes/no): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("no")) {
                continueAdding = false;
            }

         
            System.out.print("Would you like to view a specific block? (yes/no): ");
            String viewBlockResponse = scanner.nextLine();
            if (viewBlockResponse.equalsIgnoreCase("yes")) {
                System.out.print("Enter block index to view: ");
                int blockIndex = scanner.nextInt();
                Block specificBlock = blockchain.getBlockByIndex(blockIndex);
                if (specificBlock != null) {
                    System.out.println("Specific Block: " + specificBlock);
                }

              
                System.out.print("Would you like to search by transaction ID? (yes/no): ");
                scanner.nextLine();
                String searchTransactionResponse = scanner.nextLine();
                if (searchTransactionResponse.equalsIgnoreCase("yes")) {
                    System.out.print("Enter transaction ID to search: ");
                    String transactionIdSearch = scanner.nextLine();
                    Block blockWithTransaction = blockchain.getBlockByTransactionId(transactionIdSearch);
                    if (blockWithTransaction != null) {
                        System.out.println("Block with Transaction ID: " + blockWithTransaction);
                    }
                }
            }
        }

        
        scanner.close();
    }
}
