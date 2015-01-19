import java.util.ArrayList;

public class Search
{
    private HeaderNode startNode;
    private int thresholt;
    
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
    
    public ArrayList<Integer> search()
    {    	
    	ArrayList<Integer> solution = new ArrayList<Integer>();

        search(solution, 0);

        return solution;
    }
    
    private boolean search(ArrayList<Integer> solution, int depth)
    {
    	int size = 0;
    	
    	for (HeaderNode tmp = (HeaderNode) startNode.right; tmp != startNode; tmp = (HeaderNode) tmp.right) {
    		size++;
    	}
    	
        if (size <= thresholt) {
            return true;
        }

        printheaders("getnextcolumn:", 10);
        HeaderNode header = getNextColumn();
        
        if( header.headerID.equals("1"))
        {
        	int qq = 2;
        }
        
        
        System.out.println("");
        String spaces = "";
        // for(int i=0; i<depth; i++)
        // 	spaces+="  ";
        System.out.println(spaces+solution);
		System.out.println(spaces+header + ", " + size);
        
		if(header.down.rowID == 1)
    	{
    	   int x = 2;
    	   HeaderNode head = getNextColumn();
    	}
		
        cover(header);
        System.out.println("Cover: "+header.headerID);
        for (Node node = header.down; node != header; node = node.down)
        {
        	if(node.rowID == 1)
        	{
        	   int x = 2;
        	}
            solution.add(node.rowID);
            
           for (Node tmp = node.right; tmp != node; tmp = tmp.right)
                cover(tmp);

            if (search(solution, depth+1))
                return true;

            solution.remove(solution.size() - 1);

            for (Node tmp = node.right; tmp != node; tmp = tmp.right)
                uncover(tmp);
        }
        System.out.println("UNCover: "+header.headerID);
        uncover(header);

        return false;
    }
    
    private HeaderNode getNextColumn()
    {
        HeaderNode header = null;
        int min = Integer.MAX_VALUE;

        for (HeaderNode tmp = (HeaderNode) startNode.right; tmp != startNode; tmp = (HeaderNode) tmp.right)
        {
            if (min > tmp.count)
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
        for (HeaderNode tmp = (HeaderNode) startNode.right; i<n && tmp != startNode; tmp = (HeaderNode) tmp.right)
        {
        	i++;
        	System.out.print(tmp.headerID+" ");
        }
        System.out.println("");
    }

    private void cover(Node node)
    {
    	System.out.println("==== cover: from row "+node.rowID+" removing column "+node.header.headerID);
    	if( node.rowID==123)
    	{
    	   int x = 2;
    	}
        HeaderNode header = node.header;

        printheaders("before:", 5);
        header.left.right = header.right;
        header.right.left = header.left;
        printheaders("After:", 5);
        
        for (Node row = header.down; row != header; row = row.down)
        {
        	System.out.println("         removing row "+row.rowID);
//            for (Node tmp = row.right; tmp != row; tmp = tmp.right)
            for (Node tmp = row; tmp != row; tmp = tmp.right)
            {
                tmp.header.count--;
                tmp.up.down = tmp.down;
                tmp.down.up = tmp.up;
            }
        }
        //printheaders("After all:", 5);
    }

    private void uncover(Node node)
    {
    	System.out.println("==== UNcover:"+node.header.headerID);
        HeaderNode header = node.header;

        for (Node row = header.up; row != header; row = row.up)
        {
//            for (Node tmp = row.left; tmp != row; tmp = tmp.left)
            for (Node tmp = row; tmp != row; tmp = tmp.left)
            {
                tmp.header.count++;
                tmp.up.down = tmp;
                tmp.down.up = tmp;
            }
        }

        header.left.right = header;
        header.right.left = header;
    }
}
