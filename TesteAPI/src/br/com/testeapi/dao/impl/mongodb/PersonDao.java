package br.com.testeapi.dao.impl.mongodb;

import java.util.List;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import br.com.testeapi.codec.PersonCodec;
import br.com.testeapi.dao.Dao;
import br.com.testeapi.domain.Person;

public class PersonDao implements Dao<Person> {

	Codec<Document> codec;
	PersonCodec personCodec;
	
	MongoClient client;
	MongoDatabase database;
	MongoCollection<Person> personCollection;
	
	public PersonDao() {
		codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
		personCodec = new PersonCodec(codec);
		CodecRegistry registro = CodecRegistries.fromRegistries(
			      MongoClient.getDefaultCodecRegistry(), 
			      CodecRegistries.fromCodecs(personCodec));

		MongoClientOptions options = MongoClientOptions.builder().codecRegistry(registro).build();
			  
		client = new MongoClient("localhost:27017", options);
		database = client.getDatabase("test");
		personCollection = database.getCollection("person", Person.class);
	}

	@Override
	public Boolean delete(Person t) {
		personCollection.findOneAndDelete(t.getClass());
		Person p =findById(t.getId());
		fecharConexao();
		if (p != null) {
			return false;
		} else {
			return false;
		}
	}

	@Override
	public Person findById(ObjectId id) {
		Person p = personCollection.find(Filters.eq("id", id), Person.class).first();
		fecharConexao();
		return p;
	}

	@Override
	public List<Person> listAll(Person t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Person t) {
		personCollection.findOneAndDelete(t);
		Person p = findById(t.getId());
		fecharConexao();
		if (p == t) {
			return false;
		} else {
			return false;
		}
	}

	@Override
	public Boolean save(Person t) {
		personCollection.insertOne(t);
		Person p = findById(t.getId());
		fecharConexao();
		if (p == null) {
			return false;
		} else {
			return false;
		}
	}
	
	private void fecharConexao() {
		this.client.close();
	}

}
