package TestArea;

import java.util.*;

class ShannonFano {

    private Map<String, String> codewords;
    private Map<String, Integer> frequencies;
    private List<Double> probList;

    private List<String> binaryList;
    public ShannonFano() {
        codewords = new HashMap<>();
        frequencies = new HashMap<>();
        probList = new ArrayList<>();
        binaryList = new ArrayList<>();
    }

    public void compress(String message) {
        for (int i = 0; i < message.length(); i++) {
            String symbol = String.valueOf(message.charAt(i));
            frequencies.put(symbol, frequencies.getOrDefault(symbol, 0) + 1);
        }

        List<String> symbols = new ArrayList<>(frequencies.keySet());
        symbols.sort((a, b) -> frequencies.get(b) - frequencies.get(a));

        generateCodewords(symbols, 0, symbols.size() - 1, "");

        StringBuilder compressedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            String symbol = String.valueOf(message.charAt(i));
            compressedMessage.append(codewords.get(symbol));
        }
        System.out.println("Ma hoa Shannon-Fano: " + compressedMessage.toString());
        calAlgorithm(message);
    }

    private void generateCodewords(List<String> symbols, int start, int end, String codeword) {
        if (start == end) {
            codewords.put(symbols.get(start), codeword);
            return;
        }

        int mid = findMidpoint(symbols, start, end);
        for (int i = start; i <= end; i++) {
            if (i <= mid) {
                codewords.put(symbols.get(i), codeword + "0");
            } else {
                codewords.put(symbols.get(i), codeword + "1");
            }
        }


        generateCodewords(symbols, start, mid, codeword + "0");
        generateCodewords(symbols, mid + 1, end, codeword + "1");
    }

    private int findMidpoint(List<String> symbols, int start, int end) {
        int totalFrequency = 0;
        for (int i = start; i <= end; i++) {
            totalFrequency += frequencies.get(symbols.get(i));
        }

        int midFrequency = 0;
        int mid = start;
        for (int i = start; i <= end; i++) {
            midFrequency += frequencies.get(symbols.get(i));
            if (midFrequency >= totalFrequency / 2) {
                mid = i;
                break;
            }
        }

        return mid;
    }
    public static double log2(double x) {
        return Math.log(x) / Math.log(2);
    }
    private double calAlgorithm(String input) {
        double entropy = 0.0;
        double entropymu = 0.0;
        double counter = 0.0;
        for (Map.Entry<String, Integer> entry : frequencies.entrySet()) {
            int frequency = entry.getValue();
            double probability = (double) frequency / input.length();
            entropy += probability * log2(probability);
            probList.add(probability);
        }
        for (Map.Entry<String, String> entry2 : codewords.entrySet()) {
            String binaryValue = entry2.getValue();
            binaryList.add(binaryValue);
        }
        for (int i = 0; i < probList.size() && i < binaryList.size(); i++) {
            entropymu += probList.get(i) * binaryList.get(i).length();
        }
//        System.out.println(Arrays.asList(probList));
//        System.out.println(Arrays.asList(binaryList));
        double HieuSatMaHoa = entropy*-1 / entropymu;
        double duthua = 1 - HieuSatMaHoa;
        System.out.println("Hieu suat ma hoa: " + HieuSatMaHoa);
        System.out.println("Du thua: " + duthua);
        //System.out.println(entropy*-1);
        return entropy;

    }

}

