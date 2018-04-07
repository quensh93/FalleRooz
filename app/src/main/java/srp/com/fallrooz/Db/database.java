package srp.com.fallrooz.Db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class database extends SQLiteOpenHelper {

	public final String path = "data/data/srp.com.fallrooz/databases/";
	public final String Name = "Hafiz";
	public SQLiteDatabase mydb;
	private final Context mycontext;

	public database(Context context) {
		super(context, "Hafiz", null, 1);
		mycontext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

	public void useable() {
		boolean checkdb = checkdb();
		if (checkdb) {

		} else {
			this.getReadableDatabase();
			try {
				copydatabase();
			} catch (IOException e) {
			}
		}
	}

	public void open() {
		mydb = SQLiteDatabase.openDatabase(path + Name, null, SQLiteDatabase.OPEN_READWRITE);
	}

	@Override
	public void close() {
		mydb.close();
	}

	public void resetTables(String table) {
		mydb.delete(table, null, null);
	}

	public boolean checkdb() {
		SQLiteDatabase db = null;
		try {
			db = SQLiteDatabase.openDatabase(path + Name, null, SQLiteDatabase.OPEN_READONLY);
		} catch (SQLException e) {
		}
		return db != null ? true : false;
	}

	public void copydatabase() throws IOException {

		OutputStream myOutput = new FileOutputStream(path + Name);
		byte[] buffer = new byte[1024];
		int lenght;
		InputStream myInput = mycontext.getAssets().open(Name);
		while ((lenght = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, lenght);
		}
		myInput.close();
		myOutput.flush();
		myOutput.close();
	}

	public int getRowCount(String table) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM " + table, null);
		int rowCount = cursor.getCount();
		cursor.close();
		return rowCount;
	}
	public int getlogininfo(String table) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM " + table, null);
		int rowCount = cursor.getCount();
		cursor.close();
		return rowCount;
	}


//	public int getRowCountahangid(String ahangid, String table) {
//		Cursor cursor = mydb.rawQuery("SELECT * FROM " + table + " WHERE ahangid='" + ahangid + "'", null);
//		int rowCount = cursor.getCount();
//		cursor.close();
//		return rowCount;
//	}

	public int getRowCount2(String table, String ostanid) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM " + table + " WHERE ostan_id='" + ostanid + "'", null);
		int rowCount = cursor.getCount();
		cursor.close();
		return rowCount;
	}
	public int insertorderid(String orderid) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM orders", null);
		int rowCount = cursor.getCount();
		cursor.close();
		if (rowCount==0) {
			ContentValues values = new ContentValues();
			
			values.put("orderid", orderid);
			mydb.insert("orders", null, values);
		}else {
			ContentValues cv = new ContentValues();
			cv.put("orderid", orderid);
			mydb.update("orders", cv, null, null);
		}
		
		
		return rowCount;
	}

	public byte[] getPoem(int field,int id) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM Poems WHERE ID = '"+id+"'", null);
		cursor.moveToFirst();
		byte[] blob = cursor.getBlob(field);
		cursor.close();
		return blob;
	}

	public String getorderid() {
		Cursor cursor = mydb.rawQuery("SELECT * FROM orders", null);
		cursor.moveToFirst();
		String str = cursor.getString(1);
		cursor.close();
		return str;
	}
	public int getRowCount3(String table, String shahrid) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM " + table + " WHERE shahr_id='" + shahrid + "'", null);
		int rowCount = cursor.getCount();
		cursor.close();
		return rowCount;
	}
	public int getplansize(String category) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM planning WHERE category='" + category + "'", null);
		int rowCount = cursor.getCount();
		cursor.close();
		return rowCount;
	}
	public int getmylifesize(int category) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM mylife WHERE category='" + category + "'", null);
		int rowCount = cursor.getCount();
		cursor.close();
		return rowCount;
	}
	public String getmylifeinfo(int field, int row, int category) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM mylife WHERE category='" + category + "'", null);
		cursor.moveToPosition(row);
		String str = cursor.getString(field);
		cursor.close();
		return str;
	}
	public int getcategorysize1() {
		Cursor cursor = mydb.rawQuery("SELECT * FROM dowry WHERE isbought = 0 AND category != 'لوازم صوتی و تصویری (داماد)' AND category != 'جعبه ابزار' GROUP BY category", null);
		int rowCount = cursor.getCount();
		cursor.close();
		return rowCount;
	}
	
	public int getcategorysize() {
		Cursor cursor = mydb.rawQuery("SELECT * FROM dowry WHERE isbought = 0 GROUP BY category", null);
		int rowCount = cursor.getCount();
		cursor.close();
		return rowCount;
	}
	public String getdowryinfo(int field, int row) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM dowry WHERE isbought = 0 GROUP BY category ORDER BY sortlvl ASC", null);
		cursor.moveToPosition(row);
		String str = cursor.getString(field);
		cursor.close();
		return str;
	}
	
	public String getdowryinfo1(int field, int row) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM dowry WHERE isbought = 0 AND category != 'لوازم صوتی و تصویری (داماد)' AND category != 'جعبه ابزار' GROUP BY category ORDER BY sortlvl ASC", null);
		cursor.moveToPosition(row);
		String str = cursor.getString(field);
		cursor.close();
		return str;
	}
	
	public int getdowrysize(String category) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM dowry WHERE isbought = 0 AND category='" + category + "'", null);
		int rowCount = cursor.getCount();
		cursor.close();
		return rowCount;
	}
	public String getdowryinfo1(int field, int row,String category) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM dowry WHERE isbought = 0 AND category='" + category + "'", null);
		cursor.moveToPosition(row);
		String str = cursor.getString(field);
		cursor.close();
		return str;
	}
	public String getdowryinfo2(int field,String id) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM dowry WHERE id='" + id + "'", null);
		cursor.moveToFirst();
		String str = cursor.getString(field);
		cursor.close();
		return str;
	}
