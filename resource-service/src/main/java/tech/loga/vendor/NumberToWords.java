package tech.loga.vendor;

import java.text.DecimalFormat;

public class NumberToWords
{
    private static String[] dizaineNames;
    private static String[] uniteNames1;
    private static String[] uniteNames2;
    
    private NumberToWords() {
    }
    
    private static String convertZeroToHundred(int number) {
        int laDizaine = number / 10;
        int lUnite = number % 10;
        String resultat = "";
        switch (laDizaine) {
            case 1:
            case 7:
            case 9: {
                lUnite += 10;
                break;
            }
        }
        String laLiaison = "";
        if (laDizaine > 1) {
            laLiaison = "-";
        }
        switch (lUnite) {
            case 0: {
                laLiaison = "";
                break;
            }
            case 1: {
                if (laDizaine == 8) {
                    laLiaison = "-";
                    break;
                }
                laLiaison = " et ";
                break;
            }
            case 11: {
                if (laDizaine == 7) {
                    laLiaison = " et ";
                    break;
                }
                break;
            }
        }
        switch (laDizaine) {
            case 0: {
                resultat = NumberToWords.uniteNames1[lUnite];
                break;
            }
            case 8: {
                if (lUnite == 0) {
                    resultat = NumberToWords.dizaineNames[laDizaine];
                    break;
                }
                resultat = NumberToWords.dizaineNames[laDizaine] + laLiaison + NumberToWords.uniteNames1[lUnite];
                break;
            }
            default: {
                resultat = NumberToWords.dizaineNames[laDizaine] + laLiaison + NumberToWords.uniteNames1[lUnite];
                break;
            }
        }
        return resultat;
    }
    
    private static String convertLessThanOneThousand(int number) {
        int lesCentaines = number / 100;
        int leReste = number % 100;
        String sReste = convertZeroToHundred(leReste);
        String resultat = null;
        switch (lesCentaines) {
            case 0: {
                resultat = sReste;
                break;
            }
            case 1: {
                if (leReste > 0) {
                    resultat = "cent " + sReste;
                    break;
                }
                resultat = "cent";
                break;
            }
            default: {
                if (leReste > 0) {
                    resultat = NumberToWords.uniteNames2[lesCentaines] + " cent " + sReste;
                    break;
                }
                resultat = NumberToWords.uniteNames2[lesCentaines] + " cents";
                break;
            }
        }
        return resultat;
    }
    
    public static String convert(long number) {
        if (number == 0) {
            return "zéro";
        }
        String snumber = Long.toString(number);
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        snumber = df.format(number);
        int lesMilliards = Integer.parseInt(snumber.substring(0, 3));
        int lesMillions = Integer.parseInt(snumber.substring(3, 6));
        int lesCentMille = Integer.parseInt(snumber.substring(6, 9));
        int lesMille = Integer.parseInt(snumber.substring(9, 12));
        String tradMilliards = null;
        switch (lesMilliards) {
            case 0: {
                tradMilliards = "";
                break;
            }
            case 1: {
                tradMilliards = convertLessThanOneThousand(lesMilliards) + " milliard ";
                break;
            }
            default: {
                tradMilliards = convertLessThanOneThousand(lesMilliards) + " milliards ";
                break;
            }
        }
        String resultat = tradMilliards;
        String tradMillions = null;
        switch (lesMillions) {
            case 0: {
                tradMillions = "";
                break;
            }
            case 1: {
                tradMillions = convertLessThanOneThousand(lesMillions) + " million ";
                break;
            }
            default: {
                tradMillions = convertLessThanOneThousand(lesMillions) + " millions ";
                break;
            }
        }
        resultat += tradMillions;
        String tradCentMille = null;
        switch (lesCentMille) {
            case 0: {
                tradCentMille = "";
                break;
            }
            case 1: {
                tradCentMille = "mille ";
                break;
            }
            default: {
                tradCentMille = convertLessThanOneThousand(lesCentMille) + " mille ";
                break;
            }
        }
        resultat += tradCentMille;
        String tradMille = convertLessThanOneThousand(lesMille);
        resultat += tradMille;
        return resultat;
    }
    
    static {
        dizaineNames = new String[] { "", "", "vingt", "trente", "quarante", "cinquante", "soixante", "soixante", "quatre-vingt", "quatre-vingt" };
        uniteNames1 = new String[] { "", "un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf", "dix", "onze", "douze", "treize", "quatorze", "quinze", "seize", "dix-sept", "dix-huit", "dix-neuf" };
        uniteNames2 = new String[] { "", "", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf", "dix" };
    }
}
