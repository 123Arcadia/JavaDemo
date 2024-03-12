package AlgorithmNotes.hot100;

public class cycleListNode {



    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 141. 环形链表
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode soft = head;
        ListNode fast = head.next;
        // 第一次相遇在环内,
        while (soft != fast) {
            if (soft.next == null || fast == null) return false;
            soft = soft.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 142. 环形链表 II
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode soft = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            soft = soft.next;
            fast = fast.next.next;
            if (fast == soft) {
                ListNode sofeIdx = soft;
                ListNode fastIdx = head;
                while (sofeIdx != fastIdx) { // 因为前面相遇了，这里一定没哟死循环
                    sofeIdx = sofeIdx.next;
                    fastIdx = fastIdx.next;

                }
                return fastIdx;
            }
        }
        return null;
    }




    /**
     * 160. 相交链表
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        //1.  hash
//        Set<ListNode> seen = new HashSet<>();
//        while (headA!=null)
//        {
//            seen.add(headA);
//            headA = headA.next;
//        }
//
//        while (headB!=null) {
//            if (seen.contains(headB)) return headB;
//            headB = headB.next;
//        }
//        return null;

//        //2. 构造环: 假设headA遍历末尾在让它换到headA头结点，构造HeadA为一个环形链表
//        // 我们诉求的就是headB与环形链表的交点(不可行)
//        ListNode dum = new ListNode(-1);
//        dum.next = headA;
//        while (headA.next!=null)
//        {
//            headA = headA.next;
//        }
//        headA.next = dum.next;
//        //  System.out.println(headA.val + "->" + headA.next.val +"->" +dum.next.val);
//        // 找到headB和环形链表的交集
//        // ListNode newStart = new ListNode(-1);
//        // newStart.next = headB;
//        ListNode soft = headB;
//        ListNode fast = headB;
//        while (fast!=null && fast.next!=null) {
//            fast = fast.next.next;
//            soft = soft.next;
//            if (fast==soft) {
//                ListNode softIdx = soft;
//                ListNode fastIdx = headB;
//                while (softIdx!=fastIdx) {
//                    softIdx = softIdx.next;
//                    fastIdx = fastIdx.next;
//                }
//                // 题目要求不可修改链表，再次复原
//                headA.next = null;
//                return softIdx;
//            }
//        }
//        // 题目要求不可修改链表，再次复原
//        headA.next = null;
//        return null;

        //3. headA遍历完遍历headB, headB遍历完遍历headA; 各自遍历总次数相等
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }
        return a;
    }

}
