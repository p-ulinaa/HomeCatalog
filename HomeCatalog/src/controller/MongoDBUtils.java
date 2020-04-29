package controller;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.DBRef;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

import model.Fasilitas;
import model.Pemilik;
import model.Rumah;
import model.Ukuran;

public class MongoDBUtils {	
	MongoDatabase database;
	MongoCollection<Pemilik> PemilikCollection;	
	MongoCollection<Rumah> RumahCollection;
	
	public MongoDBUtils() {
		// Creating Credentials 
		MongoCredential credential; 
		credential = MongoCredential.createCredential("sampleUser", "myDb", 
				"password".toCharArray()); 
		System.out.println("Connected to the database successfully");  
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));		
		 MongoClient mongo = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
		// Accessing the database 
		database = mongo.getDatabase("myDb"); 
		database = database.withCodecRegistry(pojoCodecRegistry);
		System.out.println("Credentials ::"+ credential);
		PemilikCollection = database.getCollection("pemilikCollection", Pemilik.class);
		RumahCollection = database.getCollection("rumahCollection", Rumah.class);
	}
	
	public ArrayList<Pemilik> getPemilik() throws IOException {		
		ArrayList<Pemilik> resultList = new ArrayList<>();
		FindIterable<Pemilik> pemilikIterable = PemilikCollection.find();		
		for (Pemilik pemilik : pemilikIterable) {
			System.out.println(pemilik);
			resultList.add(pemilik);
		}		
		return resultList;
	}
	
	public ArrayList<Rumah> getRumah() throws IOException {		
		ArrayList<Rumah> resultList = new ArrayList<>();
		FindIterable<Rumah> rumahIterable = RumahCollection.find();		
		for (Rumah rumah : rumahIterable) {
			System.out.println(rumah);
			resultList.add(rumah);
		}		
		return resultList;
	}
	//pemilik meng-insert data rumah
	public boolean inserting(String nama, String status, String provinsi, String alamat, 
						     int harga, String namaP, String nomorHP, String email, 
						     Ukuran ukuran, Fasilitas fasilitas) {
		String idPemilik = new ObjectId().toString();
		try {
			//cek apakah nomorHP sudah ada di idPemilikList
			//jika ada, cari id
			//jika tidak, buat idpemilik baru
			List<String> idRumahList = new ArrayList<String>();
			String idRumah = new ObjectId().toString();
			idRumahList.add(idRumah);
			DBRef myDbRef = new DBRef("myDb", "PemilikCollection", idPemilik);
			Rumah rumah = new Rumah(nama, status, provinsi, alamat, harga, ukuran, fasilitas, myDbRef);
			RumahCollection.insertOne(rumah);

			Pemilik pemilik = new Pemilik(idPemilik, namaP, nomorHP, email, idRumahList);
			pemilik.setId(idPemilik);
			PemilikCollection.insertOne(pemilik);
			System.out.println("data inserted");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//pemilik update status rumah (terjual/belum terjual)
	public boolean updateData() {
		return true;
	}
	//sistem mendelete otomatis rumah yang terjual/pemilik menghapus data
	public boolean delete() {
		return true;
	}
}
