package tree.huffmanCode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        //以下是对字符串的压缩和解压
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
//        //以下是对字符串压缩的一步步的结果展现
//        System.out.println(Arrays.toString(contentBytes));
//        System.out.println(contentBytes.length); //40
//        List<Node> list = getNodes(contentBytes);
//        System.out.println(list);
//        System.out.println("赫夫曼树:");
//        Node huffmanTree = createHuffmanTree(list);
//        huffmanTree.preOrder();
//        //测试是否生成了赫夫曼编码表
//        getCodes(huffmanTree);
//        System.out.println(huffmanCodes); //{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
//        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes); //length=17 也就是通过编码后，长度由原来的40变成了17
//        System.out.println("huffmanCodeBytes="+Arrays.toString(huffmanCodeBytes));
////        //以下是将上面步骤封装后进行的测试
        System.out.println("封装后直接使用：");
        byte[] huffmanZip = huffmanZip(contentBytes);
        System.out.println("压缩后的结果为：" + Arrays.toString(huffmanZip) + "\r\n长度为" + huffmanZip.length);
////        //测试解压字符串
        byte[] decode = decode(huffmanCodes, huffmanZip);
        System.out.println("原来的字符串是" + new String(decode));
//        System.out.println("***************************************************************************************************");
//        //测试对文件的压缩和解压
//        String srcFile = "F:\\Pictures\\BenidictCumberbatch\\high definition\\2.jpg";
//        String srcFile = "C:\\Users\\imisf\\Desktop\\1.txt";
//        String dstFile = "C:\\Users\\imisf\\Desktop\\1.zip";
//        zipFile(srcFile, dstFile);
//        System.out.println("压缩文件ok~~");
//        String zipFile = "C:\\Users\\imisf\\Desktop\\1.zip";
//        String destFile ="C:\\Users\\imisf\\Desktop\\1-1.txt";
//        unZipFile(zipFile,destFile);
//        System.out.println("解压文件ok~~"); //由于解压那个方法本身就存在问题，所以解压不一定成功，上面的jpg不成功，但txt成功了



    }


    /**
     * 编写方法，对文件进行压缩
     *
     * @param srcFile 待压缩文件的地址
     * @param dstFile 压缩文件存放的路径
     */
    public static void zipFile(String srcFile, String dstFile) {
        FileInputStream is = null;
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            //创建文件输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件（因为b的大小和源文件一样，故一次即可全部读取）
            is.read(b);
            //进行编码,直接得到压缩后的字节数组
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件输出流
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把编码后的字节数组写入压缩文件(文件输出地方)
            oos.writeObject(huffmanBytes);
            //以对象流的方式写入赫夫曼编码，是为了以后恢复源文件时使用
            //!!!!一定要把赫夫曼编码表写入压缩文件
            oos.writeObject(huffmanCodes);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }


    }


    /**
     * 对压缩文件进行解压
     *
     * @param zipFile 待解压文件所在位置
     * @param dstFile 将文件解压到的位置
     */
    public static void unZipFile(String zipFile, String dstFile) {
        //文件输入流
        FileInputStream is = null;
        //对象输入流
        ObjectInputStream ois = null;
        //文件输出流
        FileOutputStream os = null;

        try {
            is=new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            //因为压缩文件时是用的对象流，所以读取时可以方便的用对象流读出两个不一样的东西
            //读取byte数组，即上面压缩文件时产生的huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte,String> huffmanCodes= (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes写入到dst文件中
            os = new FileOutputStream(dstFile);
            os.write(bytes);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

        }

    }

    /**
     * 将getNodes、createHuffmanTree、getCodes和zip这四个用以压缩的方法封装起来，便于调用
     *
     * @param bytes 原始字符串对应的byte数组
     * @return 压缩后的byte数组（经过赫夫曼编码处理后的字节数组）
     */
    public static byte[] huffmanZip(byte[] bytes) {
        List<Node> list = getNodes(bytes);
        Node huffmanTreeRoot = createHuffmanTree(list);
        getCodes(huffmanTreeRoot);
        byte[] zip = zip(bytes, huffmanCodes);
        return zip;
    }

    //得到节点：字符，次数
    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> list = new ArrayList<>();
        //存储每个byte出现的次数-->map
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            Integer count = map.get(b);
            if (count == null) //如果还没有这个数
                map.put(b, 1);
            else  //如果已经有了这个数，计数加1
                map.put(b, count + 1);
        }
        //遍历map,将字符和次数取出建立Node，并放入list中
