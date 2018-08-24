# Cherry Edit 🍒

## Description

Coding exercise implementing a collaborative text editor based on replicated growable arrays (RGA) [1]. An RGA is an ordered sequence conflict-free replicated data type (CRDT). The implementation is based on the explanations given on a [conference talk](https://www.youtube.com/watch?v=yCcWpzY8dIA) about the work on nested CRDTs, specifically a conflict-free replicated JSON data type [2]. 

## Usage

1. Build the project using `sbt stage`. You can use `sbt dist` to generate a ZIP application bundle.
1. Start the server. The log will show the server address, e.g. `akka.tcp://cherry-edit@127.0.0.1:2552`
   ```
   server/target/universal/stage/bin/cherry-edit-server
   ```
1. Start the client, passing the server address as a command line argument.
   ```
   client/target/universal/stage/bin/cherry-edit-client \
     akka.tcp://cherry-edit@127.0.0.1:2552
   ```

## Configuration

Please refer to the `reference.conf` files in the client and server modules for the available configuration options. 

## Known Issues

- We can't deal with messages arriving out of order, yet
- We are not retrying failed messages and might go out of sync 

## References

[1] Roh, H.G., Jeon, M., Kim, J.S. and Lee, J., 2011. Replicated abstract data types: Building blocks for collaborative applications. Journal of Parallel and Distributed Computing, 71(3), pp.354-368.

[2] Kleppmann, M. and Beresford, A.R., 2017. A conflict-free replicated JSON datatype. IEEE Transactions on Parallel and Distributed Systems, 28(10), pp.2733-2746.