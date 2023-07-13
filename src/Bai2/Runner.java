package Bai2;

import java.util.Scanner;
//Bài 2:
//        a)Nhập vào một chuỗi ký tự không dấu, có chiều dài bất kỳ, không phân biệt chữ hoa, chữ thường.
//        b)Mã hóa Huffman cho chuỗi trên.
//        c)Mã hóa Shannon-Fano cho chuỗi trên, tính hiệu suất mã hóa, và tính dư thừa.
public class Runner {
    public static void main(String[] args) {
        //a)
        System.out.println("Nhập chuỗi ký tự");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().toLowerCase();
        System.out.println("a)");
        System.out.println(input);
        //System.out.println(input);
        HuffmanCode hm = new HuffmanCode();
        ShannonFano sf = new ShannonFano();
        System.out.println("b)");
        hm.createHuffmanTree(input);
        System.out.println("c)");
        sf.compress(input);
    }
}
