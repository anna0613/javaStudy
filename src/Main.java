import java.util.Stack;

class ListNode {
       int val;
       ListNode next = null;
       ListNode(int val) {
           this.val = val;
       }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Main {

    public static TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        TreeNode root=reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
        return root;
    }
    //前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
    private static TreeNode reConstructBinaryTree(int [] pre,int startPre,int endPre,int [] in,int startIn,int endIn) {

        if(startPre>endPre||startIn>endIn)
            return null;
        TreeNode root=new TreeNode(pre[startPre]);

        for(int i=startIn;i<=endIn;i++)
            if(in[i]==pre[startPre]){
                root.left=reConstructBinaryTree(pre,startPre+1,startPre+i-startIn,in,startIn,i-1);
                root.right=reConstructBinaryTree(pre,i-startIn+startPre+1,endPre,in,i+1,endIn);
            }

        return root;
    }

    public static void recursionPreOrder(TreeNode treeNode){
        if(treeNode != null){
//                System.out.print(treeNode.val + " ");
            recursionPreOrder(treeNode.left);
//                System.out.print(treeNode.val + " ");
            recursionPreOrder(treeNode.right);
            System.out.print(treeNode.val + " ");
        }
    }

    public static String preOrder(TreeNode treeNode){
        /*if(treeNode != null){
//                System.out.print(treeNode.val + " ");
            postOrder(treeNode.left);
//                System.out.print(treeNode.val + " ");
            postOrder(treeNode.right);
            System.out.print(treeNode.val + " ");
        }*/
        StringBuffer sb = new StringBuffer();
        Stack<TreeNode> s = new Stack<TreeNode>();
        while (treeNode != null || !s.empty()) {
            while (treeNode != null) {
//                    System.out.print(treeNode.val);
                sb.append(treeNode.val);
                s.push(treeNode);
                treeNode = treeNode.left;
            }
            if (!s.empty()) {
                treeNode = s.pop();
                treeNode = treeNode.right;
            }
        }
        return sb.toString();
    }

    public static String midOrder(TreeNode treeNode){
        StringBuffer sb = new StringBuffer();
        Stack<TreeNode> s = new Stack<TreeNode>();
        while (treeNode != null || !s.empty()) {
            while (treeNode != null) {
                s.push(treeNode);
                treeNode = treeNode.left;
            }
            if (!s.empty()) {
                treeNode = s.pop();
                sb.append(treeNode.val);
                treeNode = treeNode.right;
            }
        }
        return sb.toString();
    }

    public static String postOrder(TreeNode treeNode){
        StringBuffer sb = new StringBuffer();
        Stack<TreeNode> s = new Stack<TreeNode>();
        while (treeNode != null || !s.empty()) {
            while (treeNode != null) {
                s.push(treeNode);
                treeNode = treeNode.left;
            }
            if (!s.empty()) {
                treeNode = s.pop();
                sb.append(treeNode.val);
                treeNode = treeNode.right;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] mid = {4,7,2,1,5,3,8,6};
        System.out.print(preOrder(reConstructBinaryTree(pre,mid)));//非递归 前序
        System.out.print(midOrder(reConstructBinaryTree(pre,mid)));//非递归 中序
        recursionPreOrder(reConstructBinaryTree(pre,mid));
//        int[] result = postOrder(reConstructBinaryTree(pre,mid));
//        for(int i = 0;i <result.length;i++)
//            System.out.print(result[i]);
//        System.out.print(postOrder(reConstructBinaryTree(pre,mid)).length);
    }
}
