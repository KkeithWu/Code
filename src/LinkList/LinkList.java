package LinkList;

//  Definition for singly-linked list.
public class LinkList {

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode startPre, cur, next, last, newHead;
        //找到m前一个
        startPre = head;

        for(int i = 1; i < m-1 && startPre != null; i++){
            startPre = startPre.next;
        }
        if(startPre == null || startPre.next == null){
            return head;
        }

        //头节点就开始反转
        if (m == 1) {
            startPre = null;
            cur = head;
        }else {
            cur = startPre.next;
        }

        //注意了 这里cur==end的时候，没有对end这个节点的next操作 这里需要补上。
        last = cur;
        for(int i = m; i <= n; i++){
            next = cur.next;
            cur.next = last;
            last = cur;
            cur = next;
        }
        //注意了 这里cur==end的时候，没有对end这个节点的next操作 这里需要补上。
        if(m == 1){
            head.next = cur;
            newHead = last;
        }else {
            newHead = head;
            startPre.next.next = cur;
            startPre.next = last;
        }

        printList(startPre);
        return newHead;
    }

    public static ListNode reverseBetweenOld(ListNode head, int m, int n) {
        ListNode startPre, end, cur, next, last;
        //找到m前一个
        startPre = head;

        for(int i = 1; i < m-1; i++){
            startPre = startPre.next;
        }
        cur = startPre.next;
        //头节点就开始反转
        if (m == 1) {
            startPre = null;
            cur = head;
        }
        end = cur;
        for(int i = m; i < n; i++){
            end = end.next;
        }
        last = end.next;
        next = cur.next;
        //注意了 这里cur==end的时候，没有对end这个节点的next操作 这里需要补上。
        while(cur != end){
            next = cur.next;
            cur.next = last;
            last = cur;
            cur = next;
        }
        //注意了 这里cur==end的时候，没有对end这个节点的next操作 这里需要补上。
        end.next = last;
        startPre.next = end;

        printList(startPre);
        return head;
    }

    public static void printList(ListNode node) {
        while(node != null){
            System.out.printf("%d - ", node.val);
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;
        printList(node1);
        ListNode node = reverseBetween(node1, 1,5);
        printList(node);
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}