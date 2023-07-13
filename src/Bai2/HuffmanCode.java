package Bai2;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
class Node
{
    Character ch;

    Integer freq;

    Node left = null;
    Node right = null;

    Node(Character ch, Integer freq)
    {
        this.ch = ch;
        this.freq = freq;
    }

    public Node(Character ch, Integer freq, Node left, Node right)
    {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}

public class HuffmanCode
{

    public static void createHuffmanTree(String text)
    {

        if (text == null || text.length() == 0)
        {
            return;
        }

        Map<Character, Integer> freq = new HashMap<>();

        for (char c: text.toCharArray())
        {

            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.freq));
        for (var entry: freq.entrySet())
        {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }
        while (pq.size() != 1)
        {
            Node left = pq.poll();
            Node right = pq.poll();

            int sum = left.freq + right.freq;
            pq.add(new Node(null, sum, left, right));
        }
        Node root = pq.peek();
        Map<Character, String> huffmanCode = new HashMap<>();
        encodeData(root, "", huffmanCode);
        StringBuilder sb = new StringBuilder();
        for (char c: text.toCharArray())
        {
            sb.append(huffmanCode.get(c));
        }
        System.out.println("Ma hoa Huffman: " + sb);
    }

    public static void encodeData(Node root, String str, Map<Character, String> huffmanCode)
    {
        if (root == null)
        {
            return;
        }
        if (isLeaf(root))
        {
            huffmanCode.put(root.ch, str.length() > 0 ? str : "1");
        }
        encodeData(root.left, str + '0', huffmanCode);
        encodeData(root.right, str + '1', huffmanCode);
    }

    public static boolean isLeaf(Node root)
    {
        return root.left == null && root.right == null;
    }
    //driver code
//    public static void main(String args[])
//    {
//        String text = "hellooo";
//        createHuffmanTree(text);
//    }
}