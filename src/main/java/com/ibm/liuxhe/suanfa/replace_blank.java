package com.ibm.liuxhe.suanfa;

public class replace_blank {

  public static void main(String[] args) {
    System.out.println(replace("We are happy."));
  }

  public static String replace(String preString) {
    if (preString == null) {
      return null;
    }

    char[] chars = preString.toCharArray();
    StringBuffer stringBuffer = new StringBuffer();
    for (char i : chars) {
      if (i == ' ') {
        stringBuffer.append("%20");
      } else {
        stringBuffer.append(i);
      }
    }
    return stringBuffer.toString();
  }

  public boolean isValid(String s) {

    int length = s.length() / 2;

    for (int i = 0; i < length; i++) {
      s = s.replace("()", "").replace("{}", "").replace("[]", "");
    }

    if (s.length() == 0) {
      return true;
    }

    return false;
  }

  public class ListNode {

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

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    } else if (l2 == null) {
      return l1;
    } else if (l1.val < l2.val) {
      l1.next = mergeTwoLists(l1.next, l2);
      return l1;
    } else {
      l2.next = mergeTwoLists(l1, l2.next);
      return l2;
    }
  }

  public int maxSubArray(int[] nums) {
    int ans = nums[0];
    int sum = 0;

    return sum;
  }
}
