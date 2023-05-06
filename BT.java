package Basics;

import java.util.*;





public class BT<T> {
	
    T data;
	BT<T> left;
	BT<T> right;
	private Object root;
	

	BT(T data){
	this.data=data;	
	}
	
	static ArrayList <BT<Integer>> path=new ArrayList<>();
	
	
	public static BT<Integer> takeInputLevelWise() {		 
		

		Scanner s = new Scanner(System.in);
		QueueUsingLL<BT<Integer>> pendingNodes = new QueueUsingLL<>();
		System.out.println("Enter root data");
		int rootData = s.nextInt();
		if (rootData == -1) {
			return null;
		}
		BT<Integer> root = new BT<Integer>(rootData);
		pendingNodes.enqueue(root);
		
		while (!pendingNodes.isEmpty()) {
			BT<Integer> front;
			try {
				front = pendingNodes.dequeue();
			} catch (QueueEmptyException e) {
				return null;
			}
			System.out.println("Enter left child of " + front.data);
			int leftChild = s.nextInt();
			if (leftChild != -1) {
				BT<Integer> child = new BT<Integer>(leftChild);
				pendingNodes.enqueue(child);
				front.left = child;
			}
			
			System.out.println("Enter right child of " + front.data);
			int rightChild = s.nextInt();
			if (rightChild != -1) {
				BT<Integer> child = new BT<Integer>(rightChild);
				pendingNodes.enqueue(child);
				front.right = child;
			}
		}
		return root;
	}		
	
	public static boolean searchInBST(BT<Integer> root,int k) {
	
		
		if (root == null) {
			return false;
		}
      if(root.data==k){return true;}

	  if(root.data<k){return searchInBST(root.right, k);}
	  return searchInBST(root.left, k);			
	}
	
    public static void  printLevelwise(BT<Integer> root){
		  
		 if(root == null){
			 return;
		 }
		 Queue<BT<Integer>> pendingNode=new LinkedList<>();
		 pendingNode.add(root);    
		 while(!pendingNode.isEmpty()){
			    BT<Integer> front= pendingNode.poll();

				if(front!=null){
					System.out.print(front.data+":");
			    if(front.left==null){
					System.out.print("L:-1");
				}
				else{
					System.out.print("L:"+front.left.data);
				}
				 if(front.right==null){
					System.out.print(",R:-1");
				}
				else{
					System.out.print(",R:"+front.right.data);
				}
				System.out.println();
			    pendingNode.add(front.left);
				pendingNode.add(front.right);
				}

				}
		 }
	
	public static BT<Integer> SortedArrayToBST(int[] arr, int n){
		
		return convertans(arr,0,n-1);
	}

	public static BT<Integer> convertans(int[]a,int si,int ei){

   if(si>ei){return null;}
   
   int mid=si+(ei-si)/2;
   BT<Integer> root=new BT<>(a[mid]);
   root.left=convertans(a,si,mid-1);
   root.right=convertans(a,mid+1,ei);
   return root;

	}	

	public static void preOrder(BT<Integer> root) {

		if (root == null) {
			return;
		}
		System.out.print(root.data + " ");
		preOrder(root.left);
		preOrder(root.right);
	
	}
	
	public static boolean isBST(BT<Integer> root) {
	
	if(root==null){
	return true;	
	}
	
	int leftmax=max(root.left);
	int rightmin=min(root.right);
	
	if(root.data<=leftmax){return false;}
	if(root.data>=rightmin){return false;}
	
	boolean isLeftBst=isBST(root.left);
	boolean isRightBst=isBST(root.right);
	
	if(isLeftBst && isRightBst){return true;}
	else{return false;}
	 
	}
	 
	public static int max (BT<Integer> root) {
	if(root==null) {return -1;} 	 
	return Math.max(root.data,Math.max(max(root.left),max(root.right)));	
	}	
	
	public static int min (BT<Integer> root) {
	if(root==null) {return Integer.MAX_VALUE;} 	 
	return Math.min(root.data,Math.min(min(root.left),min(root.right)));	
	}	
	