//        Set<Map.Entry<Byte, Integer>> entrySet = map.entrySet();
//        Iterator<Map.Entry<Byte, Integer>> iterator = entrySet.iterator();
//        while (iterator.hasNext()){
//            Map.Entry<Byte, Integer> next = iterator.next();
//            list.add(new Node(next.getKey(),next.getValue()));
//        }
        //与上方一致，但更简单
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            list.add(new Node(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    //创建赫夫曼树
    private static Node createHuffmanTree(List<Node> list) {
        Node leftNode;
        Node rightNode;
        while (list.size() > 1) {
            Collections.sort(list);
            leftNode = list.remove(0);
            rightNode = list.remove(0);
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            list.add(parent);
        }
        return list.get(0);
    }

    //将赫夫曼编码表放在Map<Byte,String>中，形式如：32->01 97->100 100->11000等
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //在生成赫夫曼编码表时，需要拼接路径，故定义StringBuilder存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 生成赫夫曼编码表，并放入到huffmanCodes这个map中
     *
     * @param node          传入的节点，根节点
     * @param code          路径：左子节点为0，右子节点为1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder builder = new StringBuilder(stringBuilder); //必须有啊,否则一直传的是同一个stringbuilder，会一直在后面加数字，没有了回溯的作用
        //将code加入到builder中
        builder.append(code);
        if (node != null) {
            //判断当前节点是叶子节点还是非叶子节点
            if (node.data == null) { //非叶子节点
                //递归处理
                getCodes(node.left, "0", builder);
                getCodes(node.right, "1", builder);
            } else { //叶子节点
                huffmanCodes.put(node.data, builder.toString());
            }
        }
    }

    //为了调用方便，重载getCodes
    public static Map<Byte, String> getCodes(Node root) {
        getCodes(root, "", stringBuilder);
        if (root == null)
            return null;
        else
            return huffmanCodes;
    }

    /**
     * 将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]
     *
     * @param bytes        原始的字符串对应的byte[]
     * @param huffmanCodes 上面得到的赫夫曼编码表
     * @return 赫夫曼编码处理后的byte[]
     * 举例：输入contentBytes和huffmanCodes,输出“101010001011111111001000101111111100100010111111110010010100110111000111000001101110100011110010100010111111110011000
     * 1001010011011100”对应的byte[],命名为huffmanCodeBytes；即8位一组对应一个byte,放入到huffmanCodeBytes
     * huffmanCodeBytes[0]=10101000(补码)=>byte [推导：10101000=>10101000-1=10100111(反码) =>11011000(原码) =>-88]
     * huffmanCodeBytes[0]=-88[上面过程用Integer.parseInt("10101000",2)即可得到]
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1、利用赫夫曼编码表将bytes转成赫夫曼编码对应的字符串，即将每个字母（这里都是byte了）对应其赫夫曼编码
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
//        System.out.println("解压时得到的字符串为；"+stringBuilder.toString());
//        System.out.println(stringBuilder);// 1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100
        //2、将“101010001011111111......”转成byte
        //确定huffmanCodeBytes的长度
        int len = (stringBuilder.length() + 7) / 8; //巧妙！！！用可以取模，判断是否加1,但这个多棒啊
        byte[] huffmanCodeBytes = new byte[len];
        String strBytes;
        int index = 0; //huffmanCodeBytes数组的索引
        for (int i = 0; i < stringBuilder.length(); i = i + 8) { //因为每8位一个byte
            if (i + 8 > stringBuilder.length())  //最后不够8个时
                strBytes = stringBuilder.substring(i);
            else
                strBytes = stringBuilder.substring(i, i + 8); //注：subString是左闭右开的

            huffmanCodeBytes[index++] = (byte) Integer.parseInt(strBytes, 2);
        }
        return huffmanCodeBytes;
    }

    //完成解压
    //思路：
    // 1、将huffmanCodeBytes[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //重新转写成 赫夫曼编码对应的二进制的字符串“1010100010111111110010001011111。。。”
    // 2、将“1010100010111111110010001011111。。。”对照 赫夫曼编码 转成“i like like like java do you like a java”

    /**
     * 将一个字节转成一个二进制的字符串
     *
     * @param flag 是否需要补高位，true则要补，false表示不补
     * @param b    传入的byte
     * @return 对应的二进制的字符串（注意是按补码返回，而且编码时也是用的补码，因为计算机就是用的补码）
     */
    private static String byteToBitString(boolean flag, byte b) {
//        //使用变量保存b,将b转成int
//        int temp =b;
//        temp |= 256; //即temp=temp | 256； |(按位或)的意思是两个二进制对应位为0时该位为0，否则为1。而256=>1 0000 0000,则0000 0001 | 1 0000 0000=1 0000 0001
        //下面两行代码的注释是在没有上面一行代码时写的，因为这个方法是在不断地发现问题中进行修改
//        String binaryString = Integer.toBinaryString(temp); //不能直接返回，因为返回的是temp对应的二进制的补码，负数是32位，正数不一定 例：b=-1，则返回11111111111111111111111111111111
//        String s= binaryString.substring(binaryString.length()-8); //返回的是最后8位，但若没有temp |= 256这个部位操作，如果是正数，如b=1，则上面binaryString得到的直
//        return s;                                                  //接是1，没法有8个，故对正数需要补位,用temp |= 256

        //以上，还存在一个问题，就是最后一个数，它原来所对应的很可能并不是8位，不需要补齐，所以加了flag来判断
        int temp = b;
        if (flag) { //可能最后一个不需要高位补齐,虽然只是可能，但老师把这里理解成了就是不用补齐，额。。。
            temp |= 256; //此处不管正负数都可以用的，主要是针对正数
        }
        String binaryString = Integer.toBinaryString(temp);
        if (flag || temp < 0) { //针对下面提出的第二个问题，将原来的if(flag)加了一个可能性
            return binaryString.substring(binaryString.length() - 8);
        } else { //todo 这里还有两个老师没考虑的问题：第一个没解决呢，第二个根据网友的建议改了
            // 1、最后字节若以0开头，则没法使用此方法获得真实的二进制字符串；比如：最后一个是28,进入else后得到11100，但也有可能，它原来是0011100或00011100等，而这些情况被排除了
            //2、若最后字节是负数，则它是32位的，进入这个else后返回的数据过长了
            return binaryString;
        }

    }

    /**
     * 对数据的解码
     *
     * @param huffmanCodes 赫夫曼编码表
     * @param huffmanBytes 要解压的字节数组
     * @return 未压缩前（解压后）的字符串
     */
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //得到二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = (i == huffmanBytes.length - 1); //最后一个则flag为true
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i])); //这里byteToBitString里的!flag还是挺巧妙的，要是我的话会只会单独判断是否是最后一个，否则怎样怎样，这里巧妙地放在了一起

        }
//        System.out.println(stringBuilder.toString());
        //把字符串按照指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为方向查询；例：a->100 => 100->a
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //创建集合，存放解出来的byte
        List<Byte> list = new ArrayList<>();
        //todo 巧妙！！！
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = stringBuilder.substring(i, i + count); //i不动而让count增加
                b = map.get(key);
                if (b == null) {
                    count += 1; //没匹配到，则再往后加一个数字，继续匹配
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        //当for循环结束时，list中就得到了所有字符“i like like like java do you like a java”的byte型
        //将list中的数据放到byte数组中
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }
}

//创建Node，带数据和权值
class Node implements Comparable<Node> {
    Byte data; //存储数据，如'a'=>97
    int weight; //权值，表现字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
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
        //排序，从小到大
        return this.weight - o.weight;
    }
}