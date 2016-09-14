/**
 * 
 */
package pucrs.alpro2.tf.collection;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import pucrs.alpro2.tf.Acidente;
import pucrs.alpro2.tf.Logradouro;

/**
 * @author Pedro 'PML' Lemos
 *
 */
public class MapLogradouro {
	private Map<String, Logradouro> mapLogradouro;

	public MapLogradouro() {
		mapLogradouro = new HashMap<>();
	}
	
	public void add(String logradouro, Acidente acidente, int firstSpaceIndex) {
		if (logradouro == null || logradouro.isEmpty())
			throw new IllegalArgumentException("Nome do logradouro é vazio ou null");
		else if (acidente == null)
			throw new IllegalArgumentException("Acidente é null");
		
		Logradouro log = this.get(logradouro);
		if (log != null) // se houver um mapeamento da Key logradouro para um Logradouro, então adicionamos o acidente.
			log.getListaAcid().add(acidente);
		else { // se não houver um mapeamento da Key logradouro para um Logradouro, então...
			log = new Logradouro(logradouro, firstSpaceIndex);
			log.getListaAcid().add(acidente);
			this.mapLogradouro.put(logradouro, log); // cria um mapeamento da Key logradouro para um novo Logradouro.
		}
	}
	
	public Logradouro get(String nome) {
		if (nome == null || nome.isEmpty())
			throw new IllegalArgumentException("Nome do logradouro é vazio ou null");
		return this.mapLogradouro.get(nome);
	}
	
	public Logradouro get(Logradouro log) {
		if (log == null)
			throw new IllegalArgumentException("Logradouro é null");
		String nome = String.format("%s %s", log.getTipo_endereco(), log.getNome());
		return this.get(nome);
	}
	
	public int size() {
		return this.mapLogradouro.size();
	}
	
	public void clear() {
		this.mapLogradouro.clear();
	}
	
	public Iterator<Logradouro> iterator() {
		return this.mapLogradouro.values().iterator();
	}
	
	@Override
	public String toString() {
		StringBuilder aux = new StringBuilder();
		Iterator<Logradouro> it = this.iterator();
		Logradouro log;
		
		while (it.hasNext()) {
			log = it.next();
			aux.append(log.toString());
			aux.append("\n");
		}
		return aux.toString();
	}
}
