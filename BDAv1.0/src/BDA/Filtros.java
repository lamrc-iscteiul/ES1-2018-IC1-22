package BDA;

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

	/**
	 * Filters constructor
	 */
	Filtros() {
	}

	/**
	 * Filters constructor
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @param f
	 * @param g
	 * @param h
	 * @param i
	 * @param j
	 */
	Filtros(boolean a, boolean b, boolean c, boolean d, boolean e, boolean f, boolean g, boolean h, boolean i,
			boolean j) {
		this.chckbxh = a;
		this.chckbxh_1 = b;
		this.chckbxDias = c;
		this.chckbxIscteiul = d;
		this.chckbxBiblioteca = e;
		this.chckbxElearning = f;
		this.chckbxReitora = g;
		this.chckbxFenix = h;
		this.chckbxTesouraria = i;
		this.chckbxTudo = j;
	}

	/**
	 * Gets checkbox
	 * 
	 * @return
	 */
	public boolean getChckbxh() {
		return chckbxh;
	}

	/**
	 * Sets checkbox
	 */
	@XmlElement
	public void setChckbxh(boolean chckbxh) {
		this.chckbxh = chckbxh;
	}

	/**
	 * Gets checkbox
	 * 
	 * @return
	 */
	public boolean getChckbxh_1() {
		return chckbxh_1;
	}

	/**
	 * Sets checkbox
	 */
	@XmlElement
	public void setChckbxh_1(boolean chckbxh_1) {
		this.chckbxh_1 = chckbxh_1;
	}

	/**
	 * Gets checkbox
	 * 
	 * @return
	 */
	public boolean getChckbxDias() {
		return chckbxDias;
	}

	/**
	 * Sets checkbox
	 */
	@XmlElement
	public void setChckbxDias(boolean chckbxDias) {
		this.chckbxDias = chckbxDias;
	}

	/**
	 * Gets checkbox
	 * 
	 * @return
	 */
	public boolean getChckbxIscteiul() {
		return chckbxIscteiul;
	}

	/**
	 * Sets checkbox
	 */
	@XmlElement
	public void setChckbxIscteiul(boolean chckbxIscteiul) {
		this.chckbxIscteiul = chckbxIscteiul;
	}

	/**
	 * Gets checkbox
	 * 
	 * @return
	 */
	public boolean getChckbxBiblioteca() {
		return chckbxBiblioteca;
	}

	/**
	 * Sets checkbox
	 */
	@XmlElement
	public void setChckbxBiblioteca(boolean chckbxBiblioteca) {
		this.chckbxBiblioteca = chckbxBiblioteca;
	}

	/**
	 * Gets checkbox
	 * 
	 * @return
	 */
	public boolean getChckbxElearning() {
		return chckbxElearning;
	}

	/**
	 * Sets checkbox
	 */
	@XmlElement
	public void setChckbxElearning(boolean chckbxElearning) {
		this.chckbxElearning = chckbxElearning;
	}

	/**
	 * Gets checkbox
	 * 
	 * @return
	 */
	public boolean getChckbxReitora() {
		return chckbxReitora;
	}

	/**
	 * Sets checkbox
	 */
	@XmlElement
	public void setChckbxReitora(boolean chckbxReitora) {
		this.chckbxReitora = chckbxReitora;
	}

	/**
	 * Gets checkbox
	 * 
	 * @return
	 */
	public boolean getChckbxFenix() {
		return chckbxFenix;
	}

	@XmlElement
	public void setChckbxFenix(boolean chckbxFenix) {
		this.chckbxFenix = chckbxFenix;
	}

	/**
	 * Gets checkbox
	 * 
	 * @return
	 */
	public boolean getChckbxTesouraria() {
		return chckbxTesouraria;
	}

	/**
	 * Sets checkbox
	 */
	@XmlElement
	public void setChckbxTesouraria(boolean chckbxTesouraria) {
		this.chckbxTesouraria = chckbxTesouraria;
	}

	/**
	 * Gets checkbox
	 * 
	 * @return
	 */
	public boolean getChckbxTudo() {
		return chckbxTudo;
	}

	/**
	 * Sets checkbox
	 */
	@XmlElement
	public void setChckbxTudo(boolean chckbxTudo) {
		this.chckbxTudo = chckbxTudo;
	}

}