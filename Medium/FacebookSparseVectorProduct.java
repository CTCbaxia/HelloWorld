/*
Facebook - sparse dot
用 list 来记录每个非零的位置和值
遍历一个向量上的非零值，binary search 来找另一个向量上，对应 index 的地方是不是有非 0 值

Time: O(n)
Space: O(1)
*/
    private int binarySearch(List<Vector> a, int target) { //找 a 的对应点上有没有 那个 index
        int l = 0, r = a.size();
        while (l < r - 1) {
            int mid = l + (r - l) / 2;
            if (a.get(mid).index == target)
                return a.get(mid).value;
            else if (a.get(mid).index < target)
                l = mid + 1;
            else r = mid;
        }
        if (l >= a.size() || a.get(l).index != target) return 0;
        return a.get(l).value;
    }

    public int[] dotList(int[] va, int[] vb) {
        List<Vector> a = new ArrayList<>();
        for (int i = 0; i < va.length; i ++)
            if (va[i] > 0) a.add(new Vector(i, va[i]));
                
        List<Vector> b = new ArrayList<>();
        for (int i = 0; i < vb.length; i ++)
            if (vb[i] > 0) b.add(new Vector(i, vb[i]));

        int[] c = new int[va.length];


        for (int i = 0; i < b.size(); i ++) {
            int tmp = binarySearch(a, b.get(i).index);
            if (tmp != 0) {
                tmp *= b.get(i).value;
                c[b.get(i).index] = tmp;
            }
        }

        return c;
    }