/**
 * 
 */
package pucrs.alpro2.tf;

import pucrs.alpro2.tf.collection.ListaAcid;

/**
 * @author Pedro 'PML' Lemos
 *
 */
public class Logradouro implements ILogradouro, Comparable<Logradouro> {
	private String nome, tipo_endereco;
	private ListaAcid lista;
	
	public Logradouro(String nome, int firstSpaceIndex) {
		if (firstSpaceIndex == -1)
			throw new IllegalArgumentException(String.format("O nome não possui um espaço para separar o tipo do nome do logradouro: %s", nome));
		this.setTipo_endereco(nome, firstSpaceIndex);
		this.setNome(nome, firstSpaceIndex);
		this.lista = new ListaAcid();
	}
	
	@Override
	public String getNome() {
		return nome;
	}
	
	@Override
	public void setNome(String nome, int firstSpaceIndex) {
		this.nome = nome.substring(firstSpaceIndex+1);
	}

	@Override
	public String getTipo_endereco() {
		return tipo_endereco;
	}
	
	@Override
	public void setTipo_endereco(String nome, int firstSpaceIndex) {
		this.tipo_endereco = nome.substring(0, firstSpaceIndex);
	}
	
	@Override
	public ListaAcid getListaAcid() {
		return lista;
	}
	
	@Override
	public void setListaAcid(ListaAcid list) {
		lista = list;
	}
	
	@Override
	public int quantAcidentes() {
		return lista.size();
	}

	@Override
	public String toString() {
		return String.format("LOGRADOURO: %s %s\nTOTAL DE ACIDENTES: %d\n%s", this.getTipo_endereco(), this.getNome(), this.quantAcidentes(), lista.toString());
	}
	

	@Override
	public int compareTo(Logradouro log) {
		return this.nome.compareTo(log.getNome());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Logradouro) {
			Logradouro l = (Logradouro) obj;
			return this.compareTo(l) == 0 && this.getTipo_endereco().equalsIgnoreCase(l.getTipo_endereco()) && this.lista == l.getListaAcid();
		}
		return false;
	}
}
