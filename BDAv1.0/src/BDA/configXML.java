package BDA;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class configXML {
	@XmlElement
	private Email email;
	@XmlElement
	private Facebook facebook;
	@XmlElement
	private Twitter twitter;
	@XmlElement
	private Filtros filtros;
	
	configXML(){
		email=new Email();
		facebook=new Facebook();
		twitter =new Twitter();
		filtros=new Filtros();
	};
	
	configXML(Email email, Facebook facebook, Twitter twitter, Filtros filtros){
		this.email=email;
		this.facebook=facebook;
		this.twitter=twitter;
		this.filtros=filtros;
	}
	/**
	 * Sets email credentials in the object Email
	 * @param email String
	 * @param password String
	 */
	public void setEmail(String email, String password) {
		this.email.setEmail(email);
		this.email.setPassword(password);
	}
	/**
	 * Sets token credentials in the object Twitter
	 * @param consumerKey
	 * @param consumerKeySecret
	 * @param accessToken
	 * @param accessTokenSecret
	 */
	public void setTwitter(String consumerKey, String consumerKeySecret, String accessToken, String accessTokenSecret){
		this.twitter.setConsumerKey(consumerKey);
		this.twitter.setConsumerKeySecret(consumerKeySecret);
		this.twitter.setAccessToken(accessToken);
		this.twitter.setAccessTokenSecret(accessTokenSecret);
	}
	
	public void setFacebook(String accessToken) {
		this.facebook.setAccessToken(accessToken);
	}
	
	public void setFiltros(boolean a,boolean b,boolean c,boolean d,boolean e,boolean f,boolean g,boolean h,boolean i,boolean j){
    	this.filtros.setChckbxh(a);
    	this.filtros.setChckbxh_1(b);
    	this.filtros.setChckbxDias(c);
    	this.filtros.setChckbxIscteiul(d);
    	this.filtros.setChckbxBiblioteca(e);
    	this.filtros.setChckbxElearning(f);
    	this.filtros.setChckbxReitora(g);
    	this.filtros.setChckbxFenix(h);
    	this.filtros.setChckbxTesouraria(i);
    	this.filtros.setChckbxTudo(j);
	}
	
	public Email getEmail() {
		return email;
	}
	
	public Facebook getFacebook() {
		return facebook;
	}
	
	public Twitter getTwitter() {
		return twitter;
	}

	public Filtros getFiltros() {
		return filtros;
	}
	
}
