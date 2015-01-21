// TODO: Auto-generated Javadoc
/**
 * The Class Node.
 */
public class Node
{
    
    /** The header. */
    public HeaderNode header;
    
    /** The left node. */
    public Node left;
    
    /** The right node. */
    public Node right;
    
    /** The node above. */
    public Node up;
    
    /** The node bellow. */
    public Node down;
    
    /** The row id. */
    public int rowID;

    /**
     * Instantiates a new node.
     *
     * @param header the header
     * @param row the row
     */
    public Node(HeaderNode header, int row)
    {
        this.header = header;
        left = this;
        right = this;
        up = this;
        down = this;
        rowID = row;
    }
}