//
//	public int getRowCount4(String table, String khid) {
//		Cursor cursor = mydb.rawQuery("SELECT * FROM " + table + " WHERE khid='" + khid + "'", null);
//		int rowCount = cursor.getCount();
//		return rowCount;
//	}
//
	public String namayesh(int field, int row, String table) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM " + table, null);
		cursor.moveToPosition(row);
		String str = cursor.getString(field);
		cursor.close();
		return str;
	}
	public String getplanninginfo(int field, int row, String category) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM planning WHERE category='" + category + "'", null);
		cursor.moveToPosition(row);
		String str = cursor.getString(field);
		cursor.close();
		return str;
	}
	public String namayesh2(int field, int row, String table, String ostanid) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM " + table + " WHERE ostan_id='" + ostanid + "'", null);
		cursor.moveToPosition(row);
		String str = cursor.getString(field);
		cursor.close();
		return str;
	}
//
	public String namayesh3(int field, int row, String table, String shahrid) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM " + table + " WHERE shahr_id='" + shahrid + "'", null);
		cursor.moveToPosition(row);
		String str = cursor.getString(field);
		cursor.close();
		return str;
	}
	public String getrole() {
		Cursor cursor = mydb.rawQuery("SELECT * FROM login ", null);
		cursor.moveToFirst();
		String str = cursor.getString(14);
		cursor.close();
		return str;
	}
	public String getuiduser() {
		Cursor cursor = mydb.rawQuery("SELECT * FROM login ", null);
		cursor.moveToFirst();
		String str = cursor.getString(1);
		cursor.close();
		return str;
	}
	public String getuidservice() {
		Cursor cursor = mydb.rawQuery("SELECT * FROM khadamatiha ", null);
		cursor.moveToFirst();
		String str = cursor.getString(1);
		cursor.close();
		return str;
	}
	public String getuserinfo(int field) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM login ", null);
		cursor.moveToFirst();
		String str = cursor.getString(field);
		cursor.close();
		return str;
	}
	public String getserviceinfo(int field) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM khadamatiha ", null);
		cursor.moveToFirst();
		String str = cursor.getString(field);
		cursor.close();
		return str;
	}
	public String getlocationinfo(int field) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM locationinfo ", null);
		cursor.moveToFirst();
		String str;
		if (cursor.getCount() !=0) {
			str = cursor.getString(field);
		} else {
			str="";
		}
		
		cursor.close();
		return str;
	}
	public int getboughtsize() {
		Cursor cursor = mydb.rawQuery("SELECT * FROM dowry WHERE isbought = 1", null);
		int rowCount = cursor.getCount();
		cursor.close();
		return rowCount;
	}
	public int gettopservicessize(int service) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM topservice WHERE service = '" + service + "'", null);
		int rowCount = cursor.getCount();
		cursor.close();
		return rowCount;
	}
	public String getboughtinfo(int field,int row) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM dowry WHERE isbought = 1", null);
		cursor.moveToPosition(row);
		String str = cursor.getString(field);
		cursor.close();
		return str;
	}
	public String gettopserviceinfo(int field,int row,int service) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM topservice WHERE service = '" + service + "'", null);
		cursor.moveToPosition(row);
		String str = cursor.getString(field);
		cursor.close();
		return str;
	}
