package Basics;
// * public class TreeNode {
// *     int val;
// *     TreeNode left;
// *     TreeNode right;
// *     TreeNode() {}
// *     TreeNode(int val) { this.val = val; }
// *     TreeNode(int val, TreeNode left, TreeNode right) {
// *         this.val = val;
// *         this.left = left;
// *         this.right = right;
// *     }
// * }
// */

public class DeleteinBST {

    class Solution {
        public TreeNode deleteNode(TreeNode root, int key) {

            if(root==null) return null;

            if(root.val==key) return delete(root);

            TreeNode dummy=root;
            while(root!=null){
                if(root.val>key){
                    if(root.left!=null && root.left.val==key){
                        root.left=delete(root.left);
                        break;
                    }else {
                        root=root.left;
                    }
                }else{
                    if(root.right!=null && root.right.val==key){
                        root.right=delete(root.right);
                        break;
                    }else {
                        root=root.right;
                    }
                }
            }
            return dummy;}

        TreeNode delete (TreeNode root){
            if(root.left==null) return root.right;
            if(root.right==null) return root.left;
            TreeNode rootright=root.right;
            TreeNode lastright=lastright(root.left);
            lastright.right=root.right;
            return root.left;
        }
        TreeNode lastright(TreeNode root){
            while(root.right!=null){
                root=root.right;
            }
            return root;
        }

    }
}
