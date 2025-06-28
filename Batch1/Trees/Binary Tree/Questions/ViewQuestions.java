class Node
{
    int data;
    Node left, right;

    Node(int item)
    {
        data = item;
        left = right = null;
    }
}

class ViewQuestions {
    // Width of binary Tree
    public void findWidth(Node root, int VerticalLevel, int[] maxMin){
        if(root == null){
            return;
        }

        maxMin[0] = Math.max(maxMin[0], VerticalLevel);
        maxMin[1] = Math.min(maxMin[1], VerticalLevel);

        findWidth(root.left, VerticalLevel - 1, maxMin);
        findWidth(root.right, VerticalLevel + 1, maxMin);
    }

    public int findWidth(Node root){
        int[] maxMin = new int[2];

        findWidth(root, 0, maxMin);

        int widthOfTree = maxMin[0] - (maxMin[1]) + 1;

        return widthOfTree;
    }


    // left View (https://www.geeksforgeeks.org/problems/left-view-of-binary-tree/1)
    ArrayList<Integer> leftView(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }
        
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);

        while(queue.size() > 0){
            int size = queue.size();
            ans.add(queue.getFirst().data); // adding first element of every level to answer

            while(size -- > 0){
                Node currNode = queue.removeFirst();

                if(currNode.left != null){
                    queue.addLast(currNode.left);
                }

                if(currNode.right != null){
                    queue.addLast(currNode.right);
                }
            }
        }

        return ans;
    }

    // right View (Leetcode 199)
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        
        LinkedList<TreeNode> que = new LinkedList<>();
        if(root != null)
            que.addLast(root);

        while(que.size() > 0){
            int size = que.size();
            TreeNode prev = null;

            while(size -- > 0){
                TreeNode currNode = que.removeFirst();

                if(currNode.left != null){
                    que.addLast(currNode.left);
                }

                if(currNode.right != null){
                    que.addLast(currNode.right);
                }

                prev = currNode;
            }

            ans.add(prev.val);
        }

        return ans;
    }

    // Vertical Order (https://www.geeksforgeeks.org/problems/print-a-binary-tree-in-vertical-order/1) ===================================
    class Pair {
        Node node;
        int verticalLevel;

        public Pair(Node node, int verticalLevel){
            this.node = node;
            this.verticalLevel = verticalLevel;
        }
    }

    public ArrayList<ArrayList<Integer>> verticalOrder(Node root) {
        int[] maxMin = new int[2];

        findWidth(root, 0, maxMin);
        
        int shift = -maxMin[1]; // -min is shift
        int widthOfTree = maxMin[0] - maxMin[1] + 1;

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        for(int i=0; i<widthOfTree; i++){
            ans.add(new ArrayList<>());
        }

        LinkedList<Pair> que = new LinkedList<>();
        que.addLast(new Pair(root, 0));

        while(que.size() > 0){
            int size = que.size();

            while(size -- > 0){
                Pair currPair = que.removeFirst();
                Node currNode = currPair.node;
                int currLevel = currPair.verticalLevel;

                ans.get(currLevel + shift).add(currNode.data);

                if(currNode.left != null){
                    que.addLast(new Pair(currNode.left, currLevel - 1));
                }

                if(currNode.right != null){
                    que.addLast(new Pair(currNode.right, currLevel + 1));
                }
            }
        }

        return ans;
    }
}