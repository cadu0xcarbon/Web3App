package org.web3j;

import kduzera.Kdu1155;
import kduzera.KduToken;
import kduzera.TobiNFT;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Web3App {

    private final static String PRIVATE_KEY = "c166daf5e0211a8e36c58f28efa0972f86bf1e1e70151558e19c4a8554b15f2b"; // ganache private key #1
    private final static String OWNER = "0x2a11737272Cd598664d9aa60D93F91A356223B2A"; // ganache address #1
    private final static String RECIPIENT = "0xFdFf28CE5a73623831351B5861A95f85d65D7603"; // ganache address #X
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L); // from ganache
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L); // from ganache

    public static void main(String[] args) throws Exception {

        Web3j web3j = Web3j.build(new HttpService()); // defaults to port 8545; for alchemy connection = "https://eth-goerli.alchemyapi.io/v2/<ALCHEMY_KEY>"

        Credentials credentials = Credentials.create(PRIVATE_KEY);

        useERC20Contract(web3j, credentials);
        useERC721Contract(web3j, credentials);
        useERC1155Contract(web3j, credentials);
    }

    private static void useERC20Contract(Web3j web3j, Credentials credentials) throws Exception {
        System.out.println("Deploying ERC20 contract...");
        String kduTokenContractAddress = deployERC20Contract(web3j, credentials);
        KduToken kduToken = loadERC20Contract(kduTokenContractAddress, web3j, credentials);
        System.out.println("initial owner account balance: " + kduToken.balanceOf(OWNER).sendAsync().get());
        System.out.println("initial recipient account balance: " + kduToken.balanceOf(RECIPIENT).sendAsync().get());
        System.out.println("sending 1K to account #2: " + kduToken.transfer(RECIPIENT, BigInteger.valueOf(1000)).sendAsync().get());
        System.out.println("recipient account balance after transfer: " + kduToken.balanceOf(RECIPIENT).sendAsync().get());
    }

    private static void useERC721Contract(Web3j web3j, Credentials credentials) throws Exception {
        System.out.println("Deploying ERC721 contract...");
        String tobiNFTContractAddress = deployERC721Contract(web3j, credentials);
        TobiNFT tobiNFT = loadERC721Contract(tobiNFTContractAddress, web3j, credentials);
        System.out.println("mint nft to owner: " + tobiNFT.safeMint(OWNER).sendAsync().get());
        System.out.println("owner account balance: " + tobiNFT.balanceOf(OWNER).sendAsync().get());
        System.out.println("nft owner: " + tobiNFT.owner().sendAsync().get());
        System.out.println("transfer ownership: " + tobiNFT.transferOwnership(RECIPIENT).sendAsync().get());
        System.out.println("nft new owner: " + tobiNFT.owner().sendAsync().get());
    }

    private static void useERC1155Contract(Web3j web3j, Credentials credentials) throws Exception {
        System.out.println("Deploying ERC1155 contract...");
        String kdu1155ContractAddress = deployERC1155Contract(web3j, credentials);
        Kdu1155 kdu1155 = loadERC1155Contract(kdu1155ContractAddress, web3j, credentials);
        System.out.println("mint to owner: " + kdu1155.mint(OWNER, BigInteger.ONE, BigInteger.valueOf(1000), new byte[]{Byte.MAX_VALUE}).sendAsync().get());
        System.out.println("#1 owner: " + kdu1155.balanceOf(RECIPIENT, BigInteger.ONE).sendAsync().get());
    }

    private static String deployERC20Contract(Web3j web3j, Credentials credentials) throws Exception {
        return KduToken.deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT, BigInteger.valueOf(1000000))
                .sendAsync().get().getContractAddress();
    }

    private static KduToken loadERC20Contract(String contractAddress, Web3j web3j, Credentials credentials) {
        return KduToken.load(contractAddress, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    private static String deployERC721Contract(Web3j web3j, Credentials credentials) throws Exception {
        return TobiNFT.deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT)
                .sendAsync().get().getContractAddress();
    }

    private static TobiNFT loadERC721Contract(String contractAddress, Web3j web3j, Credentials credentials) {
        return TobiNFT.load(contractAddress, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    private static String deployERC1155Contract(Web3j web3j, Credentials credentials) throws Exception {
        return Kdu1155.deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT)
                .sendAsync().get().getContractAddress();
    }

    private static Kdu1155 loadERC1155Contract(String contractAddress, Web3j web3j, Credentials credentials) {
        return Kdu1155.load(contractAddress, web3j, credentials, GAS_PRICE, GAS_LIMIT);
    }

    private static void transferEthereum(Web3j web3j, Credentials credentials) throws Exception {
        TransactionReceipt transactionReceipt = Transfer.sendFunds(
                web3j, credentials, RECIPIENT,
                BigDecimal.valueOf(1), Convert.Unit.ETHER).sendAsync().get();
        System.out.println("Transaction: " + transactionReceipt.getTransactionHash());
    }
}

