package matrix_trsansposition_cipher.cipher.impl;

import com.github.diplombmstu.matrix_trsansposition_cipher.cipher.impl.MatrixKey;
import com.github.diplombmstu.matrix_trsansposition_cipher.cipher.impl.MatrixTranspositionBlockCipher;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestMatrixTranspositionCipher
{
    @Test
    public void encryptBlockMustReturnValidValue()
    {
        MatrixTranspositionBlockCipher cipher = new MatrixTranspositionBlockCipher();
        MatrixKey key = new MatrixKey();
        key.setM(2);
        key.setS(4);
        key.setP(1);
        byte[] bytes = cipher.encryptBlock(new byte[]{1, 2, 3, 4, 5, 6, 7, 8}, key);

        assertArrayEquals(bytes, new byte[]{1, 5, 2, 6, 3, 7, 4, 8});
    }

    @Test
    public void decryptBlockMustReturnValidValue()
    {
        MatrixTranspositionBlockCipher cipher = new MatrixTranspositionBlockCipher();
        MatrixKey key = new MatrixKey();
        key.setM(2);
        key.setS(4);
        key.setP(1);
        byte[] bytes = cipher.decryptBlock(new byte[]{1, 5, 2, 6, 3, 7, 4, 8}, key);

        assertArrayEquals(bytes, new byte[]{1, 2, 3, 4, 5, 6, 7, 8});
    }
}