	public static pair<Boolean,pair<Integer,Integer>> isBST2(BT<Integer> root){
	
	if(root==null){
		pair<Boolean,pair<Integer,Integer>> ans=new pair<Boolean,pair<Integer,Integer>>();
		ans.first=true;
		ans.second=new pair<>();
		ans.second.first=Integer.MAX_VALUE;
		ans.second.second=Integer.MIN_VALUE;
		return ans;
	}
	pair<Boolean,pair<Integer,Integer>> leftans=isBST2(root.left);
	pair<Boolean,pair<Integer,Integer>> rightans=isBST2(root.right);
	
	int min=Math.min(root.data,Math.min(leftans.second.first,rightans.second.first));
	int max=Math.max(root.data,Math.max(leftans.second.first,rightans.second.first));
	boolean isBST=(root.data>leftans.second.second)&& 
			(root.data<=rightans.second.first)&& 
			leftans.first && rightans.first; 
	
	pair<Boolean,pair<Integer,Integer>> ans=new pair<Boolean,pair<Integer,Integer>>();
	ans.first=isBST;
	ans.second=new pair<>();
	ans.second.first=min;
	ans.second.second=max;
	return ans;
}
	
	
	
	
    public static ArrayList<Integer> getRootToNodePath(BT<Integer> root, int data) {
		if (root == null) {
			return null;
		}
		
		if (root.data == data){
			ArrayList<Integer> output = new ArrayList<>();
			output.add(root.data);
			return output;
		}
		
		ArrayList<Integer> leftOutput = getRootToNodePath(root.left, data);
		if (leftOutput != null) {
			leftOutput.add(root.data);
			return leftOutput;
		}
		
		ArrayList<Integer> rightOutput = getRootToNodePath(root.right, data);
		if (rightOutput != null) {
			rightOutput.add(root.data);
			return rightOutput;
		} else {
			return null;
		}
		
	}
    
    public static ArrayList<Integer> RootToNodePath(BT<Integer> root, int data) {
		if (root == null) {
			return null;
		}
		ArrayList<Integer> output = new ArrayList<>();
		output.add(root.data);
		if(root.data == data){			
		   return output;
		}
		
		ArrayList<Integer> leftOutput = getRootToNodePath(root.left,data);
		if (leftOutput != null) {
			leftOutput.add(root.data);
			return leftOutput;
		}
		
		ArrayList<Integer> rightOutput = getRootToNodePath(root.right, data);
		if (rightOutput != null) {
			rightOutput.add(root.data);
			return rightOutput;
		} else {
			return null;
		}		
	}
    public static BT<Integer> insertIntoBST(BT<Integer>  root, int val) {
        if(root==null){
            return new BT<Integer> (val);
        } 
        BT<Integer> cur=root;
        while(true){
            if(cur.data<=val){
          if(cur.right!=null){
           cur=cur.right;
                }
          else{
                    cur.right=new BT<Integer> (val);
                    break;
                }
            }     
           else{ 
           if(cur.left!=null){
                    cur=cur.left;
                }else{
                    cur.left=new BT<Integer> (val);
                    break;
                }
            }
        
        }
    return root;
      }
      

    public static void printNodesSumToS(BT<Integer> root, int s) {
		
    	Stack<BT<Integer>>	s1=new Stack<>();
    	Stack<BT<Integer>>	s2=new Stack<>();

    	BT<Integer> temp=root;
    	
    	while(temp!=null){
    	s1.push(temp);	
        temp=temp.left;		
    	}

        temp=root;
    	
    	while(temp!=null){
    	s2.push(temp);	
        temp=temp.right;		
    	}

    	

        while(!s1.isEmpty() && !s2.isEmpty() && (s1.peek().data<s2.peek().data)){

         BT<Integer> top1=s1.peek();
    	 BT<Integer> top2=s2.peek();
    	  
         if(top1.data+top2.data==s){
            System.out.println(top1.data +" " + top2.data );
    		s1.pop();
    		s2.pop();     
           
    	   top1=top1.right;
    	   while(top1!=null){
    		       s1.push(top1);
    			   top1=top1.left;
    	   }
    	    
    	   top2=top2.left;
    	   while(top2!=null){
    		       s2.push(top2);
    			   top2=top2.right;
    	    }
    	 }

    	 else if(top1.data+top2.data>s){		  
    	          s2.pop();	   
    			  top2=top2.left;
                    while(top2!=null){
    		       s2.push(top2);
    			   top2=top2.right;
    	    }

    	 }
         
    	 else{
    		 s1.pop();
    		    top1=top1.right;
    	   while(top1!=null){
    		       s1.push(top1);
    			   top1=top1.left;
    	   }

    	 }

        }
    	 } 
    
