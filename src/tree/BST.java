package tree;

public class BST {
    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    TreeNode insertIntoBST(TreeNode root, int val) {
       if (root == null) {
          return  new TreeNode(val);
        }

       //已存在
       if (val == root.val) {
           return root;
       }

       //左边
       if (val < root.val) {
//           if (root.left == null) {
//               root.left = new TreeNode(val);
//           }
           root.left = insertIntoBST(root.left, val);
       }else {
           //右边
           root.right = insertIntoBST(root.right, val);
       }
       return root;

    }

    TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            //无左右节点
            if (root.left == null && root.right == null) {
                return null;
            }
            //有一个为空
            if (root.left == null) {
                return root.right;
            }else if (root.right == null){
                return root.left;
            }
            //找下右子树的最左节点
            TreeNode minNode = getMinNode(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root, minNode.val);
            return root;
        }
        if (key < root.val) {
            root.left = insertIntoBST(root.left, key);
        }else {
            root.right = insertIntoBST(root.right, key);
        }
        return root;
    }

    TreeNode getMinNode(TreeNode node){
        while (node.left != null){
            node = node.left;
        }
        return node;
    }


    public boolean isValidBST(TreeNode root) {
        return  isValidBSTFunc(root, null, null);
    }

    //左边界 minNode 右边界 maxNode
    public boolean isValidBSTFunc(TreeNode root, TreeNode minNode, TreeNode maxNode){
        if (root == null)
            return true;
        if (minNode != null && root.val <= minNode.val) {
            return false;
        }
        if (maxNode != null && root.val >= maxNode.val) {
            return false;
        }
        //左子树 左边界改变，右子树右边界改变
        return isValidBSTFunc(root.left, minNode, root) && isValidBSTFunc(root.right, root, maxNode);
    }

}
