## Environment

### Java

$ java --version

openjdk 11.0.16 2022-07-19
OpenJDK Runtime Environment (build 11.0.16+8-post-Ubuntu-0ubuntu122.04)
OpenJDK 64-Bit Server VM (build 11.0.16+8-post-Ubuntu-0ubuntu122.04, mixed mode, sharing)

### Ganache

ganache-2.5.4-linux-x86_64

## Usage

0. Setup ganache env;
1. Create contract;
2. Run solc: $ solc src/main/solidity/<CONTRACT_NAME>.sol --bin --abi --optimize -o org/web3j/generated/contracts/
3. Run web3j $ web3j generate solidity --abiFile=org/web3j/generated/contracts/<CONTRACT_NAME>.abi -o src/main/java/ -p <PACKAGE_NAME>
4. Use deployed contract from .java files;
5. Run.
