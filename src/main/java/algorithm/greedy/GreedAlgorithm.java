package algorithm.greedy;

import java.util.*;

public class GreedAlgorithm {
    public static void main(String[] args) {
        //创建广播电台，放入map中
        HashMap<String, HashSet<String>> broadcast = new HashMap<>();
        //将各个电台放入到broadcast中
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        broadcast.put("K1", hashSet1);
        broadcast.put("K2", hashSet2);
        broadcast.put("K3", hashSet3);
        broadcast.put("K4", hashSet4);
        broadcast.put("K5", hashSet5);
        //所有待覆盖城市
        HashSet<String> allAreas = new HashSet<>();
        Iterator<Map.Entry<String, HashSet<String>>> iterator = broadcast.entrySet().iterator();
        while (iterator.hasNext()) {
            for (String str : iterator.next().getValue()) {
                allAreas.add(str); //将每个set中的城市放入allAreas数组，有的自动不再添加
            }
        }
        //存放选择的电台的集合
        ArrayList<String> selects = new ArrayList<>();
        //定义临时的集合，存放电台覆盖地区的交集
        HashSet<String> temp = new HashSet<>();
        //定义maxKey，保存要放入的电台
        //如果maxKey不为null,则会加到selects
        String maxKey =null;
        while (allAreas.size()!=0){
            maxKey=null; //!!!
            for (String key:broadcast.keySet() ) {
                int lastSize=temp.size(); //记录上一次temp的大小
                temp.clear(); //!!!
                //获取当前key能覆盖的地区
                HashSet<String> areas = broadcast.get(key);
                temp.addAll(areas);
                temp.retainAll(allAreas); //retainAll方法取temp和allAreas集合的交集，交集会赋给temp
                //体现贪心算法，每次都选最优的
                //temp.size>0是为了：例如k1已经放进去了，但还是会检查，则此时的temp.size一定为0，则不必进入if中
                //maxKey==null是为了让每次的第一个满足条件的进入
                if (temp.size()>0&&(maxKey==null||temp.size()>lastSize)){
                    maxKey=key;
                }

            }
            if (maxKey!=null){
                selects.add(maxKey);
                allAreas.removeAll(broadcast.get(maxKey));
            }
        }
        System.out.println(selects);

        //下面是我的写法，其实我觉得我的更好理解
//        Iterator<Map.Entry<String, HashSet<String>>> entryIterator = null;
//        int max = 0;
//        String b = "";
//        int size = 0;
//        while (allAreas.size() > 0) {
//            entryIterator = broadcast.entrySet().iterator();
//            max=0;
//            while (entryIterator.hasNext()) {
//                Map.Entry<String, HashSet<String>> next = entryIterator.next();
//                size = getSize(allAreas, next.getValue()); //也可以不用下面自己写的方法，根据上面老师的写法，可以如下代替；
////                next.getValue().retainAll(allAreas);
////                size=next.getValue().size();
//
//                if (size > max) { //取大的那个，并记录其key
//                    b = next.getKey();
//                    max = size;
//                }
//            }
//            selects.add(b);
//            allAreas.removeAll(broadcast.get(b));
//            broadcast.remove(b); //删除已经加入selects中的电台，避免不必要的比较
//        }
//        System.out.println("选择的电台有：");
//        System.out.println(selects);

    }

    public static int getSize(HashSet<String> allAreas, HashSet<String> k) {
        int count = 0;
        for (String s : k) {
            if (allAreas.contains(s)) {
                count++;
            }
        }
        return count;
    }



}
