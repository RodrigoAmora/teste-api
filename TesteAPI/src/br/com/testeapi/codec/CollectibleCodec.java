package br.com.testeapi.codec;

import java.io.BufferedReader;

import javax.naming.directory.DirContext;

import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.codecs.EncoderContext;

public interface CollectibleCodec<T> {
	
	public void encode(BsonWriter arg0, T arg1, EncoderContext arg2);
	public Class<T> getEncoderClass();
	public T decode(BufferedReader arg0, DirContext arg1);
	public boolean documentHasId(T arg0);
	public T generateIdIfAbsentFromDocument(T arg0);
	public BsonValue getDocumentId(T arg0);
	  
}
