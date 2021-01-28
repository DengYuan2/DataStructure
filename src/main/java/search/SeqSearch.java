package search;

//线性(顺序)查找
public class SeqSearch {
    public static void main(String[] args) {
//        int[] arr={1,9,11,-1,34,89}; //无序数组
//        int index=seqSearch(arr,11);
//        if (index==-1)
//            System.out.println("没有查找到~~");
//        else
//            System.out.println("找到，下标位"+index);
    }

    //找到一个即返回；若想找到全部，可以将找到的值的下标放到集合里
    public static int seqSearch(int[] array,int value){
        //逐一比对，有相同的值则返回下标,只返回第一个找到的值
        for (int i = 0; i < array.length; i++) {
            if (array[i]==value)
                return i;
        }
        return -1; //没有找到
    }
}
