## Problem2 (https://leetcode.com/problems/01-matrix/)
Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two cells sharing a common edge is 1.

Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]

//Approach :
Here we will go with BFS traversal in which we will keep 0's at level 0 and add them in a queue 
Now why 0's in a queue - because those are independent nodes and 1 are dependent. Also we will mark all 1's as -1 so that instead of 
maintaining a new array for keeping track we can just keep track by changinhg visited node's value to +ve value
We will also maintain a level variable so that we can assign  the distance to non zero values. 

Time Complexity : O(m*n)
Space Complexity : O(m*n)

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        //base condition
        if(mat.length==0 || mat==null){
            return mat;
        }
        //Queue 
        Queue<int[]> q=new LinkedList<>();
        int m=mat.length;
        int n=mat[0].length;
        int level=0;
        //dirs array to li=ook for adjacent elements
        int [][] dirs ={{-1,0},{1,0},{0,-1},{0,1}};
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(mat[i][j]==0){
                    //Adding all independent elements i.e 0's
                    q.add(new int[]{i,j});
                }
               else{
                    //marking 1's as -1 to keep track of visited elements
                    mat[i][j]=-1;
                }
            } 
        }
        //size variable to keep track of levels
        int size=0;
        while(!q.isEmpty()){
            size = q.size();
            for(int i=0;i<size;i++){
                int[] curr=q.poll();
                for(int[] dir:dirs){
                    int nr = curr[0]+dir[0];
                    int nc = curr[1]+dir[1];

                    if(nr>=0 && nr<m && nc>=0 && nc<n && mat[nr][nc]==-1){
                        q.add(new int[]{nr,nc});
                        // mat[nr][nc]=mat[nr][nc] * -1;
                        mat[nr][nc] = level+1;
                    } 
                }
            }
            //at the end doing level + 1 
            level++;
        }
        return mat;
    }
}