//
//	public String namayesh4(int field, String table) {
//
//		try {
//			android.database.Cursor cursor = mydb.rawQuery("SELECT * FROM " + table, null);
//			cursor.moveToFirst();
//			String str = cursor.getString(field);
//			cursor.close();
//			return str;
//		} catch (Exception e) {
//			String str = "0";
//			return str;
//		}
//
//	}
//
//	public String namayesh5(int field, String table, String khid) {
//		try {
//			android.database.Cursor cursor = mydb.rawQuery("SELECT * FROM " + table + " WHERE khid='" + khid + "'", null);
//			cursor.moveToFirst();
//			String str = cursor.getString(field);
//			cursor.close();
//			return str;
//		} catch (Exception e) {
//			String str = "0";
//			return str;
//		}
//
//	}
//
//	public String namayesh6(int field, String table, String ostan) {
//		try {
//			android.database.Cursor cursor = mydb.rawQuery("SELECT * FROM " + table + " WHERE ostan='" + ostan + "'", null);
//			cursor.moveToFirst();
//			String str = cursor.getString(field);
//			cursor.close();
//			return str;
//		} catch (Exception e) {
//			String str = "0";
//			return str;
//		}
//
//	}
//
//	public String namayesh66(int field, String table, String id) {
//		try {
//			android.database.Cursor cursor = mydb.rawQuery("SELECT * FROM " + table + " WHERE id='" + id + "'", null);
//			cursor.moveToFirst();
//			String str = cursor.getString(field);
//			cursor.close();
//			return str;
//		} catch (Exception e) {
//			String str = "0";
//			return str;
//		}
//
//	}
//
//	public void deleteFarayand(String table, String name, String hazine, String address, String phone, String id) {
//		mydb.delete(table, " name = '" + name + "' AND hazine = '" + hazine + "' AND address = '" + address + "' AND phone = '" + phone
//				+ "' AND khid = '" + id + "'", null);
//	}
//
	public void deletebarname(String id) {
		mydb.delete("mylife", " id = '" + id + "' ", null);
	}
	public void deletebought(String id) {
		mydb.delete("dowry", " id = '" + id + "' ", null);
	}
	public void deleteevent(String id) {
		mydb.delete("majales", " id = '" + id + "' ", null);
	}
