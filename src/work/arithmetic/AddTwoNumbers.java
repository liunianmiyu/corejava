package work.arithmetic;

/**
 * @TODO
 * @author yicha
 * @date 2015年10月16日
 */
public class AddTwoNumbers {

	static class ListNode {
		int value;
		ListNode next;

		ListNode(int x) {
			this.value = x;
			this.next = null;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			StringBuilder sb = new StringBuilder();
			sb.append(this.value);
			ListNode next = this.next;
			while (next != null) {
				sb.append("->");
				sb.append(next.value);
				next = next.next;
			}
			return sb.toString();
		}
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode res = new ListNode(0);
		ListNode cur = res;

		int sum = 0;
		while (true) {
			if (l1 != null) {
				sum += l1.value;
				l1 = l1.next;
			}

			if (l2 != null) {
				sum += l2.value;
				l2 = l2.next;
			}

			cur.value = sum % 10;
			sum /= 10;

			if (l1 != null || l2 != null || sum != 0) {
				cur = (cur.next = new ListNode(0));
			} else {
				break;
			}
		}
		return res;
	}

	public static void main(String[] srgs) {
		// TODO Auto-generated method stub
		ListNode node11 = new ListNode(1);
//		ListNode node12 = new ListNode(5);
//		ListNode node13 = new ListNode(4);
//
//		node11.next = node12;
//		node12.next = node13;

		ListNode node21 = new ListNode(2);
//		ListNode node22 = new ListNode(6);
//		ListNode node23 = new ListNode(3);
//
//		node21.next = node22;
//		node22.next = node23;

		ListNode res = addTwoNumbers(node11, node21);
		System.out.println(res);
	}
}
