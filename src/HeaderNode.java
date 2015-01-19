public class HeaderNode extends Node
{
    public int count;
    public String headerID;

    public HeaderNode()
    {
    	super(null, -1);
        header = this;
        count = 0;
    }
    
    public String toString() {
    	return headerID;
    }
}