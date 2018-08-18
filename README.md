# Cherry Edit 🍒

## Description

Code Kata implementing a collaborative text editor based on replicated growable arrays (RGA) [1]. An RGA is an ordered sequence conflict-free replicated data type (CRDT). The implementation is based on the explanations given on a [conference talk](https://www.youtube.com/watch?v=yCcWpzY8dIA) about the work on nested CRDTs, specifically a conflict-free replicated JSON data type [2]. 

## Known Issues

- We can't deal with messages arriving out of order, yet

## References

[1] Roh, H.G., Jeon, M., Kim, J.S. and Lee, J., 2011. Replicated abstract data types: Building blocks for collaborative applications. Journal of Parallel and Distributed Computing, 71(3), pp.354-368.

[2] Kleppmann, M. and Beresford, A.R., 2017. A conflict-free replicated JSON datatype. IEEE Transactions on Parallel and Distributed Systems, 28(10), pp.2733-2746.