package com.github.diplombmstu.matrix_trsansposition_cipher.cipher.impl;

import com.github.diplombmstu.matrix_trsansposition_cipher.cipher.Cipher;
import com.github.diplombmstu.matrix_trsansposition_cipher.cipher.DataCipher;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * TODO add comment
 */
public class MatrixTranspositionDataCipher implements DataCipher<MatrixKey>
{
    @Override
    public void encrypt(ReadableByteChannel readableByteChannel, WritableByteChannel writableByteChannel, MatrixKey key)
            throws IOException
    {
        Cipher<MatrixKey> cipher = new MatrixTranspositionCipher();

        int blockSize = key.getM() * key.getS() * key.getP();
        ByteBuffer readBuffer = ByteBuffer.allocate(blockSize);
        ByteBuffer writeBuffer = ByteBuffer.allocate(blockSize);

        for (int bytesRead = readableByteChannel.read(readBuffer);
             bytesRead != -1;
             bytesRead = readableByteChannel.read(readBuffer))
        {
            if (bytesRead < blockSize)
                readBuffer.put(new byte[blockSize - bytesRead]);

            byte[] block = readBuffer.array();
            readBuffer.flip();
            byte[] encryptedBlock = cipher.encryptBlock(block, key);

            writeBuffer.put(encryptedBlock);
            writeBuffer.flip();
            writableByteChannel.write(writeBuffer);
            writeBuffer.compact();
        }
    }

    @Override
    public void decrypt(ReadableByteChannel readableByteChannel, WritableByteChannel writableByteChannel, MatrixKey key)
            throws IOException
    {
        Cipher<MatrixKey> cipher = new MatrixTranspositionCipher();

        int blockSize = key.getM() * key.getS() * key.getP();
        ByteBuffer readBuffer = ByteBuffer.allocate(blockSize);
        ByteBuffer writeBuffer = ByteBuffer.allocate(blockSize);

        for (int bytesRead = readableByteChannel.read(readBuffer);
             bytesRead != -1;
             bytesRead = readableByteChannel.read(readBuffer))
        {
            if (bytesRead < blockSize)
                readBuffer.put(new byte[blockSize - bytesRead]);

            byte[] block = readBuffer.array();
            readBuffer.flip();
            byte[] encryptedBlock = cipher.decryptBlock(block, key);

            writeBuffer.put(encryptedBlock);
            writeBuffer.flip();
            writableByteChannel.write(writeBuffer);
            writeBuffer.compact();
        }
    }
}