//
//	public void delete2(String table, String khid) {
//		mydb.delete(table, " khid = '" + khid + "'", null);
//	}
//
	
	public void addlocationinfo(String ostanid) {
		ContentValues values = new ContentValues();
		
		values.put("ostanid", ostanid);
		mydb.insert("locationinfo", null, values);
	}
	public void updatelocationinfo(String ostanid) {
		ContentValues cv = new ContentValues();
		cv.put("ostanid", ostanid);
		mydb.update("locationinfo", cv, null, null);
	}
	public void addlike(String uuid) {
		ContentValues values = new ContentValues();
		values.put("uuid", uuid);
		mydb.insert("islike", null, values);
	}
	
	public int islike(String uuid) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM islike WHERE uuid ='" + uuid + "' ", null);
		int rowCount = cursor.getCount();
		cursor.close();
		return rowCount;
	}
	public void addrate(String musicuid,String emtiaz) {
		ContentValues values = new ContentValues();
		values.put("musicuid", musicuid);
		values.put("emtiaz", emtiaz);
		mydb.insert("israte", null, values);
	}
	public int israte(String musicuid) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM israte WHERE musicuid ='" + musicuid + "' ", null);
		int rowCount = cursor.getCount();
		cursor.close();
		return rowCount;
	}
	public String getrate(String musicuid) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM israte WHERE musicuid ='" + musicuid + "' ", null);
		cursor.moveToFirst();
		String str = cursor.getString(2);
		
		cursor.close();
		return str;
	}
	public void adduser(String uid, String name, String family, String mobile, String ezdid,
			String ezddate, String sex, String bodje, String zoojid, String birthday, String pass, String role) {
		ContentValues values = new ContentValues();
		values.put("uid", uid);
		values.put("name", name);
		values.put("family", family);
		values.put("mobile", mobile);
		values.put("ezdid", ezdid);
		values.put("ezddate", ezddate);
		values.put("sex", sex);
		values.put("bodje", bodje);
		values.put("zoojid", zoojid);
		values.put("birthday", birthday);
		values.put("pass", pass);
		values.put("role", role);
		mydb.insert("login", null, values);
	}
	public void addtopservices(String service, String username, String managername, String uuid, String minprice,
			String zone, String mobile, String avatar, String aboutme, String address, String phone, String site
			, String telegram, String instagram, String facebook, String latt, String longg, String aparat, String nlike) {
		ContentValues values = new ContentValues();
		values.put("service", service);
		values.put("username", username);
		values.put("managername", managername);
		values.put("uuid", uuid);
		values.put("minprice", minprice);
		values.put("zone", zone);
		values.put("mobile", mobile);
		values.put("avatar", avatar);
		values.put("aboutme", aboutme);
		values.put("address", address);
		values.put("phone", phone);
		values.put("site", site);
		values.put("telegram", telegram);
		values.put("instagram", instagram);
		values.put("facebook", facebook);
		values.put("latt", latt);
		values.put("longg", longg);
		values.put("aparat", aparat);
		values.put("nlike", nlike);
		mydb.insert("topservice", null, values);
	}
	public void addkhadamati(String uid, String ostan, String shahr, String mantaghe, String senf, String khadamatname, String name,
			String mobile, String phone, String address, String latt, String longg) {
		ContentValues values = new ContentValues();
		values.put("uid", uid);
		values.put("ostan", ostan);
		values.put("shahr", shahr);
		values.put("mantaghe", mantaghe);
		values.put("senf", senf);
		values.put("khadamatname", khadamatname);
		values.put("name", name);
		values.put("mobile", mobile);
		values.put("phone", phone);
		values.put("address", address);
		values.put("latt", latt);
		values.put("longg", longg);
		mydb.insert("khadamatiha", null, values);
	}
	public String addbarname(int category, String onvan, String tozihat, String tarikh, String saat) {
		ContentValues values = new ContentValues();
		values.put("category", category);
		values.put("onvan", onvan);
		values.put("tozihat", tozihat);
		values.put("tarikh", tarikh);
		values.put("saat", saat);
		mydb.insert("mylife", null, values);
		
		Cursor cursor = mydb.rawQuery("SELECT * FROM mylife WHERE onvan='" + onvan + "' AND tozihat='" + tozihat + "' AND tarikh='" + tarikh + "' AND category='" + category + "'", null);
		cursor.moveToFirst();
		String str = cursor.getString(0);
		cursor.close();
		return str;
	}
	
	public String getinfobyid(int field,String id,String table) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM " + table + " WHERE id='" + id + "'", null);
		cursor.moveToFirst();
		String str;
		if (cursor.getCount() !=0) {
			str = cursor.getString(field);
		}else {
			str ="";
		}
		
		cursor.close();
		return str;
	}
