package review.huffman;

import java.util.*;

//自己重写一遍霍夫曼编码，没写解码
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println("原来的字节长度为："+contentBytes.length+"\r\n内容为："+Arrays.toString(contentBytes));
        List<Node> list = getNodes(contentBytes);
        System.out.println(list);
        System.out.println("一共有"+list.size()+"个不用的字节");
        Node root = createTree(list);
        getEachCode(root,"",new StringBuilder());
        System.out.println("得到的编码为；"+codes);
        byte[] form = getStringFormOfBytes(contentBytes);
        System.out.println("现在字节长度为："+form.length+"\r\n内容为："+Arrays.toString(form));
    }

    //得到字节及对应的次数，并建立节点，放入list中
    public static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> list = new ArrayList<>();
        HashMap<Byte, Integer> map = new HashMap<>();
        Integer count = null;
        for (byte b : bytes) {
            count = map.get(b);
            if (count == null) {
                map.put(b, 1);
            } else {
                map.put(b, count + 1);
            }
        }

        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            list.add(new Node(entry.getValue(), entry.getKey()));
        }
        return list;
    }

    //创建霍夫曼树
    public static Node createTree(List<Node> list) {
        Node left = null;
        Node right = null;
        while (list.size() > 1) {
            Collections.sort(list);
            left=list.remove(0);
            right=list.remove(0);
            Node parent = new Node(left.weight + right.weight, null);
            parent.left=left;
            parent.right=right;
            list.add(parent);
        }
        return list.get(0);
    }

    //存放每个字节及对应的编码
    public static Map<Byte,String> codes=new HashMap<>();
    //根据霍夫曼树得到每个自己对应的编码
    //todo 该方法还是照着写的，自己写并不对
    public static void getEachCode(Node node,String way,StringBuilder stringBuilder){
        StringBuilder builder=new StringBuilder(stringBuilder);
        builder.append(way);
       if (node!=null){
           if (node.value==null){
                getEachCode(node.left,"0",builder);
                getEachCode(node.right,"1",builder);
           }else {
               codes.put(node.value,builder.toString());
           }

       }
    }

    //按照每个字节对应的编码，将要发送的字节完整连接起来，并分成8尾一字节，变成新的byte数组
    public static byte[] getStringFormOfBytes(byte[] bytes){
        StringBuilder stringBuilder= new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            stringBuilder.append(codes.get(bytes[i]));
        }
//        int length =stringBuilder.length()%8==0?stringBuilder.length()/8:stringBuilder.length()/8+1;
        //还是这样更巧妙啊！！！
        int length=(stringBuilder.length() + 7) / 8;
        byte[] newByte=new byte[length];
        String str=stringBuilder.toString();
        String s ="";
        int index=0;
        for (int i = 0; i < stringBuilder.length(); i+=8) {
            if (i+8<stringBuilder.length()){
                s=str.substring(i,i+8);
            }else {
                s=str.substring(i,stringBuilder.length());
            }
            newByte[index++]=(byte) Integer.parseInt(s,2); //疑问？如果是10000000,则int型变成了128，再强转byte，则变成了-128,不会有影响吗？==>不会，解码时是正确的啦
                    }
        return newByte;
    }
}

class Node implements Comparable<Node> {
    public int weight;
    public Byte value;
    public Node left;
    public Node right;

    public Node(int weight, Byte value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "weight=" + weight +
                ", value=" + value +
                '}';
    }
}
