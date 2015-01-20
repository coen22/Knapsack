import java.util.ArrayList;

public class Search
{
    private HeaderNode startNode;
    private int thresholt;
    private ArrayList<ArrayList<Integer>> solutions = new ArrayList<ArrayList<Integer>>();
    
    public Search(int[][] matrix, double percentage)
    {
    	thresholt = (int) (matrix[0].length * percentage);
    	System.out.println(thresholt);
    	
        startNode = new HeaderNode();
        startNode.header = null;
        startNode.up = null;
        startNode.down = null;

        for (int c = 0; c < matrix[0].length; c++)
        {
        	HeaderNode header = new HeaderNode();

            header.left = startNode.left;
            header.right = startNode;
            header.left.right = header;
            header.right.left = header;
            header.headerID = "" + c;
        }

        for (int i = 0; i < matrix.length; i++)
        {
            HeaderNode header = (HeaderNode) startNode.right;
            Node last = null;

            for (int j = 0; j < matrix[i].length; j++, header = (HeaderNode) header.right)
            {
                if (matrix[i][j] == 0)
                    continue;

                Node tmp = new Node(header, i);

                if (last != null)
                {
                    tmp.left = last;
                    tmp.right = last.right;
                    tmp.left.right = tmp;
                    tmp.right.left = tmp;
                }
                tmp.up = header.up;
                tmp.down = header;
                tmp.up.down = tmp;
                tmp.down.up = tmp;

                header.count++;
                last = tmp;
            }
        }
    }
    
    public ArrayList<ArrayList<Integer>> search()
    {    	
    	ArrayList<Integer> solution = new ArrayList<Integer>();
    	
        search(solution);

        return solutions;
    }
    
    private boolean search(ArrayList<Integer> solution)
    {
    	int size = 0;
    	
    	for (HeaderNode tmp = (HeaderNode) startNode.right; tmp != startNode; tmp = (HeaderNode) tmp.right) {
    		size++;
    	}
    	
        if (size <= thresholt) {
        	ArrayList<Integer> valid = new ArrayList<Integer>();
        	valid.addAll(solution);
        	solutions.add(valid);
            return true;
        }

        HeaderNode col = getNextColumn();
          cover(col);
          
          Node row = col.down;
          
          while (row != col) {
        	  solution.add(row.rowID);
        	  Node node = row.right;
        	  
        	  while (node != row) {
        		  cover(node);
        		  node = node.right;
        	  }
        	  
        	  if (search(solution))
        		  return true;
            
        	  solution.remove(solution.size() - 1);
            
        	  node = row.left;
            
        	  while (node != row) {
        		  uncover(node);
        		  node = node.left;
        	  }
            
        	  row = row.down;
          }
          
          uncover(col);
          
          System.out.println(solution);
          
          return false;
	}
    
    private HeaderNode getNextColumn()
    {
        HeaderNode header = null;
        int min = 0;

        for (HeaderNode tmp = (HeaderNode) startNode.right; tmp != startNode; tmp = (HeaderNode) tmp.right)
        {
            if (min > tmp.count || header == null)
            {
                min = tmp.count;
                header = tmp;
            }
        }

        return header;
    }
    
    private void printheaders(String t, int n)
    {
        int i=0;
        System.out.print(t);
        for (HeaderNode tmp = (HeaderNode) startNode.right; tmp != startNode; tmp = (HeaderNode) tmp.right)
        {
        	i++;
        	System.out.print(tmp.headerID+" ");
        }
        System.out.println("");
    }

    private void cover(Node node)
    {
    	// System.out.println("==== cover: from row "+node.rowID+" removing column "+node.header.headerID);

        HeaderNode header = node.header;

        // printheaders("BEFO:", 5);
        header.left.right = header.right;
        header.right.left = header.left;
        // printheaders("AFTR:", 5);
        
        Node row = header.down;
        
        while (row != header)
        {
            Node tmp = row.right;
            while (tmp != row) {
                tmp.header.count--;
                tmp.up.down = tmp.down;
                tmp.down.up = tmp.up;
                tmp = tmp.right;
            }
            row = row.down;
        }
        //printheaders("After all:", 5);
    }

    private void uncover(Node node)
    {
    	// System.out.println("==== UNcover:"+node.header.headerID);
        HeaderNode header = node.header;

        Node row = header.up;
        
        while (row != header)
        {
        	Node tmp = row.left;
        	while (tmp != row)
        	{
        		tmp.header.count++;
        		tmp.up.down = tmp;
                tmp.down.up = tmp;
                tmp = tmp.left;
            }
        	row = row.up;
        }

        header.left.right = header;
        header.right.left = header;
    }
}