//
//	public void addahangid(String ahangid) {
//		ContentValues values = new ContentValues();
//		values.put("ahangid", ahangid);
//		mydb.insert("rate", null, values);
//	}
//
	public String addmajles(String name, String tarikh, String mobile, String price, String desc) {
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("tarikh", tarikh);
		values.put("mobile", mobile);
		values.put("price", price);
		values.put("desc", desc);
		mydb.insert("majales", null, values);
		
		
		Cursor cursor = mydb.rawQuery("SELECT * FROM majales WHERE name='" + name + "' AND tarikh='" + tarikh + "' AND price='" + price + "' AND desc='" + desc + "'", null);
		cursor.moveToFirst();
		String str = cursor.getString(0);
		cursor.close();
		return str;
	}
	public void updatemajles(String id, String name, String tarikh, String mobile, String price, String desc) {
		ContentValues cv = new ContentValues();
		cv.put("name", name);
		cv.put("tarikh", mobile);
		cv.put("mobile", tarikh);
		cv.put("price", price);
		cv.put("desc", desc);
		mydb.update("majales", cv, "id='"+id+"' ", null);
	}
	public void updateavatar(String table, String avatar, String uid) {
		ContentValues cv = new ContentValues();
		cv.put("avatar", avatar);
		mydb.update(table, cv, "uid='"+uid+"' ", null);
	}
	
	public void updateprofileservice(String khadamatname, String name, String ostan, String shahr, String mantaghe) {
		ContentValues cv = new ContentValues();
		cv.put("khadamatname", khadamatname);
		cv.put("name", name);
		cv.put("ostan", ostan);
		cv.put("shahr", shahr);
		cv.put("mantaghe", mantaghe);
		mydb.update("khadamatiha", cv, null, null);
	}
	public void updateprofileguest(String name, String family) {
		ContentValues cv = new ContentValues();
		cv.put("name", name);
		cv.put("family", family);
		mydb.update("login", cv, null, null);
	}
	public void updateprofilecouple(String name, String family, String weddingdate, String budget, String birthday) {
		ContentValues cv = new ContentValues();
		cv.put("name", name);
		cv.put("family", family);
		cv.put("birthday", birthday);
		cv.put("ezddate", weddingdate);
		cv.put("bodje", budget);
		mydb.update("login", cv, null, null);
	}
//
//	public void addfarayand(String name, String khid, String hazine, String address, String phone, String darsad) {
//		ContentValues values = new ContentValues();
//		values.put("name", name);
//		values.put("khid", khid);
//		values.put("hazine", hazine);
//		values.put("address", address);
//		values.put("phone", phone);
//		values.put("darsad", darsad);
//		mydb.insert("farayand", null, values);
//	}
//
	public void updatebarname(String id, String onvan, String tozihat, String tarikh, String saat) {
		ContentValues cv = new ContentValues();
		cv.put("onvan", onvan);
		cv.put("tozihat", tozihat);
		cv.put("tarikh", tarikh);
		cv.put("saat", saat);
		mydb.update("mylife", cv, "id='"+id+"' ", null);
	}
	public void updatedowry(String id, String price) {
		ContentValues cv = new ContentValues();
		cv.put("price", price);
		cv.put("isbought", 1);
		mydb.update("dowry", cv, "id='"+id+"' ", null);
	}
	public void updateplanning (String id,String value) {
		ContentValues cv = new ContentValues();
		cv.put("checked", value);
		mydb.update("planning", cv, "id = '"+id+"' ", null);
	}
	public void updatecontactinfo(String mobile,String phone,String email,String site,
			String telegram,String instagram,String facebook,String aparat) {
		ContentValues cv = new ContentValues();
		cv.put("mobile", mobile);
		cv.put("phone", phone);
		cv.put("email", email);
		cv.put("site", site);
		cv.put("telegram", telegram);
		cv.put("instagram", instagram);
		cv.put("facebook", facebook);
		cv.put("aparat", aparat);
		mydb.update("khadamatiha", cv, null, null);
	}
	public void updateaboutme(String aboutme) {
		ContentValues cv = new ContentValues();
		cv.put("aboutme", aboutme);
		mydb.update("khadamatiha", cv, null, null);
	}
	public void updatelocationinfo(String ostanid,String shahrid,String mantagheid,String address,
			String latt,String longg) {
		ContentValues cv = new ContentValues();
		cv.put("ostan", ostanid);
		cv.put("shahr", shahrid);
		cv.put("mantaghe", mantagheid);
		cv.put("address", address);
		cv.put("latt", latt);
		cv.put("longg", longg);
		mydb.update("khadamatiha", cv, null, null);
	}
