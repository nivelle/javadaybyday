
package com.nivelle.guide.java2e.jdk;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * HashMap
 *
 * @author fuxinzhong
 * @date 2019/06/16
 */
public class HashMapData {

    public static void main(String[] args) throws Exception {

        ////////////////////////////整体说明//////////////////////////
        /**
         * Hash table based implementation of the <tt>Map</tt> interface.  This implementation provides all of the optional map operations,
         * and permits<tt>null</tt> values and the <tt>null</tt> key.
         * The HashMap class is roughly equivalent to HashTable, except that it is unsynchronized and permits nulls.)
         * //hashMap 允许 null key 和 null value ,大体上等于 hashTable, 不同点在于hashMap是非同步的以及允许空值
         * This class makes no guarantees as to the order of the map; in particular,
         * it does not guarantee that the order will remain constant over time.
         */
        /**
         * 对于 get 和 put 操纵是常量时间级别的
         * Iteration【迭代】 over collection views requires time proportional【成比例】 to the "capacity" of the HashMap instance (the number of buckets)
         * plus its size (the number of key-value mappings).it's very important not to set the initial capacity too high【初始容量不能太高】 (or the load factor too low)
         * if iteration performance is important.
         */
        /**
         * 影响hashMap性能的两个属性是：初始容量(initial capacity)和加载因子(load factor)
         *
         * When the number of entries in the hash table exceeds【超过】 the product of the load factor and the current capacity,
         * the hash table is rehashed (that is, internal data structures are rebuilt) so that the hash table has approximately twice【大约两倍】 the
         * number of buckets.
         */
        /**
         * 根据时间和空间的因素,默认的加载因子是 0.75
         */
        /**
         * hashMap 非同步，需要外部同步来实现同步。或者可以使用 Collections.synchronizedMap
         *
         * 在返回迭代器之后,除非通过迭代器的remove方法,其他改变hashMap结构的方法都有可能会在迭代期间抛出 ConcurrentModificationException 异常。
         *
         * 采用快速失败机制,而不是在一个不确定的未来时机抛出异常。同时快速失败机制并不是可靠的,仅仅是力所能及的抛出异常。不能依赖快速失败机制来
         *
         * Note that the fail-fast behavior of an iterator cannot be guaranteed as it is, generally speaking, impossible to make any hard guarantees in the
         * presence of unsynchronized concurrent modification.  Fail-fast iterators throw <tt>ConcurrentModificationException</tt> on a best-effort basis.
         * Therefore, it would be wrong to write a program that depended on this exception for its correctness: the fail-fast behavior of iterators
         * should be used only to detect bugs.
         */
        /**
         * treeNode 默认hashCode 排序,如果实现了Comparable 接口,则按照比较器进行排序。
         */
        /////////////////////////// 静态常量///////////////////////////////
        /**
         *
         *    //初始化默认容量2的4次方,必须是2的整数倍
         *    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
         *
         *     //最大为2的30次方
         *     static final int MAXIMUM_CAPACITY = 1 << 30;
         *
         *     //默认加载因子
         *    static final float DEFAULT_LOAD_FACTOR = 0.75f;
         *
         *     //链表数超过8个则考虑进行转换为红黑树
         *     static final int TREEIFY_THRESHOLD = 8;
         *
         *     //当执行resize操作时，当桶中bin的数量少于UNTREEIFY_THRESHOLD时使用链表来代替树。默认值是6
         *     static final int UNTREEIFY_THRESHOLD = 6;
         *
         *     //当集合中的容量大于这个值时，表中的桶才能进行树形化 ，否则桶内元素太多时会扩容而不是树形化 \
         *     为了避免进行扩容、树形化选择的冲突，这个值不能小于 4 * TREEIFY_THRESHOLD
         *     static final int MIN_TREEIFY_CAPACITY = 64;
         *
         *     //装载因子，是用来衡量 HashMap 满的程度，计算HashMap的实时装载因子的方法为：size/capacity，而不是占用桶的数量去除以capacity。capacity 是桶的数量，也就是 table 的长度length。
         *     //默认的负载因子0.75 是对空间和时间效率的一个平衡选择，建议大家不要修改，除非在时间和空间比较特殊的情况下，如果内存空间很多而又对时间效率要求很高，可以降低负载因子loadFactor 的值；相反，如果内存空间紧张而对时间效率要求不高，可以增加负载因子 loadFactor 的值，这个值可以大于1。
         *     final float loadFactory
         */
        //////////////////////////内部方法/////////////////////////////////////

        /**
         * 默认加载因子:0.75
         *
         * final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict) {
         *         int s = m.size();
         *         if (s > 0) {
         *             if (table == null) { // pre-size
         *                 float ft = ((float)s / loadFactor) + 1.0F;
         *                 int t = ((ft < (float)MAXIMUM_CAPACITY) ?
         *                          (int)ft : MAXIMUM_CAPACITY);
         *                 if (t > threshold)
         *                     //table的容量是离t最近的2的整次幂
         *                     threshold = tableSizeFor(t);
         *             }
         *             else if (s > threshold)
         *                 //若table已经初始化,容量不够则需要进行扩容
         *                 resize();
         *             for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
         *                 K key = e.getKey();
         *                 V value = e.getValue();
         *                 putVal(hash(key), key, value, false, evict);
         *             }
         *         }
         *     }
         */
        // 添加元素
        /**
         * final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
         *                    boolean evict) {
         *         Node<K,V>[] tab; Node<K,V> p; int n, i;
         *         if ((tab = table) == null || (n = tab.length) == 0)
         *             //槽是空的
         *             n = (tab = resize()).length;
         *         if ((p = tab[i = (n - 1) & hash]) == null)
         *             tab[i] = newNode(hash, key, value, null);
         *         else {
         *             Node<K,V> e; K k;
         *             if (p.hash == hash &&
         *                 ((k = p.key) == key || (key != null && key.equals(k))))
         *                 e = p;
         *             else if (p instanceof TreeNode)
         *                 e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
         *             else {
         *                 for (int binCount = 0; ; ++binCount) {
         *                     if ((e = p.next) == null) {
         *                         p.next = newNode(hash, key, value, null);
         *                         if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
         *                             treeifyBin(tab, hash);
         *                         break;
         *                     }
         *                     if (e.hash == hash &&
         *                         ((k = e.key) == key || (key != null && key.equals(k))))
         *                         break;
         *                     p = e;
         *                 }
         *             }
         *             if (e != null) { // existing mapping for key
         *                 V oldValue = e.value;
         *                 if (!onlyIfAbsent || oldValue == null)
         *                     e.value = value;
         *                 afterNodeAccess(e);
         *                 return oldValue;
         *             }
         *         }
         *         ++modCount;
         *         if (++size > threshold)
         *             resize();
         *         afterNodeInsertion(evict);
         *         return null;
         *     }
         */

        //扩容函数
        /**
         *  Initializes or doubles table size.  If null, allocates in accord with initial capacity target held in field threshold.
         *  Otherwise, because we are using power-of-two expansion, the elements from each bin must either stay at same index, or move
         *  with a power of two offset in the new table
         *   final Node<K,V>[] resize() {
         *         Node<K,V>[] oldTab = table;
         *         int oldCap = (oldTab == null) ? 0 : oldTab.length;//容量
         *         int oldThr = threshold;//阀值
         *         int newCap, newThr = 0;
         *         //1. 已经初始化过容量的扩容
         *         if (oldCap > 0) {
         *             if (oldCap >= MAXIMUM_CAPACITY) {
         *                 threshold = Integer.MAX_VALUE;
         *                 return oldTab;
         *             }
         *             //2. 如果 oldCap 扩大为原来的2倍同时还小于 MAXIMUM_CAPACITY（2^30） && oldCap 是大于 默认的 DEFAULT_INITIAL_CAPACITY（16）
         *             else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
         *                      oldCap >= DEFAULT_INITIAL_CAPACITY)
         *             //3. 阀值数量扩大为原来的2倍，容量扩大为原来的2倍
         *                 newThr = oldThr << 1;
         *         }
         *         //4. 没有初始化容量,但是已经添加过元素，有一定数量的阀值数量,将阀值数量赋值给新的容量;阀值数量不变
         *         else if (oldThr > 0) // initial capacity was placed in threshold
         *             newCap = oldThr;
         *         else {
         *             // 5. zero initial threshold signifies using defaults
         *             newCap = DEFAULT_INITIAL_CAPACITY; //16
         *             newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
         *         }
         *         //6. 阀值的数量是0
         *         if (newThr == 0) {
         *             float ft = (float)newCap * loadFactor;
         *             newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
         *                       (int)ft : Integer.MAX_VALUE);
         *         }
         *         threshold = newThr;
         *         @SuppressWarnings({"rawtypes","unchecked"})
         *         table = newTab;
         *         if (oldTab != null) {
         *             for (int j = 0; j < oldCap; ++j) {
         *                 Node<K,V> e;
         *                 // 桶位置不为空,赋值给临时变量e
         *                 if ((e = oldTab[j]) != null) {
         *                     //旧桶位置置为null,让垃圾回收器可以回收
         *                     oldTab[j] = null;
         *                     //如果该位置仅仅一个元素
         *                     if (e.next == null)
         *                         //计算下标 e.hash & (newCap -1 )
         *                         newTab[e.hash & (newCap - 1)] = e;
         *                         //如果该节点是一个树节点
         *                     else if (e instanceof TreeNode)
         *                         //Splits nodes in a tree bin into lower and upper tree bins,or untreeifies if now too small. Called only from resize;
         *                         //see above discussion about split bits and indices.
         *
         *                         ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
         *                     else { // preserve order
         *                         // 低位头;低位尾
         *                         Node<K,V> loHead = null, loTail = null;
         *                         // 高位头;高位尾
         *                         Node<K,V> hiHead = null, hiTail = null;
         *                         Node<K,V> next;
         *                         // 扩容后原来的元素进行重新安置
         *                         do {
         *                             next = e.next;
         *                             if ((e.hash & oldCap) == 0) {
         *                                 if (loTail == null)
         *                                     loHead = e;
         *                                 else
         *                                     loTail.next = e;
         *                                 loTail = e;
         *                             }
         *                             else {
         *                                 if (hiTail == null)
         *                                     hiHead = e;
         *                                 else
         *                                     hiTail.next = e;
         *                                 hiTail = e;
         *                             }
         *                         } while ((e = next) != null);
         *                         if (loTail != null) {
         *                             loTail.next = null;
         *                             newTab[j] = loHead;
         *                         }
         *                         if (hiTail != null) {
         *                             hiTail.next = null;
         *                             newTab[j + oldCap] = hiHead;
         *                         }
         *                     }
         *                 }
         *             }
         *         }
         *         return newTab;
         *     }
         */
        /**
         * 默认无参构造函数,初始化的加载因子:0.75
         */
        HashMap hashMap = new HashMap();

        hashMap.put("1", "nivelle");
        hashMap.put("2", "jessy");
        System.out.println("无参初始化HashMap" + hashMap);

        /**
         * 指定 初始化数据,默认加载因子0.75
         *
         * 1.如果目标集合为空,则除以装载因子+1,然后和 threshold比较是否需要桶扩容
         *
         * 2.如果目标集合不为空,则 直接判断是否需要扩容
         */
        HashMap hashMap1 = new HashMap(hashMap);
        System.out.println("初始化参数是已经存在的HashMap" + hashMap1);

        HashMap hashMap2 = new HashMap();
        hashMap2.put("3", "xihui");
        hashMap2.put("4", "wangzheng");
        System.out.println("hashMap键值对数量:" + hashMap2.size());
        /**
         * 也就是判断size是否为0
         */
        System.out.println("判断是否为空hashMap:" + hashMap2.isEmpty());

        /**
         * 集合元素的key的hashCode和
         */
        int hash = hashMap2.hashCode();
        System.out.println("hashMap的hashCode:" + hash);

        /**
         * 判断是否存在某个指定键/值
         */
        System.out.println("是否包含指定键:" + hashMap2.containsKey("3"));
        System.out.println("是否包含指定值:" + hashMap2.containsValue("xihui"));

        /**
         * 移除某个元素
         */
        System.out.println("移除前数据hashMap2:" + hashMap2);
        System.out.println("移除指定元素,返回关联的值:" + hashMap2.remove("3"));
        System.out.println("移除后数据hashMap2:" + hashMap2);
        //不存在的键
        System.out.println("移除指定元素,返回关联的值:" + hashMap2.remove("8"));

        hashMap2.put("5", "xubing");
        hashMap2.put("6", "biliang");
        /**
         * value集合
         */
        System.out.println("values Collections 集合:" + hashMap2.values());
        /**
         * key集合
         */
        System.out.println("keys Set 集合:" + hashMap2.keySet());

        /**
         * EntrySet集合
         */
        System.out.println("EntrySet 集合:" + hashMap2.entrySet());

        Set<Map.Entry<String, String>> entries = hashMap2.entrySet();
        Iterator iterator = entries.iterator();
        while (iterator.hasNext()) {
            System.out.println("entries 集合内的元素:" + iterator.next());
        }

        System.out.println(hashMap2.getOrDefault("8", "不存在情况下的默认值"));

        System.out.println("不存在情况返回默认值也不会在底层数据上添加上此元素:" + hashMap2.get("8"));

        /**
         * 如果存在则不添加,同时返回旧值。
         */
        System.out.println("存在则不添加,返回旧值:" + hashMap2.putIfAbsent("6", "biliang2"));
        System.out.println("hashMap2:" + hashMap2);

        System.out.println("不存在则添加,返回旧值:" + hashMap2.putIfAbsent("8", "biliang2"));
        System.out.println("hashMap2:" + hashMap2);

        /**
         * 替换值
         */
        System.out.println("替换值:" + hashMap2.replace("8", "biliang2", "biliang3"));
        System.out.println("hashMap2:" + hashMap2);

        /**
         * 对指定的键或者值 执行操纵
         */
        System.out.println("对value值指定执行某个操作:" + hashMap2.compute("8", (k, v) -> v + "++"));
        System.out.println("hashMap2:" + hashMap2);

        /**
         * HashMap 使用的方法很巧妙，它通过 hash & (table.length -1)来得到该对象的保存位，前面说过 HashMap 底层数组的长度总是2的n次方，这是HashMap在速度上的优化。
         * 当 length 总是2的n次方时，hash & (length-1)运算等价于对 length 取模，也就是 hash%length，但是&比%具有更高的效率。
         */
        int n = 16;
        int result1 = n & (32 - 1);
        int result2 = n % 32;
        boolean result3 = result1 == result2 ? true : false;
        System.out.println("位运算优化:" +result3);

        /**
         * 32位的hashCode值，通过与h>>>16然后让高位也参与到运算
         */
        String key = "8";
        int h;
        int hashInt = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        System.out.println(hashInt);

    }


}