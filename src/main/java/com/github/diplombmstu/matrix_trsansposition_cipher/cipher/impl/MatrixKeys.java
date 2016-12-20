package com.github.diplombmstu.matrix_trsansposition_cipher.cipher.impl;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Util methods for MatrixKey class
 */
public class MatrixKeys
{
    public static MatrixKey loadKeyFromFile(String fileName) throws IOException
    {
        byte[] bytes = Files.readAllBytes(Paths.get(fileName));
        return new Gson().fromJson(new String(bytes), MatrixKey.class);
    }
}
