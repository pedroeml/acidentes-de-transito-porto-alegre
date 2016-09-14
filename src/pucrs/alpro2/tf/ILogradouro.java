/**
 * 
 */
package pucrs.alpro2.tf;

import pucrs.alpro2.tf.collection.ListaAcid;

/**
 * @author Pedro 'PML' Lemos
 *
 */
public interface ILogradouro {
	String getNome();
	void setNome(String nome, int indexFirstSpace);
	String getTipo_endereco();
	void setTipo_endereco(String nome, int indexFirstSpace);
	ListaAcid getListaAcid();
	void setListaAcid(ListaAcid list);
	int quantAcidentes();
}
