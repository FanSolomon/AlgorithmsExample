package Basic_05;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集实现
 *
 * 查询次数 + 合并次数 逼近与O(N)时，
 * 平均单次查询与合并的时间复杂度为O(1)
 */
public class Code_UnionFind {

	public static class Node {
		// whatever you like String, Int, Char, F...
	}

	public static class UnionFindSet {
		public HashMap<Node, Node> fatherMap;	// key : child, value : father
		public HashMap<Node, Integer> sizeMap;

		public UnionFindSet(List<Node> nodes) {
			makeSets(nodes);
		}

		/**
		 * private方法，只允许初始化时被调用
		 */
		private void makeSets(List<Node> nodes) {
			fatherMap = new HashMap<Node, Node>();
			sizeMap = new HashMap<Node, Integer>();
			for (Node node : nodes) {
				fatherMap.put(node, node);
				sizeMap.put(node, 1);
			}
		}

		/**
		 * 递归版本
		 */
		private Node findHead(Node node) {
			Node father = fatherMap.get(node);
			if (father != node) {
				father = findHead(father);
			}
			fatherMap.put(node, father);
			return father;
		}

		/**
		 * 非递归版本
		 */
		private Node findHeadUnRecur(Node node) {
			Stack<Node> stack = new Stack<>();
			Node cur = node;
			Node parent = fatherMap.get(cur);
			while (cur != parent) {
				stack.push(cur);
				cur = parent;
				parent = fatherMap.get(cur);
			}
			while (!stack.isEmpty()) {
				fatherMap.put(stack.pop(), parent);
			}
			return parent;
		}
		
		public boolean isSameSet(Node a, Node b) {
			return findHead(a) == findHead(b);
		}

		public void union(Node a, Node b) {
			if (a == null || b == null) {
				return;
			}
			Node aHead = findHead(a);
			Node bHead = findHead(b);
			if (aHead != bHead) {
				int aSetSize= sizeMap.get(aHead);
				int bSetSize = sizeMap.get(bHead);
				if (aSetSize <= bSetSize) {
					fatherMap.put(aHead, bHead);
					sizeMap.put(bHead, aSetSize + bSetSize);
				} else {
					fatherMap.put(bHead, aHead);
					sizeMap.put(aHead, aSetSize + bSetSize);
				}
			}
		}

	}

}
