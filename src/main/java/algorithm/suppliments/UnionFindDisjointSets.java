package algorithm.suppliments;

/**
 * 并查集
 * 结合 https://zhuanlan.zhihu.com/p/93647900/
 * 和 https://github.com/CyC2018/CS-Notes/blob/master/notes/%E7%AE%97%E6%B3%95%20-%20%E5%B9%B6%E6%9F%A5%E9%9B%86.md#quick-find
 *
 * @author DengYuan2
 * @create 2021-01-26 8:47
 */
public class UnionFindDisjointSets {
    int fa[];

    //初始化
    //因为下面重写了该构造器，所以暂时将其注释掉
//    public UnionFindDisjointSets(int N) {
//        fa = new int[N];
//        for (int i = 0; i < N; i++) {
//            fa[i] = i;
//        }
//    }

    /**
     * 注：find方法查找的都是最终的根节点
     * 方式一：快速查找，但合并代价高
     * 这里：每个节点的fa[i]存的都是根节点
     *
     * @param p
     * @return
     */
    public int find1(int p) {
        return fa[p];
    }

    public void merge1(int p, int q) {
        int i = find1(p);
        int j = find1(q);
        if (i == j) {
            return;
        }
        // j合并到i上 下面这行没必要，因为在下面的for循环中会做
//        fa[j] =i;
        //以前父节点是j的，现在都将父节点改为i
        for (int k = 0; k < fa.length; k++) {
            if (fa[k] == j) {
                fa[k] = i;
            }
        }
    }

    /**
     * 方式二、查找麻烦，但合并简单
     * 这里：fa[i]存的是父节点，不断往上找才能找到根节点
     *
     * @param p
     * @return
     */
    public int find2(int p) {
        if (fa[p] == p) {
            return p;
        }
        return find2(fa[p]);
        //也可以用下面的迭代方式
//        while (fa[p]!=p){
//            p=fa[p];
//        }
//        return p;
    }

    public void merge2(int p, int q) {
        int i = find2(p);
        int j = find2(q);
        if (i == j) {
            return;
        }
        // j合并到i上
        fa[j] = i;


        //上面这些内容可以用这一行代替
//        fa[find2(p)]=find2(q);
    }


    //todo 路径压缩，效果其实就是find1的意思，即每个fa[i]存的都是根节点
    //使查找变得简单！！！

    /**
     * 这里是通过修改find2()来实现find()的效果
     *
     * @param p
     * @return
     */
    public int find3(int p) {
        if (fa[p] == p) {
            return p;
        } else {
            fa[p] = find3(fa[p]);
            return fa[p];
        }

        //以上也可以简写为1行
//        return fa[p] == p ? p : (fa[p] = find3(fa[p]));
    }

    /**
     todo 按秩合并 把简单的树往复杂的树上合并，而不是相反。因为这样合并后，到根节点距离变长的节点个数比较少
     * 修改初始化方法
     */

    //因为没法和上面的构造器同时存在，所以要先注释掉一个
    int[] rank;
    public UnionFindDisjointSets(int N) {
        fa = new int[N];
        rank=new int[N];
        for (int i = 0; i < N; i++) {
            fa[i] = i;
            rank[i]=1;
        }
    }

    public void merge3(int p,int q){
        int x = find3(p);
        int y = find3(q);
        if (rank[x]<=rank[y]){
            fa[x]=y;
        }else {
            fa[y]=x;
        }
        if (x!=y&&rank[x]==rank[y]){
            rank[y]++;
        }
    }

    //todo 总的来说，可以使用这个组合来提高效率：第二种初始化方式、find3()、merge3()



}
