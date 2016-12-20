package com.github.diplombmstu.matrix_trsansposition_cipher.cipher;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * A cipher for any type & size data cipher
 */
public interface DataCipher<T>
{
    void encrypt(ReadableByteChannel readableByteChannel, WritableByteChannel writableByteChannel, T key)
            throws IOException;

    void decrypt(ReadableByteChannel readableByteChannel, WritableByteChannel writableByteChannel, T key)
            throws IOException;
}
