package com.example.eshtery;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

public class Database extends SQLiteOpenHelper {

    private static String dbName = "eCommerceDatabase";
    SQLiteDatabase db;

    public Database(Context context)
    {
        super(context,dbName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Customers(CustID integer primary key autoincrement, CustName text not null, Email text not null, Password text not null, Gender text not null, Birthdate text not null, Job text not null)");
        sqLiteDatabase.execSQL("create table Orders(OrderID integer primary key autoincrement, OrderDate text not null, Address text not null, GPSAddress text not null, Email text not null, Appartment text not null, CustID integer not null, foreign key(CustID) references Customers(CustID))");
        sqLiteDatabase.execSQL("create table Categories(CategoryID integer primary key autoincrement, CategoryName text not null)");
        sqLiteDatabase.execSQL("create table Products(ProductID integer primary key autoincrement, ProductName text not null, Price text not null, Quantity integer, BarCode text, CategoryID integer not null, ProductImage blob, Description Text not null, foreign key(CategoryID) references Categories(CategoryID))");
        sqLiteDatabase.execSQL("create table OrderDetails(OrderID integer, ProductID, Quantity integer not null, foreign key(ProductID) references Products(ProductID), foreign key(OrderID) references Orders (OrderID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Customers");
        sqLiteDatabase.execSQL("drop table if exists Orders");
        sqLiteDatabase.execSQL("drop table if exists Categories");
        sqLiteDatabase.execSQL("drop table if exists Products");
        sqLiteDatabase.execSQL("drop table if exists OrderDetails");
    }

    public boolean insertIntoCustomers(String CustName, String Email, String Password, String Gender, String Birthdate, String Job)
    {
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select Email from Customers where Email like ?",new String[]{Email});
        cursor.moveToFirst();
        if(cursor != null)
        {
            if(cursor.getCount() != 0)
                if(cursor.getString(0).equals(Email))
                {
                    db.close();
                    return false;
                }
        }

        ContentValues row = new ContentValues();
        row.put("CustName", CustName);
        row.put("Email", Email);
        row.put("Password", Password);
        row.put("Gender", Gender);
        row.put("Birthdate", Birthdate);
        row.put("Job", Job);
        db = getWritableDatabase();
        db.insert("Customers", null, row);
        db.close();
        return true;
    }

    public boolean checkIfCustomerValid(String Email, String Password, Context context)
    {
        db = getReadableDatabase();
        String[] rowDetails = new String[]{Email};
        Cursor cursor = db.rawQuery("select Password from Customers where Email like ?", rowDetails);
        cursor.moveToFirst();
        //Cursor cursor = db.query("Customers", rowDetails, null, null, null, null, null);
        if (cursor != null)
        {
            if(cursor.getCount() == 0)
            {
                db.close();
                return false;
            }
            String password = cursor.getString(0);
            if(password.equals(Password))
            {
                db.close();
                return true;
            }
        }
        db.close();
        return false;
    }

    public void insertIntoProducts(String ProductName, String Price, String Quantity, byte[] ProductImage, String BarCode, String Description, int CategoryID)
    {
        ContentValues row = new ContentValues();
        row.put("ProductName", ProductName);
        row.put("Price", Price);
        row.put("Quantity", Quantity);
        row.put("ProductImage", ProductImage);
        row.put("BarCode", BarCode);
        row.put("Description", Description);
        row.put("CategoryID", CategoryID);
        db = getWritableDatabase();
        db.insert("Products", null, row);
        db.close();
    }

    public void insertAllCategories()
    {
        db = getReadableDatabase();
        ContentValues row = new ContentValues();
        row.put("CategoryName", "Electronics");
        db.insert("Categories", null, row);
        row = new ContentValues();
        row.put("CategoryName", "Kitchen");
        db.insert("Categories", null, row);
        row = new ContentValues();
        row.put("CategoryName", "Sports");
        db.insert("Categories", null, row);
        row = new ContentValues();
        row.put("CategoryName", "Toys");
        db.insert("Categories", null, row);
        row = new ContentValues();
        row.put("CategoryName", "Home");
        db.insert("Categories", null, row);
        row = new ContentValues();
        row.put("CategoryName", "Perfumes");
        db.insert("Categories", null, row);
        db.close();
    }

    public Cursor fetchAllCategoryItems(String Category)
    {
        db = getReadableDatabase();
        String CatID = "";
        Cursor cursor = db.rawQuery("select CategoryID from Categories where CategoryName like ?", new String[]{Category});
        cursor.moveToFirst();
        if(cursor.getCount() != 0)
            CatID = cursor.getString(0);
        cursor = db.rawQuery("select * from Products where CategoryID like ?", new String[]{CatID});
        cursor.moveToFirst();
        db.close();
        return cursor;
    }

    public Cursor fetchCurrentItem(String ItemName)
    {
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Products where ProductName like ?", new String[]{ItemName});
        cursor.moveToFirst();
        db.close();
        return cursor;
    }

    public Cursor Search(String keyword)
    {
        db = getReadableDatabase();
        String newStr = '%' + keyword + '%';
        Cursor cursor = db.rawQuery("select * from Products where ProductName like ?" , new String[]{newStr});
        cursor.moveToFirst();
        db.close();
        return cursor;
    }

    public void insertOrder(String ProductName, int Quantity, String OrderDate, String Address, String GPSAddress, String Email, String Appartment)
    {
        db = getReadableDatabase();
        Cursor C = db.rawQuery("select ProductID from Products where ProductName like ?", new String[]{ProductName});
        C.moveToFirst();
        if(C.getCount() == 0)
            return;
        int ProductID = C.getInt(0);

        C = db.rawQuery("select CustID from Customers where Email like ?", new String[]{Email});
        C.moveToFirst();
        if(C.getCount() == 0)
            return;
        int CustID = C.getInt(0);

        db = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("ProductID", ProductID);
        row.put("Quantity", Quantity);
        db.insert("OrderDetails", null,row);

        row= new ContentValues();
        row.put("OrderDate", OrderDate);
        row.put("Address", Address);
        row.put("GPSAddress", GPSAddress);
        row.put("Email", Email);
        row.put("Appartment", Appartment);
        row.put("CustID", CustID);
        db = getWritableDatabase();
        db.insert("OrderDetails", null, row);
        db.close();
    }

    public Cursor getBarcodeItem(String barcode)
    {
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select ProductName from Products where BarCode like ?", new String[]{barcode});
        cursor.moveToFirst();
        db.close();
        return cursor;
    }

    public boolean checkEmailandDate(String Email, String Date)
    {
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select Email and Birthdate from Customers where Email like ? and BirthDate like ?", new String[]{Email,Date});
        cursor.moveToFirst();
        db.close();
        if(cursor.getCount() != 0)
            return true;
        return false;
    }

    public void updatePassword(String Email,String Password)
    {
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("update Customers set Password = ? where Email like ?", new String[]{Password,Email});
        cursor.moveToFirst();
        db.close();
    }
}
