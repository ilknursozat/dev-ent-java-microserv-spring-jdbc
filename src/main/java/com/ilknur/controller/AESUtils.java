package com.ilknur.controller;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Scanner;

@Component("aesUtils")
public class AESUtils {
    private final static String AES = "AES";
    private final static String END_OF_FILE = "\\Z";
    private final static String DEFAULT_KEY = "ED405BB8B8C6971E7C7BEEA250D8C30057C64594AAAAAABB";

    /**
     * Encrypt a value and generate a keyfile.
     * If the keyfile is not found, then a new one will be created.
     */
    String encrypt(String value, String keyFilePath) {
        byte[] encrypted = "Empty".getBytes();
        try {
            File keyFile = new File(keyFilePath);
            if (!keyFile.exists()) {
                KeyGenerator keyGen = KeyGenerator.getInstance(AES);
                keyGen.init(128);
                SecretKey sk = keyGen.generateKey();
                FileWriter fw = new FileWriter(keyFile);
                fw.write(byteArrayToHexString(sk.getEncoded()));
                fw.flush();
                fw.close();
            }
            SecretKeySpec sks = getSecretKeySpec(keyFile);
            final Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, sks);
            encrypted = cipher.doFinal(value.getBytes());
        } catch (GeneralSecurityException | IOException exc) {
            exc.printStackTrace();
        }
        return byteArrayToHexString(encrypted);
    }

    /**
     * Decrypt a value.
     */
    String decrypt(String message, String keyFilePath) {
        byte[] decrypted = "Emtpy".getBytes();
        try {
            File keyFile = new File(keyFilePath);
            SecretKeySpec sks = getSecretKeySpec(keyFile);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, sks);
            decrypted = cipher.doFinal(hexStringToByteArray(message));
        } catch (GeneralSecurityException | IOException exc) {
            exc.printStackTrace();
        }
        return new String(decrypted);
    }

    private static SecretKeySpec getSecretKeySpec(File keyFile)
            throws IOException {
        byte[] key = readKeyFile(keyFile);
        return new SecretKeySpec(key, AES);
    }

    private static byte[] readKeyFile(File keyFile)
            throws FileNotFoundException {
        Scanner scanner = new Scanner(keyFile).useDelimiter(END_OF_FILE);
        String keyTextStr = scanner.next();
        while ((keyTextStr.length() < 16) && (scanner.hasNext())) {
            keyTextStr = scanner.next();
        }
        if (keyTextStr.length() < 16) {
            keyTextStr = DEFAULT_KEY;
        }
        scanner.close();

        // Convert the keyFile string (or the default key string) to an array of bytes
        byte[] keyHexByteArray = hexStringToByteArray(toHex(keyTextStr));
        // Trim to 32 bytes for AES key length of 16, 24, or max of 32 bytes
        if (keyHexByteArray.length > 32 ) {
            byte[] smallerHexByteArray = new byte[32];
            System.arraycopy(keyHexByteArray, 0, smallerHexByteArray, 0, 32);
            keyHexByteArray = smallerHexByteArray;
        }

        return keyHexByteArray;
    }

    private static String toHex(String arg) {
        return String.format("%040x", new BigInteger(1, arg.getBytes()));
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (byte value : b) {
            int v = value & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    private static byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }

    public static void main(String... args) {
        // Add your password in between the quotes, run this main, copy the encrypted password, then delete.
        String origStr = args.length == 0 ? "<Add password here, then remove when done>" : args[1];
        if (!(origStr.equals(""))) {
            com.ilknur.controller.AESUtils aesUtils = new com.ilknur.controller.AESUtils();
            System.err.println(aesUtils.encrypt(origStr,"./keyFile.key"));
        }
    }
}
