package com.mendes.caixa2025.service;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class VerificadorSenha {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String hashSenha(String senhas) {
        return encoder.encode(senhas);
    }

    public static boolean validarSenha(String senha, String hash) {
        return encoder.matches(senha, hash);
    }

    public static void main(String[] args) {
        List<String> senhas = Arrays.asList("yracema@123", "yracema@321");
            for (String senha: senhas) {
                String hash = hashSenha(senha);
                System.out.println("Hash da senha: " + hash);
                System.out.println("Senha válida? " + validarSenha("yracema@123" , hash));
            }
        }

}