//
//	public void updateinfo(String ezddate, String ezdid, String bodje, String sex, String zoojid) {
//		ContentValues cv = new ContentValues();
//		cv.put("ezddate", ezddate);
//		cv.put("ezdid", ezdid);
//		cv.put("bodje", bodje);
//		cv.put("sex", sex);
//		cv.put("zoojid", zoojid);
//		mydb.update("login", cv, null, null);
//		// mydb.update("login", cv, "uid='"+uid+"' ", null);
//	}
//	public void updatezoojinfo(String hamsarname) {
//		ContentValues cv = new ContentValues();
//		cv.put("hamsarname", hamsarname);
//		mydb.update("login", cv, null, null);
//		// mydb.update("login", cv, "uid='"+uid+"' ", null);
//	}
//	public void updatetedadpayamold(String tedadold) {
//		ContentValues cv = new ContentValues();
//		cv.put("tedadold", tedadold);
//		mydb.update("payamha", cv, null, null);
//		// mydb.update("login", cv, "uid='"+uid+"' ", null);
//	}
//	public void updatetedadpayamnew(String tedadnew) {
//		ContentValues cv = new ContentValues();
//		cv.put("tedadnew", tedadnew);
//		mydb.update("payamha", cv, null, null);
//		// mydb.update("login", cv, "uid='"+uid+"' ", null);
//	}
//	public void updatetasvir(String tasvir) {
//		ContentValues cv = new ContentValues();
//		cv.put("tasvir", tasvir);
//		mydb.update("login", cv, null, null);
//		// mydb.update("login", cv, "uid='"+uid+"' ", null);
//	}
//
//	public void updatetasvirKhadamat(String tasvir) {
//		ContentValues cv = new ContentValues();
//		cv.put("tasvir", tasvir);
//		mydb.update("khadamatiha", cv, null, null);
//		// mydb.update("login", cv, "uid='"+uid+"' ", null);
//	}
//
	public void addFavorite(String pid, String tedad, String name, String tozihat) {
		ContentValues values = new ContentValues();
		values.put("pid", pid);
		values.put("tedad", tedad);
		values.put("name", name);
		values.put("tozihat", tozihat);
		mydb.insert("fav", null, values);
	}
	public void addFavoriteService(String uuid, String username, String managername, String mobile, String minprice, String zone,
			String address, String phone, String site, String telegram, String instagram, String facebook, String latt,
			String longg, String aboutme, String aparat, String service, String avatar, String nlike) {
		ContentValues values = new ContentValues();
		values.put("uuid", uuid);
		values.put("username", username);
		values.put("managername", managername);
		values.put("mobile", mobile);
		values.put("minprice", minprice);
		values.put("zone", zone);
		values.put("address", address);
		values.put("phone", phone);
		values.put("site", site);
		values.put("telegram", telegram);
		values.put("instagram", instagram);
		values.put("facebook", facebook);
		values.put("latt", latt);
		values.put("longg", longg);
		values.put("aboutme", aboutme);
		values.put("aparat", aparat);
		values.put("service", service);
		values.put("avatar", avatar);
		values.put("nlike", nlike);
		
		mydb.insert("favResult", null, values);
	
	}
	public void deleteFavoriteService(String uuid) {
		mydb.delete("favResult", " uuid = '" + uuid + "' ", null);
	}
	public void deleteFavorite(String pid) {
		mydb.delete("fav", " pid = '" + pid + "' ", null);
	}
	public int isfav(String pid) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM fav WHERE pid ='" + pid + "' ", null);
		int rowCount = cursor.getCount();
		cursor.close();
		return rowCount;
	}
	
	public int isfavservice(String uuid) {
		Cursor cursor = mydb.rawQuery("SELECT * FROM favResult WHERE uuid = '" + uuid + "' ", null);
		int rowCount = cursor.getCount();
		cursor.close();
		return rowCount;
	}
	public Cursor getFavorite() {
		Cursor cursor = mydb.rawQuery("SELECT * FROM fav ", null);
		return cursor;
	}
	public Cursor getFavoriteSerice() {
		Cursor cursor = mydb.rawQuery("SELECT * FROM favResult ", null);
		return cursor;
	}
