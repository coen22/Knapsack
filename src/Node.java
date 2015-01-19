public class Node
{
    public HeaderNode header;
    public Node left;
    public Node right;
    public Node up;
    public Node down;
    public int rowID;

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