package com.jcm.portfolio.configuration;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

@Slf4j
public class MongoDBDummy {

  private static final String MONGODB_NAME = "dataBaseApp";

  MongoClient mongoClient = MongoClients.create();
  MongoDatabase database = mongoClient.getDatabase(MONGODB_NAME);

  MongoCollection<Document> collection = database.getCollection("exampleCollection");

  public void getAllDataBases() {
    log.info("mostramos todas las bases de datos en Mongo: ");
    mongoClient.listDatabaseNames().forEach(log::info);
  }

  public void createExampleCollection(String exampleCollection) {
    boolean collectionExists = database.listCollectionNames().into(new ArrayList<>())
        .contains(exampleCollection);
    if (collectionExists) {
      log.info("la coleccion ya existe en la base de datos no se creara");
    } else {
      log.info("creando una nueva coleccion de ejemplo");
      database.createCollection(exampleCollection);
    }
  }

  public void getAllCollections() {
    log.info("mostramos todas las colecciones en base de datos: ");
    database.listCollectionNames().forEach(log::info);
  }

  public void saveExampleDocument() {
    Document document = new Document();
    document.put("name", "dummyName");
    document.put("company", "exampleCompany");
    collection.insertOne(document);
  }

  public void updateExampleDocument() {
    log.info("actualizamos el registro de ejemplo a un nuevo valor ");
    Document query = new Document();
    query.put("name", "dummyName");
    Document newDocument = new Document();
    newDocument.put("name", "updateDummyName");
    Document updateObject = new Document();
    updateObject.put("$set", newDocument);
    collection.updateOne(query, updateObject);
  }

  public void readExampleDocument() {
    Document searchQuery = new Document();
    searchQuery.put("name", "updateDummyName");
    FindIterable<Document> cursor = collection.find(searchQuery);
    log.info("mostramos todos los documentos de la coleccion de ejemplo: ");
    try (final MongoCursor<Document> cursorIterator = cursor.cursor()) {
      while (cursorIterator.hasNext()) {
        log.info(String.valueOf(cursorIterator.next()));
      }
    }
  }

  public void deleteExampleDocument() {
    Document searchQuery = new Document();
    searchQuery.put("name", "updateDummyName");
    collection.deleteOne(searchQuery);
  }


}