//
//	// ==============================Tarikh Start
//	public void addTarikh(String tarikhaghd, String tarikhtavalod) {
//		ContentValues values = new ContentValues();
//		values.put("tarikhaghd", tarikhaghd);
//		values.put("tarikhtavalod", tarikhtavalod);
//		mydb.insert("tarikh", null, values);
//	}
//
//	public void updateTarikh(String tarikhaghd, String tarikhtavalod) {
//		ContentValues cv = new ContentValues();
//		cv.put("ezddate", tarikhaghd);
//		cv.put("zoojid", tarikhtavalod);
//		mydb.update("tarikh", cv, null, null);
//	}
//
//	// ==============================Result Favorites Start
//
//	public void addFavoriteResult(String uidzooj, String uidkhadamat, String ads, String mobile, String phone, String senf, String khadamatname,
//			String name, String tasvir, String address, String daraje, String noe, String tedad, String tozihat, String tamas, String minprice1,
//			String minprice2, String minprice3, String ostan, String shahr, String mantaghe, String ezddate) {
//		ContentValues values = new ContentValues();
//		values.put("uidzooj", uidzooj);
//		values.put("uidkhadamat", uidkhadamat);
//		values.put("ads", ads);
//		values.put("mobile", mobile);
//		values.put("phone", phone);
//		values.put("senf", senf);
//		values.put("khadamatname", khadamatname);
//		values.put("name", name);
//		values.put("tasvir", tasvir);
//		values.put("address", address);
//		values.put("daraje", daraje);
//		values.put("noe", noe);
//		values.put("tedad", tedad);
//		values.put("tozihat", tozihat);
//		values.put("tamas", tamas);
//		values.put("minprice1", minprice1);
//		values.put("minprice2", minprice2);
//		values.put("minprice3", minprice3);
//		values.put("ostan", ostan);
//		values.put("shahr", shahr);
//		values.put("mantaghe", mantaghe);
//		values.put("ezddate", ezddate);
//		mydb.insert("favResult", null, values);
//	}
//
//	public void deleteFavoriteResult(String uidZooj, String uidKhadamat, String ads, String mobile, String phone, String senf, String khadamatname,
//			String name, String tasvir, String address, String daraje, String noe, String tedad, String tozihat, String tamas, String minprice1,
//			String minprice2, String minprice3, String ostan, String shahr, String mantaghe, String ezddate) {
//		mydb.delete("favResult", " uidzooj = '" + uidZooj + "' AND uidkhadamat = '" + uidKhadamat + "' AND ads = '" + ads + "' AND mobile = '"
//				+ mobile + "' AND phone = '" + phone + "' AND senf = '" + senf + "' AND khadamatname = '" + khadamatname + "' AND name = '" + name
//				+ "' AND tasvir = '" + tasvir + "' And address = '" + address + "' AND daraje = '" + daraje + "' AND noe = '" + noe
//				+ "' AND tedad = '" + tedad + "' AND tozihat = '" + tozihat + "' AND tamas = '" + tamas + "' AND minprice1 = '" + minprice1
//				+ "' AND minprice2 = '" + minprice2 + "' AND minprice3 = '" + minprice3 + "' AND ostan = '" + ostan + "' AND shahr = '" + shahr
//				+ "' AND mantaghe = '" + mantaghe + "' AND ezddate = '" + ezddate + "'", null);
//	}
//
//	public Cursor getFavoriteResult(String uidZooj, String uidKhadamat, String ads, String mobile, String phone, String senf, String khadamatname,
//			String name, String tasvir, String address, String daraje, String noe, String tedad, String tozihat, String tamas, String minprice1,
//			String minprice2, String minprice3, String ostan, String shahr, String mantaghe, String ezddate) {
//		Cursor cursor = mydb.rawQuery("SELECT * FROM favResult WHERE uidzooj = '" + uidZooj + "' AND uidkhadamat = '" + uidKhadamat + "' AND ads = '"
//				+ ads + "' AND mobile = '" + mobile + "' AND phone = '" + phone + "' AND senf = '" + senf + "' AND khadamatname = '" + khadamatname
//				+ "' AND name = '" + name + "' AND tasvir = '" + tasvir + "' And address = '" + address + "' AND daraje = '" + daraje
//				+ "' AND noe = '" + noe + "' AND tedad = '" + tedad + "' AND tozihat = '" + tozihat + "' AND tamas = '" + tamas
//				+ "' AND minprice1 = '" + minprice1 + "' AND minprice2 = '" + minprice2 + "' AND minprice3 = '" + minprice3 + "' AND ostan = '"
//				+ ostan + "' AND shahr = '" + shahr + "' AND mantaghe = '" + mantaghe + "' AND ezddate = '" + ezddate + "'", null);
//		return cursor;
//	}
//
//	public void deleteFavoriteResultLow(String address, String uidKhadamat, String tasvir, String mobile) {
//		mydb.delete("favResult", " address = '" + address + "' AND uidkhadamat = '" + uidKhadamat + "' AND tasvir = '" + tasvir + "' AND mobile = '"
//				+ mobile + "'", null);
//	}
//
//	public Cursor getFavoriteResultLow(String address, String uidKhadamat, String tasvir, String mobile) {
//		Cursor cursor = mydb.rawQuery("SELECT * FROM favResult WHERE address = '" + address + "' AND uidkhadamat = '" + uidKhadamat
//				+ "' AND tasvir = '" + tasvir + "' AND mobile = '" + mobile + "'", null);
//		return cursor;
//	}
//
//	// ==============================Result Favorites END
//
//	public int getFav(String table, String goroh, String tedad, String tname, String tozihat) {
//		String query = "SELECT * FROM " + table + " WHERE goroh = '" + goroh + "' AND tedad ='" + tedad + "' AND tname='" + tname
//				+ "' AND tozihat ='" + tozihat + "'";
//		Cursor cursor = mydb.rawQuery(query, null);
//		int rowCount = cursor.getCount();
//		return rowCount;
//	}
//
//	public void updateTozihat(String tabel, String tozihat) {
//		ContentValues cv = new ContentValues();
//		cv.put("tozihat", tozihat);
//		mydb.update(tabel, cv, null, null);
//	}
//
//	public String namayeshgcm(String table) {
//		android.database.Cursor cursor = mydb.rawQuery("SELECT * FROM " + table, null);
//		cursor.moveToFirst();
//		String str = cursor.getString(0);
//		return str;
//	}
//
//	public void updategcm(String value) {
//		ContentValues cv = new ContentValues();
//		cv.put("id", value);
//		mydb.update("gcm", cv, null, null);
//	}
}