	public static void printatDepth(BT<Integer>root,int k,BT<Integer> blocker) {	
		
	if(root==null || root==blocker){
		return ;
	}
	
	if(k==0){
	System.out.println(root.data+ "");
	}
	
	printatDepth(root.left,k-1,blocker);
	printatDepth(root.right,k-1,blocker);
	return;		
	}
	
	
	public static boolean nodeToroot(BT<Integer>root,int k) {
		
		if(root==null){return false;}	
		
		
		if(root.data==k){
		path.add(root); 
		return true;
		}
		
		if(nodeToroot(root.left,k)||nodeToroot(root.right,k)){
		   path.add(root);
		   return true;
		}
		return false;	
		}
		
   
 public static void printatdistancek(BT<Integer>root,BT<Integer> target,int k) {
    
    path=new ArrayList<>();
    
    nodeToroot(root,target.data);
    
    for(int i=0;i<path.size();i++) {
    	printatDepth(path.get(i),k-i,i==0?null:path.get(i-1));
    	
    }
    
    }
 
    public static int pairs(BT<Integer> root,int s) {
    	Stack<BT<Integer>> s1=new Stack<>();
    	Stack<BT<Integer>> s2=new Stack<>();

    	// BinaryTreeNode<Integer> temp=root;
    	// while(temp!=null){
    	// 	s1.push(temp.data);
    	// 	root=root.left

       BT<Integer> temp=root;
    	
    	while(temp!=null){
    	s1.push(temp);	
        temp=temp.left;		
    	}

        temp=root;
    	
    	while(temp!=null){
    	s2.push(temp);	
        temp=temp.right;		
    	}

    	
        int count=0;
        while(!s1.isEmpty() && !s2.isEmpty() && (s1.peek().data<s2.peek().data)){

         BT<Integer> top1=s1.peek();
    	 BT<Integer> top2=s2.peek();
    	  
         if(top1.data+top2.data==s){
            System.out.println(top1.data +" " + top2.data );
    		count++;
        	 s1.pop();
    		s2.pop();     
           
    	   top1=top1.right;
    	   while(top1!=null){
    		       s1.push(top1);
    			   top1=top1.left;
    	   }
    	    
    	   top2=top2.left;
    	   while(top2!=null){
    		       s2.push(top2);
    			   top2=top2.right;
    	    }
    	 }

    	 else if(top1.data+top2.data>s){		  
    	          s2.pop();	   
    			  top2=top2.left;
                    while(top2!=null){
    		       s2.push(top2);
    			   top2=top2.right;
    	    }

    	 }
         
    	 else{
    		 s1.pop();
    		    top1=top1.right;
    	   while(top1!=null){
    		       s1.push(top1);
    			   top1=top1.left;
    	   }

    	 }}
        return count;
    }
     
	public static void main(String[] args) {

    BT <Integer>root=takeInputLevelWise();
//    System.out.println(searchInBST(root,3));

//sorted array into tree
//    int  a[]= {1,2,3,4,5,6,7};
//    int len=a.length;
//    BT <Integer>root=SortedArrayToBST(a,len);
//    preOrder(root);
    
//isBST    
//	boolean ans=isBST(root);
//	System.out.println(ans);
	

//isBSTbetter	
//	int m=Integer.MIN_VALUE;
//	int n=Integer.MAX_VALUE;
//	boolean ans=isBST21(root,m,n);

//roottonodepath
//	ArrayList<Integer> a=new ArrayList<>();

//	System.out.println(a);
//	}	

//insertion
//    int val=5; 
//    root=insertIntoBST(root,val);
//    printLevelwise(root);
  
//int s=25;
//printNodesSumToS(root,s);

//printNodesatDistanceK
    
//    int k=2;
//    BT<Integer> target=new BT<Integer>(2);
//    printatdistancek(root,target,k);
 
//pairs sum in BST
    int k=7;
    int ans=pairs(root,k);
    System.out.println(ans);
    
    
    
    
	}}
