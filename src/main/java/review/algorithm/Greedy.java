package review.algorithm;

import java.util.*;

public class Greedy {
    public static void main(String[] args) {
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

        HashSet<String> areas = new HashSet<>();
        for (Map.Entry<String, HashSet<String>> entry : broadcast.entrySet()) {
            HashSet<String> value = entry.getValue();
            areas.addAll(value);
//            Iterator<String> iterator = value.iterator();
//            while (iterator.hasNext()){
//                areas.add(iterator.next());
//            }
        }
//        System.out.println(areas);
//        greedy(broadcast, areas);
        test(broadcast,areas);

    }

    public static void greedy(HashMap<String, HashSet<String>> broadcast, HashSet<String> areas) {
        ArrayList<String> selects = new ArrayList<>();
        while (areas.size() > 0) {
            int count = 0;
            int max = 0;
            String broad = "";
//            String next="";
            HashSet<String> tmp = null; //tmp在每次while循环中都应该置空
            for (Map.Entry<String, HashSet<String>> entry : broadcast.entrySet()) {
                //获得电台可覆盖城市的数量
//                while (iterator.hasNext()){
//                    next = iterator.next();
//                    if (areas.contains(next)){
//                        count++;
//                    }
//                }
                //用老师的方法，直接用retainAll方法，更容易获得count
                tmp.addAll(entry.getValue()); //注意不能直接令tmp=entry.getValue,会破坏原有结构的
                tmp.retainAll(areas); //此时tmp发生改变，为原tmp与areas的交集
                count = tmp.size();
                if (count > max) { //找到覆盖城市最多的那个电台
                    max = count;
                    broad = entry.getKey();
                }
            }
            selects.add(broad);
            HashSet<String> remove = broadcast.remove(broad); //将已经被选择的电台从电台集合中删除，避免下次不必要的比较
            areas.removeAll(remove);
        }

        System.out.println(selects);

    }


    public static void test(HashMap<String, HashSet<String>> broadcast, HashSet<String> areas) {
        ArrayList<String> resList = new ArrayList<>();
        int max = 0;
        int count = 0;
        String maxKey = "";
        while (!areas.isEmpty()){
            max = 0;
            for (String  keySet:broadcast.keySet()){
                count=0;
                HashSet<String> set = broadcast.get(keySet);
                //每个广播台的覆盖区域
                ArrayList<String> arrayList = new ArrayList<>(set);
                for (int i = 0; i < set.size(); i++) {
                    if (areas.contains(arrayList.get(i))){
                        count++;
                    }
                }
                if (count>max){
                    max=count;
                    maxKey=keySet;
                }
            }
            resList.add(maxKey);
            areas.removeAll(broadcast.get(maxKey));
            broadcast.remove(maxKey);
        }
        System.out.println(resList);

    }
}
