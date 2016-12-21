package com.github.diplombmstu.matrix_trsansposition_cipher.cipher.impl;

import com.github.diplombmstu.matrix_trsansposition_cipher.cipher.BlockCipher;

import java.util.ArrayList;
import java.util.List;

/**
 * Block cipher impl for matrix transposition ciphering algorithm
 */
public class MatrixTranspositionBlockCipher implements BlockCipher<MatrixKey>
{
    @Override
    public byte[] encryptBlock(byte[] block, MatrixKey key)
    {
        List<Byte>[][] matrix = createCipherMatrix(block, key, Direction.Default);
        return shuffleBlock(block, key, matrix, Direction.Default);
    }

    @Override
    public byte[] decryptBlock(byte[] block, MatrixKey key)
    {
        List<Byte>[][] matrix = createCipherMatrix(block, key, Direction.Reverse);
        return shuffleBlock(block, key, matrix, Direction.Reverse);
    }

    private byte[] shuffleBlock(byte[] block, MatrixKey key, List<Byte>[][] matrix, Direction direction)
    {
        byte[] result = new byte[block.length];

        int it = 0;

        int iEnds = direction == Direction.Default ? key.getS() : key.getM();
        int jEnds = direction == Direction.Default ? key.getM() : key.getS();

        for (int i = 0; i < iEnds; ++i)
        {
            for (int j = 0; j < jEnds; ++j)
            {
                for (int k = 0; k < key.getP(); ++k)
                {
                    if (direction == Direction.Default)
                        result[it] = matrix[j][i].get(k);
                    else
                        result[it] = matrix[i][j].get(k);

                    ++it;
                }
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private List<Byte>[][] createCipherMatrix(byte[] block, MatrixKey key, Direction direction)
    {
        List<Byte>[][] matrix = new List[key.getM()][key.getS()];

        int it = 0;

        int iEnds = direction == Direction.Default ? key.getM() : key.getS();
        int jEnds = direction == Direction.Default ? key.getS() : key.getM();

        for (int i = 0; i < iEnds; ++i)
        {
            for (int j = 0; j < jEnds; ++j)
            {
                ArrayList<Byte> bytes = new ArrayList<>();
                for (int k = 0; k < key.getP(); ++k)
                {
                    bytes.add(block[it]);
                    ++it;
                }

                if (direction == Direction.Default)
                    matrix[i][j] = bytes;
                else
                    matrix[j][i] = bytes;
            }
        }

        return matrix;
    }
}
