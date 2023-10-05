import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class Others {
    // 不使用开方函数计算根号
    public static double sqrt(double number) {
        // 定义一个较小的阈值，当估算值与实际值的差异小于这个值时，认为已趋于稳定
        final double EPSILON = 1e-10;

        // 初始估算值
        double estimate = number;
        double lastEstimate;

        do {
            lastEstimate = estimate;

            // 牛顿迭代法的核心公式
            estimate = 0.5 * (estimate + number / estimate);

        } while (Math.abs(estimate - lastEstimate) > EPSILON);

        return estimate;
    }

    // 用List手写HashTable
    public static class SimpleHashTable<K, V> {
        private static class Entry<K, V> {
            K key;
            V value;

            public Entry(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private List<Entry<K, V>> table;

        public SimpleHashTable() {
            this.table = new ArrayList<>();
        }

        public void put(K key, V value) {
            for (Entry<K, V> entry : table) {
                if (entry.key.equals(key)) {
                    entry.value = value;
                    return;
                }
            }
            table.add(new Entry<>(key, value));
        }

        public V get(K key) {
            for (Entry<K, V> entry : table) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
            return null;
        }

        public void remove(K key) {
            for (Entry<K, V> entry : table) {
                if (entry.key.equals(key)) {
                    table.remove(entry);
                    return;
                }
            }
        }

        public boolean containsKey(K key) {
            for (Entry<K, V> entry : table) {
                if (entry.key.equals(key)) {
                    return true;
                }
            }
            return false;
        }

        public static void main(String[] args) {
            SimpleHashTable<String, Integer> map = new SimpleHashTable<>();
            map.put("one", 1);
            map.put("two", 2);
            System.out.println(map.get("one")); // Outputs: 1
            System.out.println(map.get("two")); // Outputs: 2
        }
    }


    public static void main(String[] args) {
        HashMap<String, String> hs = new HashMap<>();
        Hashtable<String, String> ht = new Hashtable<>();
    }
}
