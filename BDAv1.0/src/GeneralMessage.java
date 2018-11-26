
public class GeneralMessage {

	public final static int TWITTER = 0;
	public final static int EMAIL   = 1;
	
	private int type;
	private String subject;
	private String body;
	private String source;
	
	public int getType() {
		return type;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getBody() {
		return body;
	}
	
	public String getSource() {
		return source;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Override
	public String toString() {
		String s;
		
		switch(type) {
		
		case GeneralMessage.TWITTER:
			s = source + ": " + body;
			break;
		
		case GeneralMessage.EMAIL:
			s = subject;
			break;
			
		default:
			s = subject;
			break;
		}
		
		return s;
	}
	
}
