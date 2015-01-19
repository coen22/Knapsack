public class HeaderNode extends Node
{
    public int count;

    public HeaderNode()
    {
    	super(null, -1);
        header = this;
        count = 0;
    }
}