package com.stone.algorithm.huffmancode;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * @author stone
 * @date 2019/6/24 14:32
 * description 赫夫曼编码
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String contents = "i like like like java do you like a java";
        System.out.println(contents);
        byte[] contentsBytes = contents.getBytes();
        byte[] hufumanCodeBytes = huffmanZip(contentsBytes);
        System.out.println(Arrays.toString(hufumanCodeBytes));
        byte[] bytes = decode(huffmanCodes, hufumanCodeBytes);
        System.out.println(new String(bytes));

//        String srcFile = "D:\\temp\\p15.jpg";
//        String destFile = "D:\\temp\\p15.zip";
//        zipFile(srcFile, destFile);

//        String srcFile = "D:\\temp\\p15.zip";
//        String destFile = "D:\\temp\\p152.jpg";
//        unZipFile(srcFile, destFile);
    }

    /**
     * 赫夫曼编码
     *
     * @param bytes
     * @return
     */
    public static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        // 创建赫夫曼树
        Node node = createHuffmanTree(nodes);
        // 根据赫夫曼树得到赫夫曼编码
        getCodes(node);
        // 根据赫夫曼编码，得到压缩后的赫夫曼编码字节数组
        return zip(bytes, huffmanCodes);
    }

    /**
     * 将准备构建赫夫曼树的Node节点放到List
     *
     * @param bytes
     * @return
     */
    public static List<Node> getNodes(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();

        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            Integer count = map.get(b);
            if (count == null) {
                map.put(b, 1);
            } else {
                map.put(b, count + 1);
            }
        }

        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    /**
     * 创建赫夫曼树
     *
     * @param nodes
     * @return
     */
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parentNode = new Node(null, leftNode.getWeight() + rightNode.getWeight());
            parentNode.setLeft(leftNode);
            parentNode.setRight(rightNode);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
        }
        return nodes.get(0);
    }

    private static HashMap<Byte, String> huffmanCodes = new HashMap<>();
    private static StringBuilder stringBuilder = new StringBuilder();

    public static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root.getLeft(), "0", stringBuilder);
        getCodes(root.getRight(), "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 生成赫夫曼编码，并放到Map中
     *
     * @param node          传入节点
     * @param code          规定：左子节点为0，右子节点为1
     * @param stringBuilder 用于拼接路径
     */
    public static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if (node != null) {
            if (node.getData() == null) {
                getCodes(node.getLeft(), "0", stringBuilder1);
                getCodes(node.getRight(), "1", stringBuilder1);
            } else {
                huffmanCodes.put(node.getData(), stringBuilder1.toString());
            }
        }
    }

    /**
     * 使用赫夫曼编码来生成赫夫曼编码数据，进行压缩
     *
     * @param bytes        字符串的字节数组
     * @param huffmanCodes 赫夫曼编码
     * @return 返回字节数组，8位对应一个byte，放入到huffmanCodeBytes
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //System.out.println(stringBuilder.toString());

        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        byte[] huffmanCodeBytes = new byte[len];

        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (stringBuilder.length() < i + 8) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }

        return huffmanCodeBytes;
    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树");
        }
    }

    /**
     * 将byte转换为二进制字符串
     *
     * @param flag 标识是否需要补高位，如果是最后1个字节，无需补高位
     * @param b    传入的byte
     * @return 返回b对应的二进制字符串（注意是按照补码返回）
     */
    public static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 解码
     *
     * @param huffmanCodes 赫夫曼编码表
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 原字符串对应的数组
     */
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        // 先得到huffmanBytes对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            // 判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }
        System.out.println(stringBuilder.toString());
        System.out.println(new BigInteger(1,huffmanBytes).toString(2));

        // 把字符串按照赫夫曼编码进行解码得到字符对应的ASCII码
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //System.out.println(map);

        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag && i + count <= stringBuilder.length()) {
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            if (b != null) {
                list.add(b);
            }
            i += count;
        }
        //System.out.println(list);

        // 将List中的数据放入到byte[]中返回
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * 文件压缩
     *
     * @param srcFile  源文件路径
     * @param destFile 目标文件路径
     */
    public static void zipFile(String srcFile, String destFile) {
        FileInputStream fis = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            // 创建输入流
            fis = new FileInputStream(srcFile);
            byte[] b = new byte[fis.available()];
            fis.read(b);

            // 对输入流进行压缩
            byte[] huffmanBytes = huffmanZip(b);

            // 创建输出流，以对象流的方式写入，方便解压。且需要将赫夫曼编码写入
            os = new FileOutputStream(destFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(huffmanBytes);
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                oos.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件解压
     *
     * @param zipFile  压缩文件路径
     * @param destFile 解压文件路径
     */
    public static void unZipFile(String zipFile, String destFile) {
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try {
            // 创建文件输入流和对象输入流
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);

            // 读取压缩数据和编码表
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            // 解压
            byte[] bytes = decode(huffmanCodes, huffmanBytes);

            // 创建输出流进行输出
            os = new FileOutputStream(destFile);
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Node implements Comparable<Node> {
    /**
     * 节点数据
     */
    private Byte data;
    /**
     * 节点权重，表示字符出现的次数
     */
    private int weight;
    private Node left;
    private Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }


    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}

