package BDA;

import javax.swing.JCheckBox;
import javax.xml.bind.annotation.XmlElement;

public class Filtros {
	private boolean chckbxh;
	private boolean chckbxh_1;
	private boolean chckbxDias;
	private boolean chckbxIscteiul;
	private boolean chckbxBiblioteca;
	private boolean chckbxElearning;
	private boolean chckbxReitora;
	private boolean chckbxFenix;
	private boolean chckbxTesouraria;
	private boolean chckbxTudo;

	
    Filtros(){}
    Filtros(boolean a,boolean b,boolean c,boolean d,boolean e,boolean f,boolean g,boolean h,boolean i,boolean j){
    	this.chckbxh=a;
    	this.chckbxh_1=b;
    	this.chckbxDias=c;
    	this.chckbxIscteiul=d;
    	this.chckbxBiblioteca=e;
    	this.chckbxElearning=f;
    	this.chckbxReitora=g;
    	this.chckbxFenix=h;
    	this.chckbxTesouraria=i;
    	this.chckbxTudo=j;
    }
	public boolean getChckbxh() {
		return chckbxh;
	}
	@XmlElement
	public void setChckbxh(boolean chckbxh) {
		this.chckbxh = chckbxh;
	}
	public boolean getChckbxh_1() {
		return chckbxh_1;
	}
	@XmlElement
	public void setChckbxh_1(boolean chckbxh_1) {
		this.chckbxh_1 = chckbxh_1;
	}
	public boolean getChckbxDias() {
		return chckbxDias;
	}
	@XmlElement
	public void setChckbxDias(boolean chckbxDias) {
		this.chckbxDias = chckbxDias;
	}
	public boolean getChckbxIscteiul() {
		return chckbxIscteiul;
	}
	@XmlElement
	public void setChckbxIscteiul(boolean chckbxIscteiul) {
		this.chckbxIscteiul = chckbxIscteiul;
	}
	public boolean getChckbxBiblioteca() {
		return chckbxBiblioteca;
	}
	@XmlElement
	public void setChckbxBiblioteca(boolean chckbxBiblioteca) {
		this.chckbxBiblioteca = chckbxBiblioteca;
	}
	public boolean getChckbxElearning() {
		return chckbxElearning;
	}
	@XmlElement
	public void setChckbxElearning(boolean chckbxElearning) {
		this.chckbxElearning = chckbxElearning;
	}
	public boolean getChckbxReitora() {
		return chckbxReitora;
	}
	@XmlElement
	public void setChckbxReitora(boolean chckbxReitora) {
		this.chckbxReitora = chckbxReitora;
	}
	public boolean getChckbxFenix() {
		return chckbxFenix;
	}
	@XmlElement
	public void setChckbxFenix(boolean chckbxFenix) {
		this.chckbxFenix = chckbxFenix;
	}
	public boolean getChckbxTesouraria() {
		return chckbxTesouraria;
	}
	@XmlElement
	public void setChckbxTesouraria(boolean chckbxTesouraria) {
		this.chckbxTesouraria = chckbxTesouraria;
	}
	public boolean getChckbxTudo() {
		return chckbxTudo;
	}
	@XmlElement
	public void setChckbxTudo(boolean chckbxTudo) {
		this.chckbxTudo = chckbxTudo;
	}

   

} 