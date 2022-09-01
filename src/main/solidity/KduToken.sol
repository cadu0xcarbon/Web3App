// contracts/GLDToken.sol
// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "build/node_modules/@openzeppelin/contracts/token/ERC20/ERC20.sol";

contract KduToken is ERC20 {
    constructor(uint256 _supply) ERC20("Kdu", "KDU") {
        _mint(msg.sender, _supply * (10**decimals()));
    }
}
