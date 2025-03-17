## Problem1 (https://leetcode.com/problems/flood-fill/)

You are given an image represented by an m x n grid of integers image, where image[i][j] represents the pixel value of the image. You are also given three integers sr, sc, and color. Your task is to perform a flood fill on the image starting from the pixel image[sr][sc].

To perform a flood fill:

Begin with the starting pixel and change its color to color.
Perform the same process for each pixel that is directly adjacent (pixels that share a side with the original pixel, either horizontally or vertically) and shares the same color as the starting pixel.
Keep repeating this process by checking neighboring pixels of the updated pixels and modifying their color if it matches the original color of the starting pixel.
The process stops when there are no more adjacent pixels of the original color to update.
Return the modified image after performing the flood fill.

// Approach :  In this we are startiing with the given image[sr][sc] and then we are doing an BFS and storing that in the queue.
// Here we are not maintaining a single queue of int[] instead we are maintaining two queues one for row and one for column
// Here we don't need to maintain a level so we will not need a size variable. We will poll the element and see if the adjacent 4 elements have same value of oldcolor if so we will add it in queue and continue the process until the queue is empty
// Time Complexity : O(m*n)
// Space Complexity : O(m*n)



class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        //Base condition
        if(image == null || image.length==0 || image[sr][sc]==color){
            return image;
        }
        int m=image.length;
        int n=image[0].length;
        //Two queuees one for storing row other for storing column 
        Queue<Integer> rows = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();
        int oldColor=image[sr][sc];
        image[sr][sc]=color;
        //dirs array 
        int [][] dirs ={{-1,0},{1,0},{0,-1},{0,1}};
        rows.add(sr);
        cols.add(sc);
        while(!rows.isEmpty()){
            int currRow=rows.poll();
            int currCol=cols.poll();
            for(int[] dir:dirs){
                    int nr = currRow+dir[0];
                    int nc = currCol+dir[1];
                    //checking if newly calculated row column are valid and equal to coldcolor    
                    if(nr>=0 && nr<m && nc>=0 && nc<n && image[nr][nc]==oldColor){
                        image[nr][nc]=color;
                        rows.add(nr);
                        cols.add(nc);
                    } 
                }

        }
        return image;

    }
}

//DFS based solution: This can also be solved by DFS approach where we will start by the given element
// Approach : In this we will start from the given node change it's color and push it in the stack then we will go yto it's up and check 
// if valid and has color as oldcolor then we will change it's color and push it in stack. We will continue doing this until all valid 
// nodes are done

// Time Complexity : O(m*n) -> worst case all elements will be of old color
// Space Complexity : O(m*n) -> Stack space

