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

        search(solution);

        return solution;
    }
    
    private boolean search(ArrayList<Integer> solution)
    {
    	int size = 0;
    	
    	for (HeaderNode tmp = (HeaderNode) startNode.right; tmp != startNode; tmp = (HeaderNode) tmp.right) {
    		size++;
    	}
    	
        if (size <= thresholt) {
            return true;
        }

        HeaderNode header = getNextColumn();

        cover(header);
        for (Node node = header.down; node != header; node = node.down)
        {
            solution.add(node.rowID);
            for (Node tmp = node.right; tmp != node; tmp = tmp.right)
                cover(tmp);

            if (search(solution))
                return true;

            solution.remove(solution.size() - 1);
            for (Node tmp = node.right; tmp != node; tmp = tmp.right)
                uncover(tmp);
        }
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

    private void cover(Node node)
    {
        HeaderNode header = node.header;

        header.left.right = header.right;
        header.right.left = header.left;

        for (Node row = header.down; row != header; row = row.down)
        {
            for (Node tmp = row.right; tmp != row; tmp = tmp.right)
            {
                tmp.header.count--;
                tmp.up.down = tmp.down;
                tmp.down.up = tmp.up;
            }
        }
    }

    private void uncover(Node node)
    {
        HeaderNode header = node.header;

        for (Node row = header.up; row != header; row = row.up)
        {
            for (Node tmp = row.left; tmp != row; tmp = tmp.left)
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