// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;

import "build/node_modules/@openzeppelin/contracts/token/ERC721/ERC721.sol";
import "build/node_modules/@openzeppelin/contracts/access/Ownable.sol";
import "build/node_modules/@openzeppelin/contracts/utils/Counters.sol";

contract TobiNFT is ERC721, Ownable {
    using Counters for Counters.Counter;

    Counters.Counter private _tokenIdCounter;

    constructor() ERC721("Tobi", "TBI") {}

    function _baseURI() internal pure override returns (string memory) {
        return
        "https://ipfs.io/ipfs/QmbGC4aSYTRqszFTASV9Tzh9LX8MTtnD2oH3UvtJ29NPB4";
    }

    function safeMint(address to) public onlyOwner {
        uint256 tokenId = _tokenIdCounter.current();
        _tokenIdCounter.increment();
        _safeMint(to, tokenId);
    }
}
