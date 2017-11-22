package br.com.testeapi.codec;

import java.io.BufferedReader;

import javax.naming.directory.DirContext;

import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import br.com.testeapi.domain.Person;

public class PersonCodec implements CollectibleCodec<Person> {

	private Codec<Document> codec;
	
	public PersonCodec(Codec<Document> codec) {
		this.codec = codec;
	}
	
	@Override
	public void encode(BsonWriter writer, Person pesron, EncoderContext encoder) {
		ObjectId id = pesron.getId();
		String nome = pesron.getNome();
		String sobreNome = pesron.getSobreNome();
		String email = pesron.getEmail();
		String telefone = pesron.getTelefone();
		String endreco = pesron.getEndereco();
	
		Document documento = new Document();
		documento.put("_id", id);
		documento.put("nome", nome);
		documento.put("sobrenome", sobreNome);
		documento.put("email", email);
		documento.put("telefone", telefone);
		documento.put("endreco", endreco);
		
		codec.encode(writer, documento, encoder);
	}

	@Override
	public Class<Person> getEncoderClass() {
		return Person.class;
	}

	@Override
	public Person decode(BufferedReader arg0, DirContext arg1) {
		return null;
	}

	@Override
	public boolean documentHasId(Person person) {
		return person.getId() == null;
	}

	@Override
	public Person generateIdIfAbsentFromDocument(Person person) {
		return documentHasId(person) ? person.criaId() : person;
	}

	@Override
	public BsonValue getDocumentId(Person person) {
		if(!documentHasId(person)){
		    throw new IllegalStateException("Esse Document não tem id");
		 }
		 return new BsonString(person.getId().toHexString());
	}

}
