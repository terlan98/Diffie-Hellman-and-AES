# INFT3508 – Assignment 1
This java application imitates a message exchange between two parties using Diffie-Hellman Key Exchange and AES-128 Encryption with CBC mode of operation.

## How can I run it?

 1. Run **MessageExchanger.java**
 2. Provide a *prime number* and *generator* value for Diffie-Hellman Key Exchange algorithm. (11 and 2, for example)
 3. You will see some information regarding the key exchange process.
 4. Type a *message* and press enter to send.
 5. You will see the *original message* (plaintext), *ciphertext*, and *decrypted message* followed by the *initial vector* that was used for the CBC mode of encryption.

The initial vector is generated randomly and changes every time the program starts.
