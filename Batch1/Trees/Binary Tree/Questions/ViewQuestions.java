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

    // Vertical Sum (http://geeksforgeeks.org/problems/vertical-sum/1) ==========================================
    public ArrayList<Integer> verticalSum(Node root) {
        int[] maxMin = new int[2];

        findWidth(root, 0, maxMin);
        
        int shift = -maxMin[1]; // -min is shift
        int widthOfTree = maxMin[0] - maxMin[1] + 1;

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=0; i<widthOfTree; i++){
            ans.add(0);
        }

        LinkedList<Pair> que = new LinkedList<>();
        que.addLast(new Pair(root, shift));

        while(que.size() > 0){
            int size = que.size();

            while(size -- > 0){
                Pair currPair = que.removeFirst();
                Node currNode = currPair.node;
                int currLevel = currPair.verticalLevel;

                ans.set(currLevel, ans.get(currLevel) +  currNode.data);

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

    // Bottom view of Binary Tree(https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1) ====================================
    public ArrayList<Integer> bottomView(Node root) {
        int[] maxMin = new int[2];

        findWidth(root, 0, maxMin);
        
        int shift = -maxMin[1]; // -min is shift
        int widthOfTree = maxMin[0] - maxMin[1] + 1;

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=0; i<widthOfTree; i++){
            ans.add(0);
        }

        LinkedList<Pair> que = new LinkedList<>();
        que.addLast(new Pair(root, shift));

        while(que.size() > 0){
            int size = que.size();

            while(size -- > 0){
                Pair currPair = que.removeFirst();
                Node currNode = currPair.node;
                int currLevel = currPair.verticalLevel;

                ans.set(currLevel, currNode.data);

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

    // Top View of Binary Tree (https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1) ===================
    static ArrayList<Integer> topView(Node root) {
        int[] maxMin = new int[2];

        findWidth(root, 0, maxMin);
        
        int shift = -maxMin[1]; // -min is shift
        int widthOfTree = maxMin[0] - maxMin[1] + 1;

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=0; i<widthOfTree; i++){
            ans.add(null);
        }

        LinkedList<Pair> que = new LinkedList<>();
        que.addLast(new Pair(root, shift));

        while(que.size() > 0){
            int size = que.size();

            while(size -- > 0){
                Pair currPair = que.removeFirst();
                Node currNode = currPair.node;
                int currLevel = currPair.verticalLevel;

                if(ans.get(currLevel) == null){
                    ans.set(currLevel, currNode.data);
                }
                
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

    // Diagonal View  =====================
    public void findDiagonalWidth(Node root, int[] min, int diagLevel){
        if(root == null) return;

        min[0] = Math.min(min[0], diagLevel);

        findDiagonalWidth(root.left, min, diagLevel - 1);
        findDiagonalWidth(root.right, min, diagLevel);
    }

    // https://www.geeksforgeeks.org/problems/diagonal-traversal-of-binary-tree/1
    public void traverseDiagonally(Node root, ArrayList<ArrayList<Integer>> ans, int diagLevel){
        if(root == null) return;

        ans.get(diagLevel).add(root.data);

        traverseDiagonally(root.left, ans, diagLevel - 1);
        traverseDiagonally(root.right, ans, diagLevel);
    }

    public ArrayList<Integer> diagonal(Node root) {
        int[] min = new int[1];
        findDiagonalWidth(root,min,0);
        int diagonalWidth = -(min[0]) + 1;

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int i=0; i<diagonalWidth; i++){
            ans.add(new ArrayList<>());
        }

        traverseDiagonally(root,ans,-min[0]);

        // converting 2d arraylist to 1d
        ArrayList<Integer> res = new ArrayList<>();
        for(int i=ans.size()-1; i>=0; i--){
            for(int e: ans.get(i)){
                res.add(e);
            }
        }

        return res;
    }
}