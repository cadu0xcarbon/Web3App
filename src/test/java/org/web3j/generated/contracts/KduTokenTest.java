package org.web3j.generated.contracts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.web3j.EVMTest;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;

@EVMTest
class KduTokenTest {
    private static KduToken kduToken;

    @BeforeAll
    static void deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) throws Exception {
        kduToken = KduToken.deploy(web3j, transactionManager, contractGasProvider, BigInteger.valueOf(100000000)).send();
        System.out.println("token: " + kduToken.getContractAddress());
    }

    @Test
    public void greeting() throws Exception {
        BigInteger balance = kduToken.balanceOf("0xE6E05eEbE59814B644A5581D5B14889Aa276E3e4").send();
        Assertions.assertEquals(BigInteger.ZERO, balance);
    }
}
