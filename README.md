## Environment

### Java

$ java --version

openjdk 11.0.16 2022-07-19
OpenJDK Runtime Environment (build 11.0.16+8-post-Ubuntu-0ubuntu122.04)
OpenJDK 64-Bit Server VM (build 11.0.16+8-post-Ubuntu-0ubuntu122.04, mixed mode, sharing)

### Ganache

ganache-2.5.4-linux-x86_64

## Usage (local)

1. Start ganache;
2. Update PRIVATE_KEY (address #1), OWNER (address #1) and RECIPIENT (any other address) variables from ganache env;
3. Run solc for each contract (found in src/main/solidity): $ solc src/main/solidity/<CONTRACT_NAME>.sol --bin --abi -o org/web3j/generated/contracts/<CONTRACT_FOLDER>/
4. Run web3j for each contract (found in org/web3j/generated/contracts/<CONTRACT_FOLDER>): $ web3j generate solidity --abiFile=org/web3j/generated/contracts/<CONTRACT_FOLDER>/<CONTRACT_NAME>.abi --binFile=org/web3j/generated/contracts/<CONTRACT_FOLDER>/<CONTRACT_NAME>.bin -o src/main/java/ -p <PACKAGE_NAME>
5. Run Web3App.java;

---

- solc src/main/solidity/KduToken.sol --bin --abi -o org/web3j/generated/contracts/KduToken/
- solc src/main/solidity/TobiNFT.sol --bin --abi -o org/web3j/generated/contracts/TobiNFT/
- solc src/main/solidity/Kdu1155.sol --bin --abi -o org/web3j/generated/contracts/Kdu1155/
- web3j generate solidity --abiFile=org/web3j/generated/contracts/KduToken/KduToken.abi --binFile=org/web3j/generated/contracts/KduToken/KduToken.bin -o src/main/java/ -p kduzera
- web3j generate solidity --abiFile=org/web3j/generated/contracts/TobiNFT/TobiNFT.abi --binFile=org/web3j/generated/contracts/TobiNFT/TobiNFT.bin -o src/main/java/ -p kduzera
- web3j generate solidity --abiFile=org/web3j/generated/contracts/Kdu1155/Kdu1155.abi --binFile=org/web3j/generated/contracts/Kdu1155/Kdu1155.bin -o src/main/java/ -p kduzera
