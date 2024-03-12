package AlgorithmNotes.hot100;


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class isPalindrome {

    public boolean isPalindrome(ListNode head) {
//        ListNode lastHead = head;
//        while (lastHead.next!=null) {
//            lastHead = lastHead.next;
//        }
//        while (lastHead != null && head != null) {
//            System.out.println(lastHead.val+", " + head.val);
//            if (head.val != lastHead.val) {
//                return false;
//            }
//            head = head.next;
//            lastHead = lastHead.next;
//        }
//        return true;

//        //1. 复制到数组中
//        List<Integer> vals = new ArrayList<>();
//        ListNode cur = head;
//        while (cur != null) {
//            vals.add(cur.val);
//            cur = cur.next;
//        }
//
//        // 双指针判断回文
//        int l = 0, r = vals.size() - 1;
//        while (l < r) {
//            if (vals.get(l) != vals.get(r)) {
//                return false;
//            }
//            l++;
//            r--;
//        }
//        return true;

        //2. 快慢指针, 最终快指针到尾部，慢指针导致中间, 后一半；链表反转
       if (head == null) return false;
       // 返回中间的节点: 这里half.next才是后一半的起始
       ListNode haif = endOffHalf(head);
       // 返回后一半的链表
        ListNode lastStart = reverseList(null, haif.next);

        ListNode p1 = head;
        ListNode p2 = lastStart;
        boolean res = true;
        while (res && p2 != null) {
            if (p1.val != p2.val) {
                res = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        // 还原链表结构
        haif.next = reverseList(null, haif.next);
        return res;
    }

    /**
     * 反转链表
     * @param pre 上一个节点
     * @param head 当前节点
     */
    private ListNode reverseList(ListNode pre, ListNode head) {
        if (head == null) return pre;
        // 递归
        ListNode node = reverseList(head, head.next);
        head.next = pre;
        return node;
    }

    private ListNode endOffHalf(ListNode head) {
        ListNode soft = head;
        ListNode fast = head;
        while (fast.next!=null && fast.next.next!=null) {
            soft = soft.next;
            fast = fast.next.next;
        }
        return soft;
    }
}