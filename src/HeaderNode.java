/**
 * The Class HeaderNode.
 */
public class HeaderNode extends Node
{
    
    /** The number of nodes below */
    public int count;
    
    /** The unique header id. */
    public String headerID;

    /**
     * Instantiates a new header node.
     */
    public HeaderNode()
    {
    	super(null, -1);
        header = this;
        count = 0;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
    	return headerID;
    }
}