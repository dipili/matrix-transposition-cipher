package com.github.diplombmstu.matrix_trsansposition_cipher;

import com.github.diplombmstu.matrix_trsansposition_cipher.cipher.impl.MatrixKeys;
import com.github.diplombmstu.matrix_trsansposition_cipher.cipher.impl.MatrixTranspositionDataCipher;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        RandomAccessFile inputFile = new RandomAccessFile("samples/input.txt", "r");
        FileChannel inChannel = inputFile.getChannel();

        RandomAccessFile outputFile = new RandomAccessFile("samples/encrypted.txt", "rw");
        FileChannel outChannel = outputFile.getChannel();

        MatrixTranspositionDataCipher dataCipher = new MatrixTranspositionDataCipher();
        dataCipher.encrypt(inChannel, outChannel, MatrixKeys.loadKeyFromFile("samples/key.json"));


        RandomAccessFile decryptedOutputFile = new RandomAccessFile("samples/decrypted.txt", "rw");
        FileChannel decryptedOutChannel = decryptedOutputFile.getChannel();

        outChannel.position(0);
        dataCipher.decrypt(outChannel, decryptedOutChannel, MatrixKeys.loadKeyFromFile("samples/key.json"));
    }
}
