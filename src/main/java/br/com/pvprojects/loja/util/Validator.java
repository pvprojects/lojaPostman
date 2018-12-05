package br.com.pvprojects.loja.util;

import java.util.InputMismatchException;

public class Validator {

    public static String clean(String string) {
        return string.trim().replace(" ", "").replace(",", "")
                .replace(".", "").replace("-", "");
    }

    public static boolean verifyIfCpfIsValid(final String CPF) {
        if (simpleCheckNotValid(CPF))
            return (false);

        char dig10;
        char dig11;
        int sm;
        int i;
        int r;
        int num;
        int peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = CPF.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48);
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = CPF.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            dig11 = ((r == 10) || (r == 11)) ? '0' : (char) (r + 48);
            return (dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10));

        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    private static boolean simpleCheckNotValid(String cpf) {
        return (cpf.length() != 11) ||
                "00000000000".equals(cpf) || "11111111111".equals(cpf) ||
                "22222222222".equals(cpf) || "33333333333".equals(cpf) ||
                "44444444444".equals(cpf) || "55555555555".equals(cpf) ||
                "66666666666".equals(cpf) || "77777777777".equals(cpf) ||
                "88888888888".equals(cpf) || "99999999999".equals(cpf);
    }
}