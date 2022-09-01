package org.web3j;

import org.web3j.crypto.Credentials;
import org.web3j.generated.contracts.KduToken;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Web3App {

    private final static String PRIVATE_KEY = "013d14518188b81d19f8116b5c006583bada628984883f70ca9f70ff4677e40e"; // ganache private key #1
    private final static String OWNER = "0xa3E9dCe32E61D4E8e19648F4FA3F86727ec3457B"; // ganache address #1
    private final static String RECIPIENT = "0x898e82D31C21F01bB12049a2E866b3a595D5BE90"; // ganache address #2
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
    private final static String CONTRACT_ADDRESS = "0x13206516c1fec28d75a64526002f1073e71769fe";

    public static void main(String[] args) throws Exception {

        Web3j web3j = Web3j.build(new HttpService()); // defaults to port 8545; for alchemy connection = "https://eth-goerli.alchemyapi.io/v2/<ALCHEMY_KEY>"

        Credentials credentials = Credentials.create(PRIVATE_KEY);

        KduToken kduToken = loadContract(CONTRACT_ADDRESS, web3j, credentials);
        System.out.println("initial owner account balance: " + kduToken.balanceOf(OWNER).sendAsync().get());
        System.out.println("initial recipient account balance: " + kduToken.balanceOf(RECIPIENT).sendAsync().get());
        System.out.println("sending 1K to account #2: " + kduToken.transfer(RECIPIENT, BigInteger.valueOf(1000)).sendAsync().get());
        System.out.println("recipient account balance after transfer: " + kduToken.balanceOf(RECIPIENT).sendAsync().get());
    }

    private static String deployContract(Web3j web3j, Credentials credentials) throws Exception {
        return KduToken.deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT, BigInteger.valueOf(1000000))
                .sendAsync().get().getContractAddress();
    }

    private static KduToken loadContract(String contractAddress, Web3j web3j, Credentials credentials) {
        return KduToken.load(contractAddress, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    private static void transferEthereum(Web3j web3j, Credentials credentials) throws Exception {
        TransactionReceipt transactionReceipt = Transfer.sendFunds(
                web3j, credentials, RECIPIENT,
                BigDecimal.valueOf(1), Convert.Unit.ETHER).sendAsync().get();
        System.out.println("Transaction: " + transactionReceipt.getTransactionHash());
    }
}

