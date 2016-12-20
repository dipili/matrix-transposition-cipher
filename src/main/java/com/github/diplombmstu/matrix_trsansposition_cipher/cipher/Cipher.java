package com.github.diplombmstu.matrix_trsansposition_cipher.cipher;

/**
 * A simple block cipher interface
 */
public interface Cipher<T>
{
    byte[] encryptBlock(byte[] block, T key);

    byte[] decryptBlock(byte[] block, T key);
}
